<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="panel panel-primary">
					<div class="panel-heading">
						<i class="glyphicon glyphicon-heart"></i> 회원관리
         			</div>
         			<!--  회원관리 아이템 -->
					<ul class="list-group">
						 <li class="list-group-item">
						 	<a href="#">개인정보조회</a> 
						 </li>
						 
						 <c:if test="${sessionScope.user.role == 'admin'}">
						 <li class="list-group-item">
						 	<a href="#">회원정보조회</a> 
						 </li>
						 </c:if>
					</ul>
		        </div>
               
               <c:if test="${sessionScope.user.role == 'admin'}">
				<div class="panel panel-primary">
					<div class="panel-heading">
							<i class="glyphicon glyphicon-briefcase"></i> 판매상품관리
         			</div>
					<ul class="list-group">
						 <li class="list-group-item">
						 	<a href="#">판매상품등록</a> 
						 </li>
						 <li class="list-group-item">
						 	<a href="#">판매상품관리</a> 
						 </li>
					</ul>
		        </div>
		        </c:if>
               
               
				<div class="panel panel-primary">
					<div class="panel-heading">
							<i class="glyphicon glyphicon-shopping-cart"></i> 상품구매
	    			</div>
					<ul class="list-group">
						 <li class="list-group-item"><a href="#">상품검색</a></li>
						  <li class="list-group-item">
						  	<a href="#">구매이력조회</a> 
						  </li>
						 <li class="list-group-item">
						 	<a href="#">최근본상품</a> 
						 </li>
					</ul>
				</div>