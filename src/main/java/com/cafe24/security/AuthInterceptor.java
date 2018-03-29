package com.cafe24.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.vo.UserVO;
import com.cafe24.security.Auth.Role;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler )
	    throws Exception {
	// 1. Handler 종류 확인
	if ( handler instanceof HandlerMethod == false ) {
	    return true;
	}
	// 2. Type Casting
	HandlerMethod handlerMethod = (HandlerMethod) handler;
	// 3. get @Auth
	Auth authMethod = handlerMethod.getMethodAnnotation( Auth.class );
	// Class의 @Auth를 가져옴
	Auth authClass = handlerMethod.getBeanType().getAnnotation( Auth.class );
	// 4. method, class에 @Auth가 없는 경우
	if ( authMethod == null && authClass == null ) {
	    return true;
	}
	// 5. @Auth가 있는 경우, 인증여부 체크
	HttpSession session = request.getSession();
	if ( session == null ) {
	    response.sendRedirect( request.getContextPath() + "/user/login" );
	    return false;
	}
	UserVO authUser = (UserVO) session.getAttribute( "authUser" );
	if( authUser == null ){
	    response.sendRedirect( request.getContextPath() + "/user/login" );
	    return false;
	}
	
	// class에 @Auth가 없는 경우
	if ( authClass == null ) {
	    return true;
	}
	
	// 인증되었을 경우, 권한 체크
	if( authClass.role() == Role.ADMIN ){
	    //--- The way to get from URL path(Using PathVariable)
	    Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
	    String blogId = (String)pathVariables.get( "bid" );
	    //--- The way to parse to url
	    // String requestUrl = request.getRequestURI();
	    // String blogId = requestUrl.substring(requestUrl.lastIndexOf("/")+1, requestUrl.length());
	    if( !blogId.equals(authUser.getId()) ){
		System.out.println( authUser.getId() );
		response.sendRedirect( request.getContextPath() + "/" + blogId);
		return false;
	    }
	}
	// 6. 접근 허가
	return true;
    }
}
