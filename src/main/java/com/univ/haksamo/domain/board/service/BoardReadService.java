package com.univ.haksamo.domain.board.service;

import com.univ.haksamo.domain.board.entity.Board;
import com.univ.haksamo.domain.board.repository.BoardCustomRepository;
import com.univ.haksamo.domain.board.repository.BoardJpaRepository;
import com.univ.haksamo.domain.board.service.res.BoardDto;
import com.univ.haksamo.domain.board.service.res.BoardListResponse;
import com.univ.haksamo.domain.image.entity.Image;
import com.univ.haksamo.domain.image.repository.ImageRepository;
import com.univ.haksamo.domain.keyword.entity.Keyword;
import com.univ.haksamo.domain.keyword.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardReadService {
    private final ImageRepository imageRepository;
    private final BoardCustomRepository boardCustomRepository;
    private final BoardJpaRepository boardJpaRepository;
    private final KeywordRepository keywordRepository;
    private static final int PAGE_SIZE = 20;

    public BoardDto getBoard(Long boardId){
        Board board = boardJpaRepository.findById(boardId)
                .orElseThrow(()->new IllegalArgumentException("없는 게시글"));
        List<Image> imagesInBoard = imageRepository.findAllByBoardId(board.getId());
        List<String> imagesUrl = imagesInBoard.stream().map(image -> image.getUrl()).collect(Collectors.toList());
        return BoardDto.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .contents(board.getContents())
                .createdDateTime(board.getCreatedDateTime())
                .imageUrl(imagesUrl)
                .build();
    }
    public BoardListResponse getBoards(Long lastBookId, Long groupId) {
        List<Board> boardPaging = boardCustomRepository.findAllByGroupId(lastBookId, groupId, PAGE_SIZE);
        List<BoardDto> boardDtos = new ArrayList<>();
        for (Board board : boardPaging) {
            boardDtos.add(getBoard(board.getId()));
        }
        return BoardListResponse.builder().boards(boardDtos).build();

    }

    public BoardListResponse getBoardsByInput(String input) {
        Keyword keyword = keywordRepository.findByName(input);
        Set<Board> boardPaging = new HashSet<>();
        boardPaging.addAll(boardCustomRepository.findAllByTitleInput(input, PAGE_SIZE));
        boardPaging.addAll(boardCustomRepository.findAllByContentInput(input, PAGE_SIZE));
        if(keyword != null){
            boardPaging.addAll(boardCustomRepository.findAllByKeywordInput(keyword.getId(), PAGE_SIZE));
        }
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
        return BoardListResponse.builder().boards(boardDtos).build();
    }

}
