<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>회원 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script src="../javascript/jquery-2.1.4.js" type="text/javascript"></script>
<script type="text/javascript">

function fncGetList(page) {
	$('#page').val(page);
	$('form').attr("method","POST").attr("action","/user/listUser").submit();
	
}

$(function () {
	$('td.ct_btn01:contains("검색")').on("click", function(){
		fncGetList(1);
	});
});

$(function(){
	$('.ct_list_pop td:nth-child(3)').on("click", function(){
		self.location = "/user/getUser?userId="+$(this).text().trim();
	});
});


$(function() {
	$('td:contains("탈퇴")').on("click", function(e){
		
// 		var userId = $('.ct_list_pop td:nth-child(3)').val();
		
		alert( $($(this).parent().children()[2]).text());
		
		$('input:hidden[name="removeUserId"]').val($($(this).parent().children()[2]).text());
		
		fncGetList(1);
		
	});
	
	alert($('.ct_list_pop td:nth-child(3)').html());
	
	$('.ct_list_pop td:nth-child(3)').css('color','aqua');
	$('.ct_list_pop td:nth-child(9)').css('color','#4aa8d8');
	$('.ct_list_pop td:nth-child(11)').css('color','red');
// 	$('.ct_list_pop td:nth-child(3)').css('background-color','whiteSmoke');

	alert($('table[id="list"] tr:nth-child(even)'));
	$('table[id="list"] tr:nth-child(4n)').css('background-color','whitesmoke');
	
	$('td:contains("펼치기")').on("click", function(){
		alert($(this).parent());
// 		alert($($(this).parent().children()[4]).html());
		var userId = $($(this).parent().children()[2]).text().trim();
		alert("userId:" + userId);
		
		alert($(this).text());
		
		if($(this).text().trim() == '닫기'){
			$("h3").remove();
	 		$('td[id="detailInfo'+ userId+'"]').text("펼치기");
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
				
					var displayValue = "<h3>" + "아이디:" + JSONData.userId + "<br/>"
										  + "이 름:" + JSONData.userName + "<br/>"
										  + "이메일:" + JSONData.email + "<br/>"
										  + "ROLE:" + JSONData.role + "<br/>"
										  + "등록일:" + JSONData.regDateString + "<br/>"
										  + "</h3>";
				
					$('td:contains("닫기")').text("펼치기");
					$('td[id="detailInfo' + userId + '"]').text("닫기");
					$("h3").remove();
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

<form name="detailForm">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">회원 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37">
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
	
	
	<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0" ${! empty searchCondition && search.getSearchCondition == "0" ? "selected" : "" }>회원ID</option>
				<option value="1" ${! empty searchCondition && search.getSearchCondition == "1" ? "selected" : "" }>회원명</option>
			</select>
			<input type="text" name="searchKeyword" value="${! empty search.searchKeyword ? search.searchKeyword :''  }"  class="ct_input_g" style="width:200px; height:19px" >
		</td>
	
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
							검색
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<table id="list" width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >전체  ${pageInfo.totalCount} 건수, 현재 ${pageInfo.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">이메일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">세부정보</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">탈퇴</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	
	<c:set var="idx" value="0">
	</c:set>
	
	<c:forEach var="user" items="${list}" begin="${idx}" step="1" end="${idx+pageInfo.pageSize}">
		<tr class="ct_list_pop">
		<td align="center">${idx+1+pageInfo.pageSize*(pageInfo.currentPage-1)}</td>
		<td></td>
		<td align="left">${user.userId}</td>
		<td></td>
		<td align="left">${user.userName}</td>
		<td></td>
		<td align="left">${user.email}</td>
		<td></td>
		<td id="detailInfo${user.userId}" align="left">펼치기</td>
		<td></td>
		<td align="left">탈퇴</td>
		</tr>
	
		<tr>
			<td id="${user.userId}" colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>
	
		<c:set var="idx" value="${idx+1}">
		</c:set>
	
	</c:forEach>
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
<!--  페이지 Navigator 끝 -->
</form>
</div>

</body>
</html>