

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
	location.href = contextRoot + url + ".posco";
}

/**
 * goPage
 * 상세조회 화면에서 측량/점검 기능 전환을 위한 함수.
 * @param pageId  'survey'(측량), 'check'(점검) 두가지 값을 파라미터로 받아 전환한다.
 * @returns
 */
function goPage(pageId) {
	$(".measure_wrap").each(function() {
		$(this).hide();
	});
	$("#" + pageId).show();
}

/** 점검 마커 표시 **/	
function highlightMarker(table){
	$(".check_drawing li").removeClass("warn");
	var subTd = table.getElementsByTagName("td")
	var markerId = 'marker' + subTd[1].innerHTML
	$("#"+ markerId).addClass("warn");
}



function logout(){
	location.href=contextRoot + "logout.posco";
}