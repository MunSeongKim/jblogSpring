package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.jblog.repository.BlogRepository;
import com.cafe24.jblog.repository.CategoryRepository;
import com.cafe24.jblog.repository.UserRepository;
import com.cafe24.jblog.vo.BlogVO;
import com.cafe24.jblog.vo.CategoryVO;
import com.cafe24.jblog.vo.UserVO;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Transactional
    public void join( UserVO vo ) {
	if ( userRepository.create( vo ) ){
	    BlogVO blogVo = new BlogVO();
	    blogVo.setUserId(vo.getId());
	    blogVo.setTitle(vo.getName() + "'s blog");
	    blogVo.setImagePath("/assets/images/default.png");
	    blogRepository.create(blogVo);
	    CategoryVO categoryVo = new CategoryVO();
	    categoryVo.setName( "미분류" );
	    categoryVo.setDescription( "분류하지 않은 카테고리입니다." );
	    categoryVo.setPostCount( 0 );
	    categoryVo.setUserId( vo.getId() );
	    categoryRepository.create(categoryVo);
	    return ; 
	}
    }

    public UserVO getUserForLogin( UserVO vo ) {
	return userRepository.readByIdAndPassword( vo );
    }
    
    public String checkId( String id ) {
	if( userRepository.readById( id ) == null ) {
	    return "NOT_EXIST";
	}
	return "EXIST";
    }
}
