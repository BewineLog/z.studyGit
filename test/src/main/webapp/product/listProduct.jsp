<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
function fncGetProductList(){
	document.detailForm.submit();
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do?menu=${param.menu}" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
					
							상품 목록조회
					
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0" ${searchVO.searchCondition.trim() == '0' ? 'selected' : '' }>상품번호</option>
				<option value="1" ${searchVO.searchCondition.trim() == '1' ? 'selected' : '' } >상품명</option>
				<option value="2" ${searchVO.searchCondition.trim() == '2' ? 'selected' : '' } >상품가격</option>
			</select>
			<input type="text" name="searchKeyword" value = "${searchVO.searchKeyword.trim()}"  class="ct_input_g" style="width:200px; height:19px" />
		</td>
	
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList();">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >전체 ${count} 건수, 현재 ${pageInfo.currentPage}페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
		
	

	<c:set var="idx" value="${pageInfo.pageSize *(pageInfo.currentPage -1 )}">
	</c:set>
	<c:forEach var="i" items="${list}">
	<tr class="ct_list_pop">
		<td align="center">${idx+1}</td>
		<td></td>
				
				<td align="left"><a href="/getProduct.do?prodNo=${i.prodNo}&menu=${param.menu}">${i.prodName}</a></td>
		
		<td></td>
		
		<td align="left">${i.price}</td>
		<td></td>
		<td align="left">${i.regDate}</td>
		<td></td>
		<td align="left">
		
			${i.proTranCode} 
			
			<c:if test="${i.proTranCode.trim() == '구매완료' && menu.trim() == 'manage'}">
				<a href="/updateTranCodeByProd.do?prodNo=${i.prodNo}&tranCode=2">배송하기</a>
			</c:if>
		
		</td>
		
	<c:set var="idx" value="${idx+1}">
	</c:set>
	
	</c:forEach>
	
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
			
		<c:if test="${pageInfo.beginUnitPage > '1' }">
			<a href="/listProduct.do?page=${pageInfo.beginUnitPage-1}&searchKeyword=${searchVO.searchKeyword}&searchCondition=${searchVO.searchCondtion}">${'<'}</a>
		</c:if>
		
		<c:forEach var="i" begin="${pageInfo.beginUnitPage}" end="${pageInfo.endUnitPage}">
		<a href="/listProduct.do?page=${i}&menu=${menu}&searchKeyword=${searchVO.searchKeyword}&searchCondition=${searchVO.searchCondition}">${i}</a>
		</c:forEach>
		
		<c:if test="${pageInfo.endUnitPage < pageInfo.maxPage }">
			<a href="/listProduct.do?page=${pageInfo.endUnitPage+1}&searchKeyword=${searchVO.searchKeyword}&searchCondition=${searchVO.searchCondtion}">${'>'}</a>
		</c:if>
		
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>