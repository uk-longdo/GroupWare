<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link href="./resources/css/styles.css" rel="stylesheet" />
<style type="text/css">
tr:hover{
	cursor: pointer;
	color: black;
}

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
		<table class="table table-dark table-striped">
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>날짜</td>
				<td>조회수</td>
			</tr>
			<!-- 게시글 셀렉트 -->
			<c:forEach var="notice" items="${notice }">
				<tr onclick="location.href='Gw_Noticeread?notice_idx=${notice.notice_idx}&page=${pageck}'">
					<td>${notice.notice_idx}</td> 
					<td>${notice.notice_title}</td>
					<td>${notice.user_name}</td>
					<td>${notice.notice_date}</td>
					<td>${notice.notice_count}</td>
				</tr>
			</c:forEach>
		</table>
		<div id="tests">
		<c:if test="${sessionScope.user_rank eq 4}">
			<input type="button" onclick="location.href='Gw_Noticeinsert'" value="글쓰기"> 
		</c:if>
		</div>
		<div style="width: 100%; text-align: center; padding: 1rem;">
		<c:if test="${prev == true}">
			<a href="Gw_Noticelist?page=${startPage-1}">이전</a>
		</c:if>
		<c:forEach var="page" begin="${startPage}" end="${endPage}">
		<c:choose>
			<c:when test="${page eq pageck}">
				<span style="font-weight: bold;">${page}</span>
			</c:when>
			<c:otherwise>
				<a href="Gw_Noticelist?page=${page}">${page}</a>
			</c:otherwise>
		</c:choose>
		</c:forEach>
		<c:if test="${next == true and endPage > 0}">
			<a href="Gw_Noticelist?page=${endPage+1}">다음</a>
		</c:if>
		</div>

</main>
</div>
</div>
</div>
</body>
</html>