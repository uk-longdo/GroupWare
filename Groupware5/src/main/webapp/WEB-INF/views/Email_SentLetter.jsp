<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sent mail</title>
<link href="./resources/css/styles.css" rel="stylesheet" />
<style type="text/css">
tr:hover{
	cursor: pointer;
	color: blue;
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
<div class="p-3 mb-2 bg-dark text-white" style="height: 100%">
<div id="layoutSidenav">
<div id="layoutSidenav_content">

<main>
<h1>${sessionScope.user_name}님 보낸메일함</h1>



<table class="table table-dark table-striped">
<tr>
		
		<td>선택</td>
		<td>번호</td>
	
		<td>받는사람</td>
		
		<td>제목</td>
		<td>시간</td>
		<td>읽은 시간</td>
	</tr>
	
<c:forEach var="email3" items="${email3 }">
	<tr>
		<td>
			<input type="checkbox" name="chk_box" value="${email3.email_idx}" id="chack_box">
		</td>
		
		<td onclick="location.href='Email_read_sent?email_idx=${email3.email_idx}'">${email3.email_idx}</td>
		<td onclick="location.href='Email_read_sent?email_idx=${email3.email_idx}'">${email3.re_name}</td>
		<td onclick="location.href='Email_read_sent?email_idx=${email3.email_idx}'">${email3.email_title}</td>
		<td onclick="location.href='Email_read_sent?email_idx=${email3.email_idx}'">${email3.email_day}</td>
	
	<%-- 
	<c:choose>
		<c:when test="${email_readtime eq null}">
				<td>읽지않음</td>
		</c:when>
	</c:choose>
	 --%>	
		<td onclick="location.href='Email_read_sent?email_idx=${email3.email_idx}'">${email3.email_readtime}</td>	
	
	
		
	</tr>

</c:forEach>
	
</table>

<div id="tests">
	<div>
		<c:if test="${prev == true }">
			<a href="Email_SentLetter?page=${startPage-1 }&user_idx=${sessionScope.user_idx}">이전</a>
		</c:if>
		
		<c:forEach var="page" begin="${startPage }" end="${end_Page }">
			<c:choose>
				<c:when test="${page eq page_page }">
					<span style="font-weight: bold;">${page}</span>
				</c:when>
				
				<c:otherwise>
					<a href="Email_SentLetter?page=${page}&user_idx=${sessionScope.user_idx}">${page }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<c:if test="${next == true and end_Page > 0 }">
			<a href="Email_SentLetter?page=${end_Page+1}&user_idx=${sessionScope.user_idx}">다음</a>
		</c:if>
	</div>

</div>

	<input type="button" value="메일삭제" onclick="submitCheck()">
	<input type="button" value="메일쓰기" onclick="location.href='Email_insert'"> 
	<input type="button" value="받은메일함" onclick="location.href='Email_list?email_re=${sessionScope.user_idx}'"> 

</main>
</div>
</div>
</div>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
	function submitCheck(){
		var box = $("input[name=chk_box]");
		var boxnum = box.length;
		var checkCnt = 0;
		
		
		for(var i=0; i<boxnum; i++){
			if(box[i].checked == true){
			
				checkCnt++;	
			}
		}
		if(checkCnt == 0){
			alert("체크박스를 선택해주세요");
			return false;
		} 
		
		var eamil_idxs="";
		$("input[name=chk_box]:checked").each(function(i) {
			if(i == checkCnt-1){
				eamil_idxs += $(this).val();
				
			}else{
				eamil_idxs += $(this).val()+",";
			}
		});
			var user_idx = "${sessionScope.user_idx}";
			//user_idx 를 변수에 담아서 
			var email_idx = eamil_idxs;
			alert(email_idx);
			
			
			  location.href='email_delete_sent_read?eamil_idxs='+eamil_idxs+'&user_idx='+user_idx;
			  //로케이션으로 보낼 때 값을 담아서 보냄
		}
	</script>
	
</body>
</html>