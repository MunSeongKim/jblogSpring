package com.cafe24.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.service.CategoryService;
import com.cafe24.jblog.service.PostService;
import com.cafe24.jblog.vo.BlogVO;
import com.cafe24.jblog.vo.CategoryVO;
import com.cafe24.jblog.vo.PostVO;
import com.cafe24.pager.Page;
import com.cafe24.pager.Pager;
import com.cafe24.security.Auth;

@Controller
@RequestMapping( "/{uid:(?!assets|logo).*}" )
@Auth
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostService postService;
    
    @RequestMapping( { "", "/{path1}/{path2}", "/{path1}/{path2}/{path3}" } )
    public String main( @PathVariable( "uid" ) String userId, @PathVariable( "path1" ) Optional<Long> path1,
	    @PathVariable( "path2" ) Optional<Long> path2, @PathVariable( "path3" ) Optional<Long> path3,
	    @Page Pager pager,
	    Model model ) {
	Long pageNo = 1L;
	Long categoryNo = null;
	Long postNo = null;
	
	if( path3.isPresent() ){
	    postNo = path3.get();
	    pageNo = path2.get();
	    categoryNo = path1.get();
	} else if( path2.isPresent() ) {
	    postNo = path2.get();
	    pageNo = path1.get();
	}
	
	Map<String, Object> dataForCount = new HashMap<String, Object>(); 
	dataForCount.put("userId", userId);
	dataForCount.put("categoryNo", categoryNo);
	pager.setPager(pageNo.intValue(), dataForCount);
	
	BlogVO blogVo = blogService.getBlogInfo( userId );
	List<CategoryVO> categories = categoryService.getAllCategories( userId );
	List<PostVO> posts = postService.getAllPosts( userId, categoryNo, pager );
	PostVO post = postService.getPost( userId, pageNo, categoryNo, postNo );
	
	model.addAttribute( "blog", blogVo );
	model.addAttribute( "categories", categories );
	model.addAttribute( "posts", posts );
	model.addAttribute( "post", post );
	model.addAttribute( "categoryNo", categoryNo);
	model.addAttribute( "pager", pager);
	return "blog/main";
    }

}
