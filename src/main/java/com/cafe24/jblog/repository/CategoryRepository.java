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

    public CategoryVO create( CategoryVO vo ) {
	if ( sqlSession.insert( "category.insert", vo ) == 0 ) {
	    return null;
	}
	return vo;
    }

    public CategoryVO read( CategoryVO vo ) {
	return sqlSession.selectOne( "category.selectByNo", vo );
    }

    public List<CategoryVO> readAll( String id ) {
	return sqlSession.selectList( "category.selectAllById", id );
    }

    public boolean delete( CategoryVO vo ) {
	return (sqlSession.delete( "category.deleteByNo", vo ) == 1);
    }

    public boolean update( Long no ) {
	return (sqlSession.update( "category.updateByNo", no ) == 1);
    }
}
