<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="navigation">
	<h2>카테고리</h2>
	<ul>
		<li><a href="${pageContext.servletContext.contextPath }/${blog.userId}">전체</a>
		<c:forEach items="${ categories }" var="category" varStatus="status">
			<li><a href="${pageContext.servletContext.contextPath }/${blog.userId }/${category.no}/0?p=1">${category.name }</a></li>
		</c:forEach>
	</ul>
</div>