<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./resources/css/styles.css" rel="stylesheet" />
</head>
<body>


<jsp:include page="gw_main.jsp"></jsp:include>
<div class="p-3 mb-2 bg-dark text-white" >
<div id="layoutSidenav">
<div id="layoutSidenav_content">

<main>

<table class="table table-dark table-striped">
	<tr>
		<td>사원번호</td>
		<td>이름</td>
		<td>출근시간</td>
		<td>퇴근시간</td>
	</tr>




<c:forEach var="work" items="${work }">
	<tr>
		<td>${work.user_idx }</td>
		<td>${work.user_name }</td>
		<td>${work.work_on }</td>
		<td>${work.work_off }</td>
	</tr>

</c:forEach>
</table>
</main>
</div>
</div>
</div>
</body>
</html>