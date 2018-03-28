<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="header">
	<h1><a href="${pageContext.servletContext.contextPath }/blog/${blog.userId}">${blog.title}</a></h1>
	<ul>
		<c:if test='${empty authUser }'>
		<li><a href="${pageContext.servletContext.contextPath }/user/login">로그인</a></li>
		</c:if>
		<c:if test='${not empty authUser }'>
		<li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a></li>
		</c:if>
		<c:if test='${not empty authUser and authUser.id eq blog.userId}'>
		<li><a href="${pageContext.servletContext.contextPath }/blog/admin/${blog.userId}">블로그 관리</a></li>
		</c:if>
		<li><a href="${pageContext.servletContext.contextPath }">메인</a>
	</ul>
</div>