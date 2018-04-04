package com.cafe24.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogRepository;
import com.cafe24.jblog.vo.BlogVO;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public BlogVO getBlogInfo( String id ) {
	return blogRepository.read( id );
    }

    public List<BlogVO> getBlogs( String keyword ) {
	
	return blogRepository.readByKeyword( keyword );
    }

    public void modifyBlog( BlogVO vo ) {
	blogRepository.update( vo );
    }
}
