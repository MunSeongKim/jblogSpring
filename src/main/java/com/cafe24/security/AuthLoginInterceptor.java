package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.UserVO;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler )
	    throws Exception {
	String id = request.getParameter( "id" );
	String password = request.getParameter( "password" );

	UserVO vo = new UserVO();
	vo.setId( id );
	vo.setPassword( password );

//	ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext( request.getServletContext() );
//	UserService userService = ac.getBean( UserService.class );

	UserVO authUser = userService.getUserForLogin( vo );

	if ( authUser == null ) {
	    request.setAttribute( "id", id );
	    request.setAttribute( "result", "fail" );
	    request.getRequestDispatcher( "/WEB-INF/views/user/login.jsp" ).forward( request, response );
	    return false;
	}

	HttpSession session = request.getSession( true );
	session.setAttribute( "authUser", authUser );
	response.sendRedirect( request.getContextPath() + "/" + authUser.getId() );
	return false;
    }
}
