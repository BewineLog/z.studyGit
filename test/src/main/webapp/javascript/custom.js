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
		
		$("a[href='#']:contains('판매상품등록')").on("click", function(){
			self.location = "/product/addProductView.jsp";
		});
		
		$("a[href='#']:contains('판매상품관리')").on("click", function(){
			self.location = "/product/listProduct?menu=manage";
		});
		
		
		$("a[href='#']:contains('상품검색')").on("click", function(){
			self.location = "/product/listProduct?menu=search";
		});
		
		$("a[href='#']:contains('구매이력조회')").on("click", function(){
			self.location = "/purchase/listPurchase";
		});
		
		$("a[href='#']:contains('최근본상품')").on("click", function(){
			popWin = window.open("/history.jsp","popWin","left=300, top=200, width=300, height=200, marginwidth=0, marginheight=0, scrollbars=no, scrolling=no, menubar=no, resizable=no");
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
	