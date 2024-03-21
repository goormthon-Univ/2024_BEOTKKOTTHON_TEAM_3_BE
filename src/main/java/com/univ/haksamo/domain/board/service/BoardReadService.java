package com.univ.haksamo.domain.board.service;

import com.univ.haksamo.domain.board.entity.Board;
import com.univ.haksamo.domain.board.repository.BoardCustomRepository;
import com.univ.haksamo.domain.board.repository.BoardJpaRepository;
import com.univ.haksamo.domain.board.service.res.BoardDto;
import com.univ.haksamo.domain.board.service.res.BoardsResponse;
import com.univ.haksamo.domain.image.entity.Image;
import com.univ.haksamo.domain.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardReadService {
    private final ImageRepository imageRepository;
    private final BoardCustomRepository boardCustomRepository;
    private static final int PAGE_SIZE = 20;

    public BoardsResponse getBoards(Long lastBookId, Long groupId) {
        List<Board> boardPaging = boardCustomRepository.findAllByGroupId(lastBookId, groupId, PAGE_SIZE);
        List<BoardDto> boardDtos = new ArrayList<>();
        for (Board board : boardPaging) {
            List<Image> imagesInBoard = imageRepository.findAllByBoardId(board.getId());
            List<String> imagesUrl = imagesInBoard.stream().map(image -> image.getUrl()).collect(Collectors.toList());
            boardDtos.add(BoardDto.builder()
                    .boardId(board.getId())
                    .title(board.getTitle())
                    .contents(board.getContents())
                    .createdDateTime(board.getCreatedDateTime())
                    .imageUrl(imagesUrl)
                    .build());
        }
        return BoardsResponse.builder().boards(boardDtos).build();

    }
}