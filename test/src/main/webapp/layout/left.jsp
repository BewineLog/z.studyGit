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
	
	$('.Depth03:contains("����������ȸ")').on('click', function(){
		alert(  $( ".Depth03:contains('����������ȸ')" ).html() );
		$(window.parent.frames["rightFrame"].document.location).attr("href","/user/getUser?userId=$(user.userId)");
	});
	
	$('.Depth03:contains("ȸ��������ȸ")').on('click', function(){
		alert(  $( ".Depth03:contains('ȸ��������ȸ')" ) );
		$(window.parent.frames["rightFrame"].document.location).attr("href","/user/listUser");
	});
	
// 	<a href="../product/addProductView.jsp;" target="rightFrame">�ǸŻ�ǰ���</a>
	$('.Depth03:contains("�ǸŻ�ǰ���")').on('click', function(){
		alert(  $( ".Depth03:contains('�ǸŻ�ǰ���')" ).text() );
		$(window.parent.frames["rightFrame"].document.location).attr("href","/product/addProductView.jsp;");
	});
	
	
// 	<a href="/product/listProduct?menu=manage" target="rightFrame">�ǸŻ�ǰ����</a>
	
	$('.Depth03:contains("�ǸŻ�ǰ����")').on('click', function(){
		alert(  $( ".Depth03:contains('�ǸŻ�ǰ����')" ).text() );
		$(window.parent.frames["rightFrame"].document.location).attr("href","/product/listProduct?menu=manage");
		
	});
	
// 	<a href="/product/listProduct?menu=search" target="rightFrame">�� ǰ �� ��</a>
	
	$('.Depth03:contains("�� ǰ �� ��")').on('click', function(){
		alert(  $( ".Depth03:contains('�� ǰ �� ��')" ).text() );
		$(window.parent.frames["rightFrame"].document.location).attr("href","/product/listProduct?menu=search");
		
	});
	
// 	<a href="/purchase/listPurchase" target="rightFrame">�����̷���ȸ</a>
	
	$('.Depth03:contains("�����̷���ȸ")').on('click', function(){
		alert(  $( ".Depth03:contains('�����̷���ȸ')" ).text() );
		$(window.parent.frames["rightFrame"].document.location).attr("href","/purchase/listPurchase");
		
	});
	
// 	<a href="javascript:history()">�ֱ� �� ��ǰ</a>
	
	$('.Depth03:contains("�ֱ� �� ��ǰ")').on('click', function(){
		alert(  $( ".Depth03:contains('�ֱ� �� ��ǰ')" ).text() );
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
				����������ȸ
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
				ȸ��������ȸ
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
<!-- 				<a href="../product/addProductView.jsp;" target="rightFrame">�ǸŻ�ǰ���</a> -->
					�ǸŻ�ǰ���
			</td>
		</tr>
		<td class="Depth03">
<!-- 				<a href="/product/listProduct?menu=manage" target="rightFrame">�ǸŻ�ǰ����</a> -->
					�ǸŻ�ǰ����
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
<!-- 				<a href="/product/listProduct?menu=search" target="rightFrame">�� ǰ �� ��</a> -->
					�� ǰ �� ��
			</td>
		</tr>
		<%
			if(vo != null){
				if(role.equals("user")){
		%>
		<tr>
			<td class="Depth03">
<!-- 				<a href="/purchase/listPurchase" target="rightFrame">�����̷���ȸ</a> -->
					�����̷���ȸ
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
<!-- 				<a href="javascript:history()">�ֱ� �� ��ǰ</a> -->
					�ֱ� �� ��ǰ
			</td>
		</tr>
	</table>
</td>
</tr>

</table>
</body>
</html>