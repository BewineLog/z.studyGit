<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>회원 목록조회</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--   jQuery , Bootstrap CDN  -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   <link href="/css/custom.css" rel="stylesheet">
   
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
	
	<!--  CSS 추가 : 툴바에 화면 가리는 현상 해결 :  주석처리 전, 후 확인-->
<!-- 	<style> -->

<!--    	</style> -->
   	
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	 <script>
		var userId = "${user.userId}";
	
	</script>
	<script src="/javascript/custom.js" charset='utf-8'>
	</script>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<!-- <script src="../javascript/jquery-2.1.4.js" type="text/javascript"></script> -->
<script type="text/javascript">

function fncGetList(page) {
	$('#page').val(page);
	$('form').attr("method","POST").attr("action","/user/listUser").submit();
	
}

$(function () {
	$('button.btn:contains("검색")').on("click", function(){
		fncGetList(1);
	});
});

$(function(){
	$('.ct_list_pop td:nth-child(2)').on("click", function(){
		self.location = "/user/getUser?userId="+$(this).text().trim();
	});
});

$(function(){
	
	$('button.btn-default').on("click",function(){
		alert($(this).html().trim());
		fncGetList($(this).html().trim());
	});
});


$(function() {
	$('td:contains("탈퇴")').on("click", function(e){
		
// 		var userId = $('.ct_list_pop td:nth-child(3)').val();
		
		alert( $($(this).parent().children()[1]).text());
		
		$('input:hidden[name="removeUserId"]').val($($(this).parent().children()[1]).text());
		
		fncGetList(1);
		
	});
	
	alert($('.ct_list_pop td:nth-child(2)').html());
	
	$('.ct_list_pop td:nth-child(2)').css('color','blue');
	$('.ct_list_pop td:nth-child(5)').css('color','#4aa8d8');
	$('.ct_list_pop td:nth-child(6)').css('color','red');
// 	$('.ct_list_pop td:nth-child(3)').css('background-color','whiteSmoke');

	alert($('table[id="list"] tr:nth-child(even)'));
// 	$('table[id="list"] tr:nth-child(4n)').css('background-color','whitesmoke');
	
	$('td:contains("펼치기")').on("click", function(){
		alert($(this).parent());
// 		alert($($(this).parent().children()[4]).html());
		var userId = $($(this).parent().children()[1]).text().trim();
		alert("userId:" + userId);
		
		alert($(this).text());
		
		if($(this).text().trim() == '닫기'){
			$("h5").remove();
	 		$('td[id="detailInfo'+ userId+'"]').text("펼치기");
	 		$("#detailInfoSpace" + userId + "").css("display","none");
		}else if($(this).text().trim() == '펼치기'){
			$.ajax({
				url:"/user/json/getUser/" + userId,
				method:"GET",
				dataType:"json",
				headers : {
					"Accept" : "application/json",
					"Content-Type" : "application/json"
				},
				success : function (JSONData, status){
					alert("JSONData: " + JSONData);
					alert("status:" + status);
				
					var displayValue = "<h5>" + "아이디:" + JSONData.userId + "<br/>"
										  + "이 름:" + JSONData.userName + "<br/>"
										  + "이메일:" + JSONData.email + "<br/>"
										  + "ROLE:" + JSONData.role + "<br/>"
										  + "등록일:" + JSONData.regDateString + "<br/>"
										  + "</h5>";
				
					$('td:contains("닫기")').text("펼치기");
					$('td[id="detailInfo' + userId + '"]').text("닫기");
					$("h5").remove();
					$("#detailInfoSpace" + userId +"").css("display","");
					$("#" + userId+"").html(displayValue);
				}
			});
		}
	});

	
	
	
});


</script>




</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<!-- <form name="detailForm"> -->

	<!-- ToolBar Start /////////////////////////////////////-->
<%-- 	<jsp:include page="/layout/toolbar.jsp" /> --%>
   	<!-- ToolBar End /////////////////////////////////////-->
   	<jsp:include page="/common/topBar.jsp"/>
   	<!-- ToolBar End /////////////////////////////////////-->
   	
	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
		
		<!-- 다단레이아웃  Start /////////////////////////////////////-->
		<div class="row">
	
			<!--  Menu 구성 Start /////////////////////////////////////-->     	
			<div class="col-md-3">
		        
		       	<!--  회원관리 목록에 제목 -->
				<jsp:include page="/common/bodyMenu.jsp"/>
				
			</div>
		

	<!--  아래의 내용은 http://getbootstrap.com/getting-started/  참조 -->	
		<div class="col-md-9">

