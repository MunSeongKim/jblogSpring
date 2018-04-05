package com.cafe24.jblog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.jblog.service.BlogService;

@Controller
public class MainController {
    @Autowired
    private BlogService blogService;

    @RequestMapping( { "", "/" } )
    public String main(
	    @RequestParam( value = "k", required = false ) String keyword,
	    @RequestParam( value = "t", required = false ) String type,
	    Model model ) {
	
	
	if( keyword == null && type == null ) {
	    return "main/index";
	}
	
	List<Map<String, Object>> blogs = blogService.getBlogs( keyword, type );
	model.addAttribute( "blogs", blogs );
	return "main/index";
    }
}
