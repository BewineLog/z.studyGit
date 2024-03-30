<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<div class="navbar">
		
        <div class="container">
        
        	<a class="navbar-brand" href="#">Model2 MVC Shop</a>
			
			<!-- toolBar Button Start //////////////////////// -->
			<div class="navbar-header">
			    <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#target">
			        <span class="sr-only">Toggle navigation</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			    </button>
			</div>
			<!-- toolBar Button End //////////////////////// -->
			
			<div class="collapse navbar-collapse"  id="target">
	             <ul class="nav navbar-nav navbar-right">
	             
	           		<c:if test="${! empty user}">
	                 	<li><a id="topButton" href="#">로그아웃</a></li>
	                </c:if>
	                
	                <c:if test="${empty user}">
	                	<li><a id="topButton" href="#">로 그 인</a></li>
	                 	<li><a id="topButton" href="#">회원가입</a></li>
	                </c:if>
	           	</ul>
	       </div>
   		
   		</div>
   	</div>