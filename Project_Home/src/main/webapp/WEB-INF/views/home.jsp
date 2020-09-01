<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home.jsp</title>
</head>
<body>
<div class="container">
	<c:choose>
		<c:when test="${empty id }">
			<a href="${pageContext.request.contextPath}/users/loginform.do">로그인</a>
			<a href="${pageContext.request.contextPath}/users/signup_form.do">회원가입</a>
		</c:when>
	</c:choose>
	<c:otherwise>
		<strong>${id }</strong>님 로그인 중입니다.
		<a href="">로그아웃</a>
	</c:otherwise>
</div>

<h1>Welcome to Inna's HomePage</h1>
<p>누추한 홈페이지에 어서오세요^^</p>
</body>
</html>