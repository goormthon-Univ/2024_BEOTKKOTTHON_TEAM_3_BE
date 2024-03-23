package com.univ.haksamo.domain.bookmark.service;

import com.univ.haksamo.domain.board.entity.Board;
import com.univ.haksamo.domain.board.repository.BoardJpaRepository;
import com.univ.haksamo.domain.board.service.BoardReadService;
import com.univ.haksamo.domain.board.service.res.BoardDto;
import com.univ.haksamo.domain.bookmark.dto.ScrapBoardsDto;
import com.univ.haksamo.domain.bookmark.entity.UserBoard;
import com.univ.haksamo.domain.bookmark.repository.UserBoardRepository;
import com.univ.haksamo.domain.user.entity.User;
import com.univ.haksamo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBoardService {
    private final UserBoardRepository userBoardRepository;
    private final BoardReadService boardReadService;
    private final UserRepository userRepository;
    private final BoardJpaRepository boardJpaRepository;


    public ScrapBoardsDto findUserBoard(Long userId) {
        List<UserBoard> userBoards = userBoardRepository.findAllByUserId(userId);
        List<BoardDto> scrapBoard = new ArrayList<>();
        for (UserBoard userBoard : userBoards) {
            Board board = userBoard.getBoard();
            scrapBoard.add(boardReadService.getBoard(board.getId()));
        }
        return ScrapBoardsDto.builder()
                .scrapBoards(scrapBoard)
                .build();
    }

    public void save(Long userId, Long boardId) {
        User userRef = userRepository.getReferenceById(userId);
        Board boardRef = boardJpaRepository.getReferenceById(boardId);
        userBoardRepository.save(UserBoard.builder()
                .user(userRef)
                .board(boardRef)
                .build());
    }

    public void delete(Long userId, Long boardId) {
        UserBoard userBoardRef = userBoardRepository.getUserBoardByBoardIdAndUserId(boardId, userId);
        userBoardRepository.delete(userBoardRef);
    }
}
