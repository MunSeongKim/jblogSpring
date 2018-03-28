package com.cafe24.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.CategoryVO;

@Repository
public class CategoryRepository {
    @Autowired
    private SqlSession sqlSession;
    
    public boolean create(CategoryVO vo){
	return (sqlSession.insert("category.insert", vo) == 1);
    }
    
    public List<CategoryVO> readAll(String id){
	return sqlSession.selectList( "category.selectAllById", id );
    }
}
