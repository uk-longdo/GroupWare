<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./resources/css/styles.css" rel="stylesheet" />
<style>
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
	<table class="table table-dark">
			<tr>
				<td style=" padding-bottom: 0px; border-top:0px;"><span style="padding-left:0.7rem; font-size:1.7rem;">${notice2.notice_title}</span></td>
			</tr>
			<tr>
				<td style=" padding-top: 0px; border-top:0px; font-size:0.8rem;">
					<span style="padding-left:0.7rem;">${notice2.user_name}</span>
					<span style="padding-left:0.7rem;">${notice2.notice_date}</span>
					
				</td>
			</tr>
			<tr>
				<td>
				<div style="min-height:300px; padding-left:0.7rem;">${notice2.notice_content}</div>
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
	</table>
	<div id="tests">
		<div>
			<input class="btn btn-dark" type="button" value="목록" onclick = "location.href = 'Gw_Noticelist?page=${page}'">
		</div>
		<div>
			<c:choose>
				<c:when test="${sessionScope.user_rank eq 4}">
				<input class="btn btn-dark" type="button" value="글쓰기" onclick = "location.href = 'Gw_Noticeinsert'">
				<input class="btn btn-dark" type="button" value="수정" onclick = "location.href = 'Gw_Noticeupdatepage?notice_idx=${notice2.notice_idx}'">
				<input class="btn btn-dark" type="button" value="삭제" onclick = "location.href = 'Gw_Noticedelet?notice_idx=${notice2.notice_idx}'">
				</c:when>
			</c:choose>	
		</div>
	</div>
	
</main>
</div>
</div>
</div>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

</script>
</body>
</html>