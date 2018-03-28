package com.cafe24.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.vo.BlogVO;
import com.cafe24.security.Auth;
import com.cafe24.security.Auth.Role;

@Controller
@RequestMapping("/blog/admin")
@Auth(role=Role.ADMIN)
public class BlogAdminController {
    @Autowired
    private BlogService blogService;
    
    @RequestMapping(value="/{uid}", method=RequestMethod.GET )
    public String basic(@PathVariable("uid") String userId, Model model) {
	BlogVO blogVo = blogService.getBlogInfo( userId );
	model.addAttribute( "blog", blogVo );
	return "blog/admin/basic";
    }
    /*
    @RequestMapping(value="/{uid}", method=RequestMethod.GET )
    public String category(@PathVariable("uid") String userId, Model model) {
	BlogVO blogVo = blogService.getBlogInfo( userId );
	model.addAttribute( "blog", blogVo );
	return "blog/admin/basic";
    }
    
    @RequestMapping(value="/{uid}", method=RequestMethod.GET )
    public String write(@PathVariable("uid") String userId, Model model) {
	BlogVO blogVo = blogService.getBlogInfo( userId );
	model.addAttribute( "blog", blogVo );
	return "blog/admin/basic";
    }*/
}
