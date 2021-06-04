<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>

<link href="./resources/css/styles.css" rel="stylesheet" />
<style type="text/css">
#user_img{
	width: 100px;
	height: 100px;
	

}

</style>
</head>
<body>


<jsp:include page="gw_main.jsp"></jsp:include>
<div class="p-3 mb-2 bg-dark text-white" style="width: 100%">
<div id="layoutSidenav">
<div id="layoutSidenav_content">

<main>
<div class="test">
<table class="table table-dark table-striped" style="width: 100%">
	<tr>
		<td>선택박스</td>
		<td>사원번호</td>
		<td>사원이름</td>
		<td>부서</td>
		<td>직급</td>
		<td>입사일</td>
		<td>전화번호</td>
		<td>이메일</td>
		<td>주소</td>
		<td>이미지</td>
	
		
	</tr>

<c:forEach var="result" items="${result }">
	<tr>
		<td>
			<input type="checkbox" name="chek" id="chek" value="${result.user_idx }">
		</td>
		<td>${result.user_idx }</td>
		<td>${result.user_name }</td>
		<td>${result.team_name }</td>
		<td>${result.position_name }</td>
		<td>${result.user_day }</td>
		<td>${result.user_tel }</td>
		<td>${result.user_email }</td>
		<td>${result.user_add }</td>
		
		<td>
			<img id="user_img" src="resources/user_img/${result.user_imgname}">
		</td>
	</tr>
</c:forEach>
<tr>
	<td>
		<input type="button" value="회원수정" onclick="location.href='Gw_user_list_update'">
		<input type="button" value="회원삭제" onclick="chbox()">
	</td>

</tr>
	</table>
	</div>
	</main>

	</div>
	</div>
</div>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
	  function chbox(){	
	  	alert("테스트")
	  }
	
	</script>
	

</body>
</html>