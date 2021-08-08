package com.spinrgmvc.board.dao;

import com.spinrgmvc.board.domain.BoardVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDaoMyBatis implements BoardDao {
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public void setSqlMapClient(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public List<BoardVO> list() {
        return sqlSessionTemplate.selectList("list");
    }

    @Override
    public int delete(BoardVO boardVO) {
        return sqlSessionTemplate.delete("delete", boardVO);
    }

    @Override
    public int deleteAll() {
        return sqlSessionTemplate.delete("deleteAll");
    }

    @Override
    public int update(BoardVO boardVO) {
        return sqlSessionTemplate.update("update", boardVO);
    }

    @Override
    public int insert(BoardVO boardVO) {
        return sqlSessionTemplate.insert("insert", boardVO);
    }

    @Override
    public BoardVO select(int seq) {
        return (BoardVO) sqlSessionTemplate.selectOne("select", seq);
    }

    @Override
    public int updateReadCount(int seq) {
        return sqlSessionTemplate.update("updateCount", seq);
    }
}
