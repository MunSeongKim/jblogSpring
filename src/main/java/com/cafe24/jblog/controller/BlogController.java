package com.cafe24.jblog.controller;

import java.util.List;

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

@Controller
@RequestMapping( "/blog" )
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostService postService;
    
    @RequestMapping( "/{uid}" )
    public String main( @PathVariable( "uid" ) String userId, Model model ) {
	BlogVO blogVo = blogService.getBlogInfo( userId );
	List<CategoryVO> categories = categoryService.getAllCategories( userId );
	List<PostVO> posts = postService.getAllPosts( userId );
	model.addAttribute( "blog", blogVo );
	model.addAttribute( "categories", categories );
	model.addAttribute( "posts", posts );
	return "blog/main";
    }
}
