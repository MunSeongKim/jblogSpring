<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<a href="${pageContext.servletContext.contextPath }"><h1 class="logo">JBlog</h1></a>

<ul class="menu">
	<c:choose>
		<c:when test="${ empty authUser }">
			<li><a href="${pageContext.servletContext.contextPath }/user/login">로그인</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/user/join">회원가입</a></li>
		</c:when>
		<c:when test="${ param.k ne null }">
			<li>${ authUser.name }님 환영합니다.</li>
			<li><a href="${pageContext.servletContext.contextPath }/${authUser.id}?k=${param.k}&t=${param.t}">내블로그</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a></li>
		</c:when>
		<c:otherwise>
			<li>${ authUser.name }님 환영합니다.</li>
			<li><a href="${pageContext.servletContext.contextPath }/${authUser.id}">내블로그</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a></li>
		</c:otherwise>
	</c:choose>
</ul>