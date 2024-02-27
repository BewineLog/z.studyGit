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
	
	alert("showInventory");
	
	fncGetList(page);
}


