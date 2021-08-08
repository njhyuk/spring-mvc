package com.spinrgmvc.board.service;

import com.spinrgmvc.board.domain.BoardVO;

import java.util.List;

public interface BoardService {
    public abstract List<BoardVO> list();

    public abstract int delete(BoardVO boardVO);

    public abstract int edit(BoardVO boardVO);

    public abstract void write(BoardVO boardVO);

    public abstract BoardVO read(int seq);
}
