package com.cafe24.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Map<String, Object>> getBlogs( String keyword, String type ) {
	Map<String, Object> map = new HashMap<String, Object>();
	map.put( "keyword", keyword );
	map.put( "type", type );
	return blogRepository.readByKeyword( map );
    }

    public void modifyBlog( BlogVO vo ) {
	blogRepository.update( vo );
    }
}
