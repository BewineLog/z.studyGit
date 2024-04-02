<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!-- <div class="container text-center"> -->

	<c:if test="${pageInfo.beginUnitPage > pageInfo.pageSize}">
		<a href="javascript:fncGetList('${pageInfo.beginUnitPage-1}')"><i class="glyphicon glyphicon-chevron-left"></i></a>
	</c:if>

	<c:forEach var="i" begin="${pageInfo.beginUnitPage}"
	end="${pageInfo.endUnitPage}">
		
			 <button type="button" id="indexButton${i}" class="btn btn-default"> ${i} </button>
		
	</c:forEach>
	
	<c:if test="${pageInfo.endUnitPage < pageInfo.maxPage}">
		<a href="javascript:fncGetList('${pageInfo.endUnitPage+1}')"><i class="glyphicon glyphicon-chevron-right"></i> </a>
	</c:if>
	
<!-- </div> -->

<%-- <a href="javascript:fncGetList('${i}')">${i}</a> --%>

