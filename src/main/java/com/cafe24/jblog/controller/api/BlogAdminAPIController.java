package com.cafe24.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.jblog.dto.JSONResult;
import com.cafe24.jblog.service.CategoryService;
import com.cafe24.jblog.vo.CategoryVO;
import com.cafe24.security.Auth;
import com.cafe24.security.Auth.Role;

@Controller( "BlogAPIController" )
@RequestMapping( "/{uid:(?!assets|logo).*}/api/blog" )
@Auth( role = Role.ADMIN )
public class BlogAdminAPIController {
    @Autowired
    private CategoryService categoryService;

    @ResponseBody
    @RequestMapping( value = "/category", method = RequestMethod.POST )
    public JSONResult addCategory( @RequestBody CategoryVO categoryVo ) {
	// @RequestBody Map<String, Object> requestData) {
	CategoryVO category = categoryService.addition( categoryVo );
	return JSONResult.success( category );
    }

    @ResponseBody
    @RequestMapping( value = "/category", method = RequestMethod.DELETE )
    public JSONResult removeCategoty( @RequestBody CategoryVO categoryVo ) {
	if ( categoryService.remove( categoryVo ) ) {
	    return JSONResult.success( categoryVo.getNo() + " is removed." );
	}
	return JSONResult.fail( categoryVo.getNo() + " is failed to remove" );
    }
}
