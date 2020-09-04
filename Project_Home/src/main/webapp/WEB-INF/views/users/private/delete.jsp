<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<p>
		<strong>${param.id }</strong>님 탈퇴처리되셨습니다.
		안녕히가십시오.
		<a href="${pageContext.request.contextPath}/home.do">홈으로</a>
	</p>
</div>
</body>
</html>