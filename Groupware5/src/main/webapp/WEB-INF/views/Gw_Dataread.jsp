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
				<td style=" padding-bottom: 0px; border-top:0px;"><span style="padding-left:0.7rem; font-size:1.7rem;">${dataroom_read.data_title}</span></td>
			</tr>
			<tr>
				<td style=" padding-top: 0px; border-top:0px; font-size:0.8rem;">
					<span style="padding-left:0.7rem;">${dataroom_read.user_name}</span>
					<span style="padding-left:0.7rem;">${dataroom_read.data_date}</span>
					
				</td>
			</tr>
			<tr>
				<td>
				<div style="min-height:300px; padding-left:0.7rem;">${dataroom_read.data_content}</div>
				</td>
			</tr>
			<tr>
				<td style="padding-left:0.7rem;"> <span>첨부파일 : </span> <a class="card-text" name="filedownload">${dataroom_read.data_up}</a></td>
			</tr>
	</table>
	<div id="tests">
		<div>
			<input class="btn btn-dark" type="button" value="목록" onclick = "location.href = 'Gw_Datapage?page=${page}'">
		</div>
		<div>
			<input class="btn btn-dark" type="button" value="글쓰기" onclick = "location.href = 'Gw_Datainsertpage'">
			<input class="btn btn-dark" type="button" value="수정" onclick = "location.href = 'Gw_Dataupdatepage?data_idx=${dataroom_read.data_idx}'">
			<input class="btn btn-dark" type="button" value="삭제" onclick = "location.href = 'Gw_Datadelete?data_idx=${dataroom_read.data_idx}'">
		</div>
	</div>
	
</main>
</div>
</div>
</div>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$('a[name=filedownload]').click(function(){
		var data_uppath = "${dataroom_read.data_uppath}";
		var data_up = "${dataroom_read.data_up}";
		var ck = confirm("파일을 다운로드하시겠습니까?");
		
		if(!ck){
			return;
		}else{
			location.href='dataroom_filedownload?data_up='+data_up+'&data_uppath='+data_uppath;
			alert(data_up+"다운로드 완료");
		}
		
                
   		
    
	});
</script>
</body>
</html>