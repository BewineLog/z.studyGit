<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="utf-8">
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<!-- <script src="../javascript/list.js"></script> -->
<script>
function fncGetList(page) {
	document.getElementById("page").value = page;
	document.detailForm.submit();
}


function rankingAsc(page){
	if(document.getElementById("rankingAscValue").value ==="" || document.getElementById("rankingAscValue").value ==="null"){
		document.getElementById("rankingAscValue").value = "asc";
		document.getElementById("rankingDescValue").value = null;
	}else{
		document.getElementById("rankingAscValue").value = null;
	}
	
	fncGetList(page);
}


function rankingDesc(page){
	if(document.getElementById("rankingDescValue").value ==="" || document.getElementById("rankingDescValue").value ==="desc"){
		document.getElementById("rankingDescValue").value = "desc";
		document.getElementById("rankingAscValue").value = null;
	}else{
		document.getElementById("rankingDescValue").value = null;
	}
	
	fncGetList(page);
}


function showInventory(page){
	if(document.getElementById("inventoryValue").value === "" || document.getElementById("inventoryValue").value === "show"){
		document.getElementById("inventoryValue").value = "notShow";
	}else{
		document.getElementById("inventoryValue").value = "show";
	}
	
	fncGetList(page);
}


function removeUser(page,id){
	document.getElementById("removeUserId").value = id;
	fncGetList(page);
}

function openDetailPage(){
	alert('openDetailpage');
//	window.open("/detailSearch.jsp");
}

</script>
</head>

