package com.cafe24.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.PostRepository;
import com.cafe24.jblog.vo.PostVO;
import com.cafe24.pager.Pager;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    
    public List<PostVO> getAllPosts( String id, Long categoryNo, Pager pager ) {
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("start", pager.getStartLimitIndex());
	map.put("count", pager.getPostCount());
	if( categoryNo != null ) {
	    map.put("no", categoryNo);
	    return postRepository.readAllByCategory( map );
	}
	map.put("id", id);
	return postRepository.readAll( map );
    }

    public PostVO getPost( String id, Integer pageNo, Long categoryNo, Long postNo ) {
	Map<String, Object> map = new HashMap<String, Object>();
	// 처음 블로그 접속
	if( postNo == null ) {
	    return postRepository.readAtLast( id );
	}

	// 전체 카테고리의 포스트 글 보기 
	if ( categoryNo == null ) {
	    map.put( "userId", id );
	    map.put( "postNo", postNo );
	    map.put ("pageNo", pageNo );
	    return postRepository.readAtAll( map );
	}
	
	// 카테고리 선택 -> 카테고리 별 첫 화면
	if ( postNo == 0 ) {
	    map.put( "userId", id );
	    map.put( "categoryNo", categoryNo );
	    return postRepository.readAtLastByCategory( map );
	}

	// 카테고리를  조회하고 특정 포스트를 조회했을 때
	map.put( "userId", id );
	map.put( "categoryNo", categoryNo );
	map.put( "postNo", postNo );
	return postRepository.readAtAllByCategory( map );
    }

    public void writePost( PostVO vo ) {
	postRepository.create( vo );
    }
}
