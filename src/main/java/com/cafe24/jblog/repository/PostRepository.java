package com.cafe24.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.PostVO;

@Repository
public class PostRepository {
    @Autowired
    private SqlSession sqlSession;
        
    public boolean create( PostVO vo ) {
	return (sqlSession.insert( "post.insert", vo ) == 1);
    }

    public List<PostVO> readAll( Map<?, ?> map  ) {
	return sqlSession.selectList( "post.selectAllById", map );
    }

    public List<PostVO> readAllByCategory( Map<?, ?> map ) {
	return sqlSession.selectList( "post.selectAllByCategory", map );
    }

    public PostVO readAtLast( String id ) {
	return sqlSession.selectOne( "post.selectAtLast", id );
    }

    public PostVO readAtAll( Map<?, ?> map ) {
	return sqlSession.selectOne( "post.selectAtAll", map );
    }

    public PostVO readAtLastByCategory( Map<?, ?> map ) {
	return sqlSession.selectOne( "post.selectAtLastByCategory", map );
    }

    public PostVO readAtAllByCategory( Map<?, ?> map ) {
	return sqlSession.selectOne( "post.selectAtAllByCategory", map );
    }
    
    public int readCount( Map<?, ?> map ){
	return sqlSession.selectOne( "post.selectCount", map );
    }

}