<body bgcolor="#ffffff" text="#000000">

	<div style="width: 98%; margin-left: 10px;">

		<form name="detailForm" action="/product/listProduct?menu=${param.menu}"
			method="post">

			<table width="100%" height="37" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
						width="15" height="37" /></td>
					<td background="/images/ct_ttl_img02.gif" width="100%"
						style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="93%" class="ct_ttl01">상품 목록조회</td>
							</tr>
						</table>
					</td>
					<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
						width="12" height="37" /></td>
				</tr>
			</table>


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>

					<td align="right"><select name="searchCondition"
						class="ct_input_g" style="width: 80px">
							<c:if test="${! empty menu && menu == 'manage'}">
								<option value="0"
									${! empty search.searchCondition && search.searchCondition.trim() == '0' ? 'selected' : '' }>상품번호</option>
							</c:if>
							<option value="1"
								${! empty search.searchCondition && search.searchCondition.trim() == '1' ? 'selected' : '' }>상품명</option>
							<%-- 					<option value="2" ${! empty search.searchCondition && search.searchCondition.trim() == '2' ? 'selected' : '' } >상품가격</option> --%>
					</select> <input type="hidden" id="optionValue" name="optionValue"
						value="${optionValue}" /> <input type="text" name="searchKeyword"
						value="${! empty search.searchKeyword ? search.searchKeyword.trim() : ''}"
						class="ct_input_g" style="width: 200px; height: 19px" /> </a></td>
					<!-- 
				여기에 상세보기 버튼을 하나 생성
				상세보기 버튼을 누르면, 새로운 window open by jsp ??
				
				기본검색
				
				가격대: ~ 1만원 1~2만원 2~3만원 직접 입력  ㅁ-ㅁ 검색 버튼   
				
				값을 입력하는 칸 안에 max 가격은 천만원으로 회색 글씨??
				
				그럼 적어도 query에 between price A AND B option
				
				search에 price range variable 필요
				
				
				 -->
					<c:if test="${empty search.searchKeyword }">
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
					</c:if>
				</tr>
			</table>

			<c:if test="${! empty search.searchKeyword}">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					style="margin-top: 10px;">
					<tr>
						<td width=30><a><input type="checkbox"
								name="fixedSearchRangeOne"
								value="${search.fixedSearchRangeOne}"
								${search.fixedSearchRangeOne ? 'checked' : ''} />1만원~2만원</a></td>
						<td width=30><a><input type="checkbox"
								name="fixedSearchRangeTwo"
								value="${search.fixedSearchRangeTwo}"
								${search.fixedSearchRangeTwo ? 'checked' : ''} />2만원~3만원</a></td>
						<td width=30><a><input type="checkbox"
								name="fixedSearchRangeThree"
								value="${search.fixedSearchRangeThree}"
								${search.fixedSearchRangeThree ? 'checked' : ''} />3만원~4만원</a></td>
					</tr>


					<!-- 
				위 아래 중복 안되도록 처리 필요
			 -->



					<tr>
						<td align="right"><input type="text" name="searchRangeLow"
							value="${! empty search.searchRangeLow ? search.searchRangeLow : ''}"
							class="ct_input_g" style="width: 200px; height: 19px" /></td>
						<td align="right"><input type="text" name="searchRangeHigh"
							value="${! empty search.searchRangeHigh ? search.searchRangeHigh : ''}"
							class="ct_input_g" style="width: 200px; height: 19px" /></td>

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
			</c:if>

			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>

					<!-- 
						얘네 위치를 네이버처럼 물품 위로 올리자
					 -->
					<c:if test="${! empty menu && menu == 'search' }">
						<td align="right">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<a><input type="checkbox" id="orderAsc" name="orderAsc"
										onclick="javascript:rankingAsc('1')"
										${! empty search.rankingAscValue && search.rankingAscValue == "asc" ? 'checked' : '' }>가격
										낮은 순</a>
									<input type="hidden" id="rankingAscValue"
										name="rankingAscValue"
										value="${! empty search.rankingAscValue ? search.rankingAscValue : '' }" />
								</tr>
								<tr>
									<a><input type="checkbox" id="orderDesc" name="orderDesc"
										onclick="javascript:rankingDesc('1')"
										${! empty search.rankingDescValue && search.rankingDescValue =="desc" ? 'checked' : '' }>가격
										높은 순</a>
									<input type="hidden" id="rankingDescValue"
										name="rankingDescValue"
										value="${! empty search.rankingDescValue ? search.rankingDescValue : '' }" />

								</tr>
							</table>
						</td>

						<td align="right" width="70">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<a><input type="checkbox" id="inventory" name="inventory"
										onclick="javascript:showInventory('1')"
										${! empty search.inventoryValue && search.inventoryValue =="notShow" ? 'checked' : '' }>재고없음
										보지않기</a>
									<input type="hidden" id="inventoryValue" name="inventoryValue"
										value="${! empty search.inventoryValue ? search.inventoryValue : '' }" />
								</tr>

							</table>
						</td>
					</c:if>

				</tr>
			</table>


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td colspan="11">전체 ${count} 건수, 현재 ${pageInfo.currentPage}페이지</td>
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



				<c:set var="idx"
					value="${pageInfo.pageSize *(pageInfo.currentPage -1 )}">
				</c:set>
				<c:forEach var="i" items="${list}">
					<tr class="ct_list_pop">
						<td align="center">${idx+1}</td>
						<td></td>

						<td align="left">${! empty i.proTranCode && i.proTranCode.trim() == '판매중' ? '<a href="/product/getProduct?prodNo='.concat(i.prodNo).concat('&menu=').concat(menu).concat('">').concat(i.prodName).concat('</a>') : i.prodName}
						</td>

						<td></td>

						<td align="left">${i.price}</td>
						<td></td>
						<td align="left">${i.regDate}</td>
						<td></td>
						<td align="left">${menu == 'manage' && i.proTranCode.trim() !='판매중' ? i.proTranCode : i.proTranCode.trim() == '판매중' ? '판매중' : '재고없음'}

							<c:if
								test="${i.proTranCode.trim() == '구매완료' && menu.trim() == 'manage'}">
								<a href="/purchase/updateTranCodeByProd?prodNo=${i.prodNo}&tranCode=2">배송하기</a>
							</c:if>

						</td>

						<c:set var="idx" value="${idx+1}">
						</c:set>
				</c:forEach>

				<tr>
					<td colspan="11" bgcolor="D6D7D6" height="1"></td>
				</tr>

			</table>

			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td align="center"><input type="hidden" id="page" name="page"
						value="" /> <c:import url="../common/pageNavigator.jsp" /></td>
				</tr>
			</table>
			<!--  페이지 Navigator 끝 -->

		</form>

	</div>
</body>
</html>