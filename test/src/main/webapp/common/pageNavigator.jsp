<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:if test="${pageInfo.beginUnitPage > pageInfo.pageSize}">
	<a href="javascript:fncGetList('${pageInfo.beginUnitPage-1}')">◀ 이전</a>
</c:if>

<c:forEach var="i" begin="${pageInfo.beginUnitPage}"
	end="${pageInfo.endUnitPage}">
	<a href="javascript:fncGetList('${i}')">${i}</a>
</c:forEach>

<c:if test="${pageInfo.endUnitPage < pageInfo.maxPage}">
	<a href="javascript:fncGetList('${pageInfo.endUnitPage+1}')">이후 ▶</a>
</c:if>