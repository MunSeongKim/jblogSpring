<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${ pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<script>
$(function() {
	var id = $('#login-id');
	var pw = $('#login-pw');
	
	$('#btn-login').click(function() {
		if( id.val() === "" ){
			alert("아이디를 입력해주세요.");
			id.focus();
			return false;
		}
		
		if( pw.val() === "" ){
			alert("비밀번호를 입력해주세요.");
			pw.focus();
			return false;
		}
		
		return true;
	});
	
});
</script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/main/header.jsp" />
		<form class="login-form" action="${pageContext.servletContext.contextPath }/user/login/auth" method="POST">
      		<label>아이디</label> <input id="login-id" type="text" name="id" value="${id}">
      		<label>패스워드</label> <input id="login-pw" type="password" name="password">
      		<input id="btn-login" type="submit" value="로그인">
		</form>
		<c:if test="${id ne null}">
		<p>아이디 또는 비밀번호가 일치하지 않습니다.</p>
		</c:if>		
	</div>
</body>
</html>
