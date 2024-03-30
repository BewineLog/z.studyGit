 	$( function() {
		//==> 추가된부분 : "addUser"  Event 연결
		$("a[href='#' ]:contains('회원가입')").on("click" , function() {
			self.location = "/user/addUser"
		});
		
		$("a[href='#']:contains('로 그 인')").on("click", function(){
			self.location = "/user/login";
		});
		
		$("a[href='#']:contains('개인정보조회')").on("click", function(){
			self.location = "/user/getUser?userId="+ userId;
		});
		
		$("a[href='#']:contains('회원정보조회')").on("click", function(){
			self.location = "/user/listUser";
		});
		
		
		$("a[href='#']:contains('상품검색')").on("click", function(){
			self.location = "/product/listProduct?menu=search";
		});
		
	});
	
	$(function(){
		$('a.navbar-brand:contains("Model2")').on("click", function(){
			$(window.parent.document.location).attr("href","/index.jsp");
		});
					
		$('a[href="#"]:contains("로그아웃")').on("click",function(){
			self.location="/user/logout";
		});
	});
		
	$(function(){
		$('li a[id="topButton"]').css('color','white');
	});
	