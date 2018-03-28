package com.cafe24.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.service.CategoryService;
import com.cafe24.jblog.service.FileUploadService;
import com.cafe24.jblog.service.PostService;
import com.cafe24.jblog.vo.BlogVO;
import com.cafe24.jblog.vo.CategoryVO;
import com.cafe24.jblog.vo.PostVO;
import com.cafe24.security.Auth;
import com.cafe24.security.Auth.Role;

@Controller
@RequestMapping( "/{uid:(?!assets|logo).*}/admin" )
@Auth( role = Role.ADMIN )
public class BlogAdminController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostService postService;
    @Autowired
    private FileUploadService fileUploadService;
    
    @RequestMapping( value="/basic", method=RequestMethod.GET )
    public String basic( @PathVariable( "uid" ) String userId, Model model ) {
	BlogVO blogVo = blogService.getBlogInfo( userId );
	model.addAttribute( "blog", blogVo );
	return "blog/admin/basic";
    }
    
    @RequestMapping( value="/category", method=RequestMethod.GET )
    public String category( @PathVariable( "uid" ) String userId, Model model ) {
	BlogVO blogVo = blogService.getBlogInfo( userId );
	List<CategoryVO> categories = categoryService.getAllCategories( userId );
	model.addAttribute( "blog", blogVo );
	model.addAttribute( "categories", categories);
	return "blog/admin/category";
    }
    
    @RequestMapping( value="/write", method=RequestMethod.GET )
    public String write( @PathVariable( "uid" ) String userId, Model model ) {
	BlogVO blogVo = blogService.getBlogInfo( userId );
	List<CategoryVO> categories = categoryService.getAllCategories( userId );
	model.addAttribute( "blog", blogVo );
	model.addAttribute( "categories", categories);
	return "blog/admin/write";
    }
    
    @RequestMapping( value="/write", method=RequestMethod.POST )
    public String write( @PathVariable( "uid" ) String userId,
	    @ModelAttribute PostVO postVo ) {
	postService.writePost(postVo);
	return "redirect:/"+userId+"/admin/write";
    }
    
    @RequestMapping( value="/basic", method=RequestMethod.POST )
    public String update( @PathVariable( "uid" ) String userId,
	    @ModelAttribute BlogVO blogVo,
	    @RequestParam( "logo-image" ) MultipartFile multipartFile) {
	String imagePath = fileUploadService.restore( multipartFile );
	blogVo.setUserId(userId);
	blogVo.setImagePath( imagePath );
	blogService.modifyBlog(blogVo);
	
	return "redirect:/" + userId + "/admin/basic";
    }
}
