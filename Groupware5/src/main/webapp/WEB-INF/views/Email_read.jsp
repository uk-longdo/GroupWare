<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Email</title>
<link href="./resources/css/styles.css" rel="stylesheet" />
<style type="text/css">
#tests{
	
	display: flex;
	justify-content: space-between;
	padding : 0.3rem;
}
hr{
	background-color: white;

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
			<td style=" padding-bottom: 0px; border-top:0px;">
				<span style="padding-left:0.7rem; font-size:1.7rem;">${email_re.email_title }</span>
			</td>
		</tr>
		
		<tr>
			<td style=" padding-top: 0px; border-top:0px; font-size:0.8rem;">
				<span style="padding-left:0.7rem;">${email_re.user_name }</span>
				<span style="padding-left:0.7rem;">${email_re.email_day }</span>
			</td>
		</tr>
		
		<tr>
			<td>
				<div style="min-height:300px; padding-left:0.7rem;">${email_re.email_content }</div>
			</td>
		</tr>
		<tr>
			<td></td>
		</tr>	
</table>

<div id="tests">
	<div>
		
		
	</div>

</div>

	<div id="tests">
		<div>
			<input class="btn btn-dark" type="button" value="목록" onclick = "location.href = 'Email_list?email_re=${email_re.email_re}'">
		</div>
		<div>
			<input class="btn btn-dark" type="button" value="메일보내기" onclick = "location.href = 'Email_insert'">
			<input class="btn btn-dark" type="button" value="메일삭제" onclick = "location.href = 'Email_delete?email_idx=${email_re.email_idx}&email_re=${email_re.email_re}'">	
		</div>
	</div>

</main>
</div>
</div>
</div>

</body>
</html>