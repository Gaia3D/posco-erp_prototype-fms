

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
	location.href = "<%=contextRoot%>" + url + ".posco";
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