<!-- 	<!-- 참조 : http://getbootstrap.com/css/   : container part..... --> 
<!-- 			<div class="container-md"> -->
<!-- 			<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0"> -->
<!-- 	<tr> -->
<!-- 		<td width="15" height="37"> -->
<!-- 			<img src="/images/ct_ttl_img01.gif" width="15" height="37"> -->
<!-- 		</td> -->
<!-- 		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;"> -->
<!-- 			<table width="100%" border="0" cellspacing="0" cellpadding="0"> -->
<!-- 				<tr> -->
<!-- 					<td width="93%" class="ct_ttl01">회원 목록조회</td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
<!-- 		</td> -->
<!-- 		<td width="12" height="37"> -->
<!-- 			<img src="/images/ct_ttl_img03.gif" width="12" height="37"> -->
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- </table> -->

<!-- <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;"> -->
<div class="container">

<div class="page-header text-info">
	       <h3>회원목록조회</h3>
	    </div>
	    
<div class="row">
	<div class="col-md-4 text-left">
	
	<p>전체  ${pageInfo.totalCount} 건수, 현재 ${pageInfo.currentPage} 페이지</p>
	</div>
	
	<div class="col-md-5 text-right">
	<form class="form-inline" name="detailForm">
		<div class="form-group">
			<select class="form-control" name="searchCondition">
				<option value="0" ${! empty searchCondition && search.getSearchCondition == "0" ? "selected" : "" }>회원ID</option>
				<option value="1" ${! empty searchCondition && search.getSearchCondition == "1" ? "selected" : "" }>회원명</option>
			</select>
			</div>
	
		
			<div class="form-group">
				<label class="sr-only" for="searchKeyword">검색어</label> 
				<input
								type="text" class="form-control" id="searchKeyword"
								name="searchKeyword" placeholder="검색어" 
								value="">
			</div>
			<button type="button" class="btn" >검색</button>
			<input type="hidden" id="currentPage" name="currentPage" value="" />
			</form>
	</div>
	

</div>
</div>
<!-- </table> -->

<table id="list" class="table table-hover table-striped" width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
<!-- 	<tr> -->
<%-- 		<td colspan="11" >전체  ${pageInfo.totalCount} 건수, 현재 ${pageInfo.currentPage} 페이지</td> --%>
<!-- 	</tr> -->
	<thead>
	<tr>
		<td class="ct_list_b" width="100">No</td>
<!-- 		<td class="ct_line02"></td> -->
		<td class="ct_list_b" width="150">회원ID</td>
<!-- 		<td class="ct_line02"></td> -->
		<td class="ct_list_b" width="150">회원명</td>
<!-- 		<td class="ct_line02"></td> -->
		<td class="ct_list_b">이메일</td>	
<!-- 		<td class="ct_line02"></td> -->
		<td class="ct_list_b">세부정보</td>	
<!-- 		<td class="ct_line02"></td> -->
		<td class="ct_list_b">탈퇴</td>	
	</tr>
	</thead>
<!-- 	<tr> -->
<!-- 		<td colspan="11" bgcolor="808285" height="1"></td> -->
<!-- 	</tr> -->
	
	<tbody>
	<c:set var="idx" value="0">
	</c:set>
	
	<c:forEach var="user" items="${list}" begin="${idx}" step="1" end="${idx+pageInfo.pageSize}">
		<tr class="ct_list_pop">
		<td align="center">${idx+1+pageInfo.pageSize*(pageInfo.currentPage-1)}</td>
<!-- 		<td></td> -->
		<td align="left">${user.userId}</td>
<!-- 		<td></td> -->
		<td align="left">${user.userName}</td>
<!-- 		<td></td> -->
		<td align="left">${user.email}</td>
<!-- 		<td></td> -->
		<td id="detailInfo${user.userId}" align="left">펼치기</td>
<!-- 		<td></td> -->
		<td align="left">탈퇴</td>
		</tr>
	
		<tr id="detailInfoSpace${user.userId}" style="display:none;">
			<td id="${user.userId}" colspan="6" bgcolor="D6D7D6" height="1" width="100%" ></td>
		</tr>
	
		<c:set var="idx" value="${idx+1}">
		</c:set>
	
	</c:forEach>
	</tbody>
	<input type="hidden" id="removeUserId" name="removeUserId" value=""/>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		
		<input type="hidden" id="page" name="page" value="" />
		
		<c:import url="../common/pageNavigator.jsp"/>
		
    	</td>
	</tr>
</table>
  	 		</div>
  	 	</div> <!-- col md -->
  	</div>
</div>

<!--  페이지 Navigator 끝 -->
<!-- </form> -->


</body>
</html>