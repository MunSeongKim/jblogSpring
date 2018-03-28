package com.cafe24.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.PostVO;

@Repository
public class PostRepository {
    @Autowired
    private SqlSession sqlSession;
    
    public boolean create() {
	return false;
    }
    
    public List<PostVO> readAll(String id){
	return sqlSession.selectList("post.selectAllById", id);
    }
}
