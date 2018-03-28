package com.cafe24.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.UserVO;

@Repository
public class UserRepository {
    @Autowired
    private SqlSession sqlSession;

    public boolean create( UserVO vo ) {
	int count = sqlSession.insert( "user.insert", vo );
	return count == 1;
    }

    public UserVO readById( String id ) {
	return sqlSession.selectOne( "user.selectById", id );
    }

    public UserVO readByIdAndPassword( UserVO vo ) {
	return sqlSession.selectOne( "user.selectByIdAndPassword", vo );
    }

    // public boolean deleteById( String id ) {
    // boolean result = false;
    // Connection conn = null;
    // PreparedStatement pstmt = null;
    //
    // try {
    // conn = super.getConnection();
    // String sql = "DELETE FROM user WHERE id = ?";
    // pstmt = conn.prepareStatement( sql );
    //
    // pstmt.setString( 1, id );
    // int count = pstmt.executeUpdate();
    //
    // result = (count == 1);
    // } catch ( SQLException e ) {
    // e.printStackTrace();
    // } finally {
    // super.closeConnection( conn, pstmt, null );
    // }
    //
    // return result;
    // }
}
