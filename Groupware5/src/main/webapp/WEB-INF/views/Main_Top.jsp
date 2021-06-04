<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${sessionScope.user_rank eq 4}">
			${sessionScope.user_name}사원 관리자 로그인을 하셨습니다!
<div id="wrap">
	<span><a href="Gw_user">사원 등록</a></span>
	<span><a href="Gw_user_list">사원 관리</a></span>
	<span><a href="work_userlist">근태 관리</a></span>
	<span><a href="Email_list?email_re=${sessionScope.user_idx}">전자우편</a></span>		
	

	<span>
		<a href="logout">
			로그아웃
		</a>
	</span>
</div>
</c:if>
<c:if test="${sessionScope.user_rank ne 4}">
			${sessionScope.user_name}사원님 반갑습니다.

<div>
	<span>
		<a href="work_user">
			<input type="hidden" value="${sessionScope.user_idx}" name="user_idx">
			 근태 확인		
		</a>
	</span>
	
	
	<span><a href="Email_list?email_re=${sessionScope.user_idx}">전자우편</a></span>		
	<span>
	
		<a href="logout">
			로그아웃
		</a>
	</span>
</div>
</c:if>
</body>
</html>