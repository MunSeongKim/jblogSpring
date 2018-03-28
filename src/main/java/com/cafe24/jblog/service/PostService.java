package com.cafe24.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.PostRepository;
import com.cafe24.jblog.vo.PostVO;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    
    public List<PostVO> getAllPosts(String id){
	return postRepository.readAll( id );
    }
    
}
