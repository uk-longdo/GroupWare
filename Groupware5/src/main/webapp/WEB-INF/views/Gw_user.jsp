<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./resources/css/styles.css" rel="stylesheet" />
<style type="text/css">


td{
	text-align: center;
	vertical-align : middle;
}

.test {
	text-align: left;
	
}
input {
	background-color: transparent;
	color: white;
}

</style>
	
</head>
<body>
<jsp:include page="gw_main.jsp"></jsp:include>
<div class="p-3 mb-2 bg-dark text-white" >
<div id="layoutSidenav">
<div id="layoutSidenav_content">

<main>

<form action="insert_user" method="post" enctype="multipart/form-data">

<div class="test">

<table class="table table-dark table-striped" id="tabl" border="1">
	<tr>
		<td >사원이름</td>
		<td class="test">
			<input type="text"  name="user_name"> 
		</td>
	</tr>
	
	<tr>
		<td >비밀번호</td>
		<td class="test">
	    	<input type="password"  name="user_pw" aria-label="Sizing example input">
		</td>
	</tr>
	

	<tr>
		<td>사원부서</td>
		<td class="test">
			<select name="team_idx">
			<option value="">선택</option>
			<option>=======</option>
			<c:forEach var="team" items="${team}">
			<option value="${team.team_idx}">${team.team_name}</option>
			</c:forEach>
		</select>
		</td>
	</tr>
	
	<tr>
		<td>사원직급</td>
		<td class="test">
			<select name="position_idx">
			<option>선택</option>
			<option>=======</option>
			<c:forEach var="position" items="${position}">
			<option value="${position.position_idx}">${position.position_name}</option>
			</c:forEach>
		</select>
		</td>
	</tr>
	<tr >
		<td >입사일</td>
		<td class="test">
			<input type="date" name="user_day">
		</td>
	</tr>
	
	<tr>
		<td >사원 전화번호</td>
		<td class="test">
		    <input type="tel" name="user_tel">	 
		</td>
	</tr>
	
	<tr>
		<td >사원 이메일</td>
		<td class="test">
		    <input type="email"  name="user_email">	  
		</td>
	</tr>
		
	<tr>
		<td >사원 주소</td>
		<td class="test">
		    <input type="text" name="user_add">
		</td>
	</tr>
	
	<tr>
		<td>사진등록</td>
		<td class="test">
			<input type="file" name="user_imgname">
		</td>

	</tr>
		
	<tr>
		<td colspan="2">
			<input type="submit" value="등록">
			<input type="reset" value="취소">
		</td>	
	</tr>

</table>
</div>
</form>

</main>
</div>
</div>
</div>
</body>
</html>