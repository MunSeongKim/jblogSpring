<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
	$("#btn-checkid").click(function() {
		var id = $("#blog-id").val();
		if( id == "") return ;
		
		$.ajax({
			url: '${ pageContext.servletContext.contextPath }/api/user/checkid?id=' + id,
			type: 'GET',
			data: "",
			dataType: 'json',
			success: function( response, status, xhr ) {
				console.log( response );
				if( response.result != "success" ) {
					console.log(response.message);
					return ;
				}
				
				if( response.data == "EXIST" ){
					alert(id + "(은)는 이미 사용중입니다.")
					$('#blog-id').val("").focus();
					return ;
				}
				$('#btn-checkid').hide();
				$('#img-checkid').show();
				
			},
			error: function( e, status, xhr ) {
				console.error("[" + status + "] " + e);
			}
		});
	});
	
	$('#blog-id').keydown(function() {
		if( $('#img-checkid').css("display") != "none" ){
			$('#img-checkid').hide();
			$('#btn-checkid').show();
		}
	});
	
	$('#btn-join').click(function() {
		if( $('#name').val() == "" || $('#blog-id').val() == "" ||
		    $('#password').val() == "" ) {
			alert("빈 항목이 있습니다.");
			return false;
		}
		
		if( !$('#agree-prov').is(':checked') ){
			alert("약관동의에 체크해주세요.");
			return false;
		}
		
		if( $('#img-checkid').css("display") == "none" ){
			alert("ID 중복체크를 해주세요.");	
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
		
		<form:form modelAttribute="userVO" id="join-form" class="join-form" name="joinForm" method="POST" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<form:input path="name" />
			<p style="padding: 0; text-align: left; color: red" >
				<form:errors path="name" />
			</p>
			
			<label class="block-label" for="blog-id">아이디</label>
			<form:input path="id" />
			<input id="btn-checkid" type="button" value="id 중복체크">
			<img id="img-checkid" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			<p style="padding: 0; text-align: left; color: red" >
				<form:errors path="id" />
			</p>
			
			<label class="block-label" for="password">패스워드</label>
			<form:password path="password" />
			<p style="padding: 0; text-align: left; color: red">
				<form:errors path="password" />
			</p>
			
			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input id="btn-join" type="submit" value="가입하기" />
		</form:form>
	</div>
</body>
</html>
