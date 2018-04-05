<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="header">
	<c:choose>
		<c:when test='${ param.k ne null }'>
			<h1><a href="${pageContext.servletContext.contextPath }/${blog.userId}?k=${param.k}&t=${param.t}">${blog.title}</a></h1>
		</c:when>
		<c:otherwise>
			<h1><a href="${pageContext.servletContext.contextPath }/${blog.userId}">${blog.title}</a></h1>	
		</c:otherwise>
	</c:choose>
	
	<ul>
		<c:if test='${empty authUser }'>
		<li><a href="${pageContext.servletContext.contextPath }/user/login">로그인</a></li>
		</c:if>
		<c:if test='${not empty authUser }'>
		<li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a></li>
		</c:if>
		<c:if test='${not empty authUser and authUser.id eq blog.userId}'>
		<li><a href="${pageContext.servletContext.contextPath }/${blog.userId}/admin/basic">블로그 관리</a></li>
		</c:if>
		<c:choose>
			<c:when test='${ param.k ne null }'>
				<li><a href="${pageContext.servletContext.contextPath }?k=${param.k}&t=${param.t}">메인</a>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.servletContext.contextPath }">메인</a></li>	
			</c:otherwise>
		</c:choose>
		
	</ul>
</div>