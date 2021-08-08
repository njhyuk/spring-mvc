package com.spinrgmvc.board.dao;

import com.spinrgmvc.board.domain.BoardVO;

import java.util.List;

public interface BoardDao {
    public abstract List<BoardVO> list();

    public abstract int delete(BoardVO boardVO);

    public abstract int deleteAll();

    public abstract int update(BoardVO boardVO);

    public abstract int insert(BoardVO boardVO);

    public abstract BoardVO select(int seq);

    public abstract int updateReadCount(int seq);
}
