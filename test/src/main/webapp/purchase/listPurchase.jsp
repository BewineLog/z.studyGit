<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script src="../javascript/list.js">
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/purchase/listPurchase" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">전체 ${count} 건수, 현재 ${pageInfo.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100">주문번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">전화번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">정보수정</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

<%-- 	<c:forEach var="idx" begin="1" end="${count}"> --%>
	<c:forEach var="i" items="${list}" end="${count}" varStatus = "status">
	
	
	<tr class="ct_list_pop">
		<td align="center">
			<a href="/purchase/getPurchase?tranNo=${i.tranNo}">${status.index + 1}</a>
		</td>
		<td></td>
		<td><a href="/purchase/getPurchase?tranNo=${i.tranNo}">${i.tranNo}</a></td>
		<td></td>
		<td align="left">
			<a href="/user/getUser?userId=${i.buyer.userId}">${i.buyer.userId}</a>
		</td>
		<td></td>
		<td align="left">${i.buyer.userName}</td>
		<td></td>
		<td align="left">${i.receiverPhone }</td>
		<td></td>
		<td align="left">현재
				
					${i.purchaseProd.proTranCode}
				상태 입니다.
				
				<c:if test="${i.purchaseProd.proTranCode.trim() == '배송중' }">
					<a href="/purchase/updateTranCode?page=${status.index + 1}&tranNo=${i.tranNo}&tranCode=3">${'물건도착'}</a> 
				</c:if>
				
				
				<%--환불하기 만들기 --%>
				</td>
		<td></td>
		<td align="left">
			
		</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>
<%-- 	</c:forEach> --%>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
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