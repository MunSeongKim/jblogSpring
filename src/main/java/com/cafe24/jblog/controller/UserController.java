package com.cafe24.jblog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.UserVO;

@Controller
@RequestMapping( "/user" )
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping( value = "/join", method = RequestMethod.GET )
    public String join(@ModelAttribute UserVO vo) {
	return "user/join";
    }

    @RequestMapping( value = "/join", method = RequestMethod.POST )
    public String join( @ModelAttribute @Valid UserVO vo, BindingResult result ) {
	if( result.hasErrors() ) {
	    List<ObjectError> list = result.getAllErrors();
	    for(ObjectError error: list){
		System.out.println( "Object Error: " + error );
	    }
	    return "user/join";
	}
	userService.join( vo );
	return "user/joinsuccess";
    }
    
    @RequestMapping( value = "/login", method = RequestMethod.GET )
    public String login() {
	return "user/login";
    }

}
