package com.spinrgmvc.board.domain;

import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Alias("boardV0")
public class BoardVO {
    private int seq;
    private String title;
    private String content;
    private String writer;
    private int password;
    private Timestamp regDate;
    private int cnt;

    public BoardVO() { }

    public BoardVO(String title, String content, String writer, int password) {
        super();
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
        this.cnt = 0;
    }
}
