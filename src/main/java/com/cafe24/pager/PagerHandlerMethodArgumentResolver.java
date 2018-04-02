package com.cafe24.pager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PagerHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public Object resolveArgument( MethodParameter parameter, ModelAndViewContainer mavContainer,
	    NativeWebRequest webRequest, WebDataBinderFactory binderFactory ) throws Exception {
	if ( supportsParameter( parameter ) == false ) {
	    return WebArgumentResolver.UNRESOLVED;
	}
	
	// Request 객체 획득
	HttpServletRequest request = webRequest.getNativeRequest( HttpServletRequest.class );
	// WebApplicationContext 획득
	WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext( request.getServletContext() );
	// Numerable 구현 객체 획득
	Numerable numerable = ac.getBean( Numerable.class);
	Pager pager = new Pager();
	// pager에 numerable 삽입
	pager.setNumerable(numerable);

	return pager;
    }

    @Override
    public boolean supportsParameter( MethodParameter parameter ) {
	// 1. Parameter에 @Page 여부 검사
	Page page = (Page) parameter.getParameterAnnotation( Page.class );
	// 2. @Page가 없는 경우
	if ( page == null ) {
	    return false;
	}
	// 3. @Page의 타입 확인
	if ( parameter.getParameterType().equals( Pager.class ) == false ) {
	    return false;
	}

	return true;
    }

}
