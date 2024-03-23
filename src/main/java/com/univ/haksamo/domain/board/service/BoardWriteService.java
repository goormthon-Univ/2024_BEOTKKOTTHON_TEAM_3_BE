package com.univ.haksamo.domain.board.service;

import com.univ.haksamo.domain.board.controller.req.WriteBoardRequestDto;
import com.univ.haksamo.domain.board.entity.Board;
import com.univ.haksamo.domain.board.repository.BoardJpaRepository;
import com.univ.haksamo.domain.common.fcm.service.MessageSendService;
import com.univ.haksamo.domain.group.entity.Group;
import com.univ.haksamo.domain.group.repository.GroupJpaRepository;
import com.univ.haksamo.domain.image.entity.Image;
import com.univ.haksamo.domain.image.repository.ImageRepository;
import com.univ.haksamo.domain.image.service.S3WriteService;
import com.univ.haksamo.domain.image.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardWriteService {
    private final BoardJpaRepository boardJpaRepository;
    private final GroupJpaRepository groupJpaRepository;
    private final S3WriteService s3WriteService;
    private final ImageRepository imageRepository;
    private final MessageSendService messageSendService;

    public void writeBoard(Long groupId, List<MultipartFile> images, WriteBoardRequestDto requestDto) throws IOException {
        Group groupProxy = groupJpaRepository.getReferenceById(groupId);

        //게시글 저장
        Board board = Board.builder()
                .contents(requestDto.getContents())
                .title(requestDto.getTitle())
                .group(groupProxy)
                .keywordId(requestDto.getKeywordId())
                .build();
        boardJpaRepository.save(board);
        //게시글의 이미지 저장
        List<Image> imageArr = new ArrayList<>();
        for (MultipartFile image : images) {
            String imageUrl = s3WriteService.saveImageToS3(image);
            String imageName = ImageUtil.splitUrlForGetImageName(imageUrl);
            imageArr.add(Image.builder()
                    .board(board)
                    .url(imageUrl)
                    .name(imageName)
                    .build());
        }
        imageRepository.saveAll(imageArr);
        //키워드를 가진 사용자에게 알림
        messageSendService.send(board.getId(),requestDto.getKeywordId());
    }
}
