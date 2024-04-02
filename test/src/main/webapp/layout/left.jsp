<%@ page contentType="text/html; charset=euc-kr" %>

<%@ page import="com.model2.mvc.service.domain.*" %>

<%
	User vo=(User)session.getAttribute("user");

	String role="";

	if(vo != null) {
		role=vo.getRole();
	}
%>

<html>
<head>
<title>Model2 MVC Shop</title>

<link href="/css/left.css" rel="stylesheet" type="text/css">

<script src="../javascript/jquery-2.1.4.js" type="text/javascript"></script>
<script type="text/javascript">
function history(){
	popWin = window.open("/history.jsp","popWin","left=300, top=200, width=300, height=200, marginwidth=0, marginheight=0, scrollbars=no, scrolling=no, menubar=no, resizable=no");
}

$(function(){
// 	$('.Depth03').hover(
// 		$(this).css({"font-size:20px"});
// 	);
	
	$('.Depth03:contains("개인정보조회")').on('click', function(){
		alert(  $( ".Depth03:contains('개인정보조회')" ).html() );
		$(window.parent.frames["rightFrame"].document.location).attr("href","/user/getUser?userId=$(user.userId)");
	});
	
	$('.Depth03:contains("회원정보조회")').on('click', function(){
		alert(  $( ".Depth03:contains('회원정보조회')" ) );
		$(window.parent.frames["rightFrame"].document.location).attr("href","/user/listUser");
	});
	
// 	<a href="../product/addProductView.jsp;" target="rightFrame">판매상품등록</a>
	$('.Depth03:contains("판매상품등록")').on('click', function(){
		alert(  $( ".Depth03:contains('판매상품등록')" ).text() );
		$(window.parent.frames["rightFrame"].document.location).attr("href","/product/addProductView.jsp;");
	});
	
	
// 	<a href="/product/listProduct?menu=manage" target="rightFrame">판매상품관리</a>
	
	$('.Depth03:contains("판매상품관리")').on('click', function(){
		alert(  $( ".Depth03:contains('판매상품관리')" ).text() );
		$(window.parent.frames["rightFrame"].document.location).attr("href","/product/listProduct?menu=manage");
		
	});
	
// 	<a href="/product/listProduct?menu=search" target="rightFrame">상 품 검 색</a>
	
	$('.Depth03:contains("상 품 검 색")').on('click', function(){
		alert(  $( ".Depth03:contains('상 품 검 색')" ).text() );
		$(window.parent.frames["rightFrame"].document.location).attr("href","/product/listProduct?menu=search");
		
	});
	
// 	<a href="/purchase/listPurchase" target="rightFrame">구매이력조회</a>
	
	$('.Depth03:contains("구매이력조회")').on('click', function(){
		alert(  $( ".Depth03:contains('구매이력조회')" ).text() );
		$(window.parent.frames["rightFrame"].document.location).attr("href","/purchase/listPurchase");
		
	});
	
// 	<a href="javascript:history()">최근 본 상품</a>
	
	$('.Depth03:contains("최근 본 상품")').on('click', function(){
		alert(  $( ".Depth03:contains('최근 본 상품')" ).text() );
		history();
		
	});
	
});
</script>
</head>

<body background="/images/left/imgLeftBg.gif" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  >

<table width="159" border="0" cellspacing="0" cellpadding="0">

<!--menu 01 line-->
<tr>
<td valign="top"> 
	<table  border="0" cellspacing="0" cellpadding="0" width="159" >	
		<tr>
		<%
			if(vo != null){
		%>
		<tr>
		<td class="Depth03">
				개인정보조회
		</td>
		</tr>
		<%
			}
		%>
		<%
			if(role.equals("admin")){
		%>
		<tr>
		<td class="Depth03" >
				회원정보조회
		</td>
		</tr>
		<%
			}
		%>
		<tr>
			<td class="DepthEnd">&nbsp;</td>
		</tr>
	</table>
</td>
</tr>

	<%
		if(role.equals("admin")){
	%>
<!--menu 02 line-->
<tr>
<td valign="top"> 
	<table  border="0" cellspacing="0" cellpadding="0" width="159">
		<tr>
			<td class="Depth03">
<!-- 				<a href="../product/addProductView.jsp;" target="rightFrame">판매상품등록</a> -->
					판매상품등록
			</td>
		</tr>
		<td class="Depth03">
<!-- 				<a href="/product/listProduct?menu=manage" target="rightFrame">판매상품관리</a> -->
					판매상품관리
			</td>
		</tr>
		<tr>
			<td class="DepthEnd">&nbsp;</td>
		</tr>
	</table>
</td>
</tr>
	<%
				}
	%>

<!--menu 03 line-->
<tr>
<td valign="top">
	<table  border="0" cellspacing="0" cellpadding="0" width="159">
		<tr>
			<td class="Depth03">
<!-- 				<a href="/product/listProduct?menu=search" target="rightFrame">상 품 검 색</a> -->
					상 품 검 색
			</td>
		</tr>
		<%
			if(vo != null){
				if(role.equals("user")){
		%>
		<tr>
			<td class="Depth03">
<!-- 				<a href="/purchase/listPurchase" target="rightFrame">구매이력조회</a> -->
					구매이력조회
			</td>
		</tr>
		<%
				}
			}
		%>
		<tr>
		<td class="DepthEnd">&nbsp;</td>
		</tr>
		<tr>
			<td class="Depth03">
<!-- 				<a href="javascript:history()">최근 본 상품</a> -->
					최근 본 상품
			</td>
		</tr>
	</table>
</td>
</tr>

</table>
</body>
</html>