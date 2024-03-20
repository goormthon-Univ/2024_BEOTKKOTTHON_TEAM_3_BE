package com.univ.haksamo.domain.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.univ.haksamo.domain.board.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.univ.haksamo.domain.board.entity.QBoard.board;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Board> findAllByGroupId(Long lastBoardId, Long groupId, int pageSize) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(lastBoardId!=null){
            booleanBuilder.and(board.id.gt(lastBoardId));
        }
        return jpaQueryFactory.select(board)
                .from(board).where(booleanBuilder.and(board.group.id.eq(groupId)))
                .orderBy(board.createdDateTime.asc())
                .limit(pageSize)
                .fetch();

    }
}
