

/**
 * 
 * @param url
 * @returns
 */
function goMenu(url) {
	
	$("#topMenu > li").each(function() {
		$(this).removeClass("on");
	});
	$("." + url).addClass("on");
	//alert(contextRoot + url + ".posco");
	var dt = $("#measurementDate").val();
	if(dt == null || dt.indexOf('undefined') > -1 || dt == undefined){
		location.href = contextRoot + url + ".posco" ;
	} else {
		//alert(">"+dt + "<");
		location.href = contextRoot + url + ".posco?selDate=" + dt ;
	}
}

/** 점검 마커 표시 **/	
function highlightMarker(table){
	$(".check_drawing li").removeClass("warn");
	var subTd = table.getElementsByTagName("td");
	var markerId = 'marker' + subTd[1].innerHTML;
	$("#"+ markerId).addClass("warn");
}



function logout(){
	location.href=contextRoot + "logout.posco";
}

