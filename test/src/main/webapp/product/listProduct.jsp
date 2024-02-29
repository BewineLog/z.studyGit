<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="utf-8">
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script src="../javascript/list.js">
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


<table width="100%" border="0" cellspacing="0" cellpadding="0"style="margin-top: 10px;">
		<tr>

		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width: 80px">
					<c:if test="${! empty menu && menu == 'manage'}">
					<option value="0" ${! empty searchVO.searchCondition && searchVO.searchCondition.trim() == '0' ? 'selected' : '' }>상품번호</option>
					</c:if>
					<option value="1" ${! empty searchVO.searchCondition && searchVO.searchCondition.trim() == '1' ? 'selected' : '' } >상품명</option>
					<option value="2" ${! empty searchVO.searchCondition && searchVO.searchCondition.trim() == '2' ? 'selected' : '' } >상품가격</option>
			</select> 
			
			<input type="hidden" id="optionValue" nsame="optionValue" value="${optionValue}"/>
			
			<input type="text" name="searchKeyword" value="${! empty searchVO.searchKeyword ? searchVO.searchKeyword.trim() : ''}" class="ct_input_g" style="width: 200px; height: 19px" /> </a>
			</td>
			<td>
				<!-- 
				여기에 상세보기 버튼을 하나 생성
				상세보기 버튼을 누르면, 새로운 window open by jsp ??
				
				기본검색
				
				가격대: ~ 1만원 1~2만원 2~3만원 직접 입력  ㅁ-ㅁ 검색 버튼   
				
				값을 입력하는 칸 안에 max 가격은 천만원으로 회색 글씨??
				
				그럼 적어도 query에 between price A AND B option
				
				SearchVO에 price range variable 필요
				
				
				 -->
			</td>

			<td align="right" width="70">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="17" height="23"><img
									src="/images/ct_btnbg01.gif" width="17" height="23"></td>
								<td background="/images/ct_btnbg02.gif" class="ct_btn01"
									style="padding-top: 3px;"><a
									href="javascript:fncGetList('1');">검색</a></td>
								<td width="14" height="23"><img
									src="/images/ct_btnbg03.gif" width="14" height="23"></td>
							</tr>

						</table>
					</td>
				</tr>
			</table>

			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>

<!-- 					<td align="right"><select name="searchCondition" -->
<!-- 						class="ct_input_g" style="width: 80px"> -->
<!-- 							<option value="0" -->
<%-- 								${! empty searchVO.searchCondition && searchVO.searchCondition.trim() == '0' ? 'selected' : '' }>상품번호</option> --%>
<!-- 							<option value="1" -->
<%-- 								${! empty searchVO.searchCondition && searchVO.searchCondition.trim() == '1' ? 'selected' : '' }>상품명</option> --%>
<!-- 							<option value="2" -->
<%-- 								${! empty searchVO.searchCondition && searchVO.searchCondition.trim() == '2' ? 'selected' : '' }>상품가격</option> --%>
<!-- 					</select>  -->
					
<!-- 					<input type="text" name="searchKeyword" -->
<%-- 						value="${! empty searchVO.searchKeyword ? searchVO.searchKeyword.trim() : ''}" --%>
<!-- 						class="ct_input_g" style="width: 200px; height: 19px" /></td> -->



					<!-- 
						얘네 위치를 네이버처럼 물품 위로 올리자
					 -->
					<c:if test="${! empty menu && menu == 'search' }">
					<td align="right">
						<table border="0" cellspacing="0" cellpadding="0">
						<tr>
						<a><input type="checkbox" id="orderAsc" name="orderAsc" onclick="javascript:rankingAsc('1')" ${! empty rankingAscValue && rankingAscValue == "asc" ? 'checked' : '' }>가격 낮은 순</a>
							<input type="hidden" id="rankingAscValue" name="rankingAscValue" value="${! empty rankingAscValue ? rankingAscValue : '' }"/>
						</tr>
						<tr>
						<a><input type="checkbox" id="orderDesc" name="orderDesc" onclick="javascript:rankingDesc('1')" ${! empty rankingDescValue && rankingDescValue =="desc" ? 'checked' : '' }>가격 높은 순</a>
								<input type="hidden" id="rankingDescValue" name="rankingDescValue" value="${! empty rankingDescValue ? rankingDescValue : '' }"/>
						
						</tr>
						</table>	
					</td>

					<td align="right" width="70">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<a><input type="checkbox" id="inventory" name="inventory" onclick="javascript:showInventory('1')" ${! empty inventoryValue && inventoryValue =="notShow" ? 'checked' : '' }>재고없음 보지않기</a>
								<input type="hidden" id="inventoryValue" name="inventoryValue" value="${! empty inventoryValue ? inventoryValue : '' }"/>
							</tr>

						</table>
					</td>
					</c:if>

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
				
		<td align="left">
			${! empty i.proTranCode && i.proTranCode.trim() == '판매중' ? '<a href="/getProduct.do?prodNo='.concat(i.prodNo).concat('&menu=').concat(menu).concat('">').concat(i.prodName).concat('</a>') : i.prodName} 
		</td>
		
		<td></td>
		
		<td align="left">${i.price}</td>
		<td></td>
		<td align="left">${i.regDate}</td>
		<td></td>
		<td align="left">
		
			${menu == 'manage' && i.proTranCode.trim() !='판매중' ? i.proTranCode : i.proTranCode.trim() == '판매중' ? '판매중' : '재고없음'} 
			
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