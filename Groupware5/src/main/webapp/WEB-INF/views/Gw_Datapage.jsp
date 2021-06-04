<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./resources/css/styles.css?ver=2" rel="stylesheet" />
<style type="text/css">
	#tests{
		
		display: flex;
		justify-content: space-between;
		padding : 0.3rem;
	}
	
</style>
</head>
<body>
<jsp:include page="gw_main.jsp"></jsp:include>
<div class="p-3 mb-2 bg-dark text-white" >
<div id="layoutSidenav">
<div id="layoutSidenav_content">

<main>
	<table class="table table-dark table-striped table-hover">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<c:forEach var="dataroom" items="${dataroom}">
		<tr onclick="location.href='Gw_Dataread?data_idx=${dataroom.data_idx}&page=${pageck}'">
			<td>${dataroom.data_idx}</td>
			<td>${dataroom.data_title}</td>
			<td>${dataroom.user_name}</td>
			<td>${dataroom.data_date}</td>
		
		</tr>
		</c:forEach>
</table>
	<div id="tests">
	<div>
	<c:if test="${prev == true}">
		<a href="Gw_Datapage?page=${startPage-1}">이전</a>
	</c:if>
	<c:forEach var="page" begin="${startPage}" end="${endPage}">
		<c:choose>
			<c:when test="${page eq pageck}">
				<span style="font-weight: bold;">${page}</span>
			</c:when>
			<c:otherwise>
				<a href="Gw_Datapage?page=${page}">${page}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${next == true and endPage > 0}">
			<a href="Gw_Datapage?page=${endPage+1}">다음</a>
	</c:if>
	</div>
	<div>
	<input type="button" value="글쓰기" onclick = "location.href = 'Gw_Datainsertpage'" class="btn btn-dark">
	</div>
	</div>
</main>
</div>
</div>
</div>
</body>
</html>