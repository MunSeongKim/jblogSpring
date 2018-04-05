package com.cafe24.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.BlogVO;

@Repository
public class BlogRepository {
    @Autowired
    private SqlSession sqlSession;

    public boolean create( BlogVO vo ) {
	return (sqlSession.insert( "blog.insert", vo ) == 1);
    }

    public BlogVO read( String id ) {
	BlogVO result = sqlSession.selectOne( "blog.selectById", id );
	if( result == null ){
	    throw new RuntimeException("해당 블로그는 없습니다.");
	}
	return result;
    }
    
    public List<Map<String, Object>> readByKeyword(Map<?, ?> map){
	return sqlSession.selectList( "blog.selectAll", map);
    }

    public boolean update( BlogVO vo ) {
	return (sqlSession.update( "blog.update", vo ) == 1);
    }
}
