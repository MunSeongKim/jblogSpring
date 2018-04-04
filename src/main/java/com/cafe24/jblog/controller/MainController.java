package com.cafe24.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.vo.BlogVO;

@Controller
public class MainController {
    @Autowired
    private BlogService blogService;

    @RequestMapping( { "", "/" } )
    public String main( @RequestParam( value = "k", required = false ) String keyword, Model model ) {
	List<BlogVO> blogs = blogService.getBlogs( keyword );
	
	model.addAttribute( "blogs", blogs );
	return "main/index";
    }
}
