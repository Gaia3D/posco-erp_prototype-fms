

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

function logout(){
	location.href=contextRoot + "logout.posco";
}

