/**
 * 
 */

$(document).ready(onLoad);

var selectedGirderId = "";
var selectedDate = "";
var pageMode = "";

function onLoad()
{
	$("#measurementDate").change(function(){
		selectedDate = this.value;
		
		refreshDetailPageToNewMeasurement(selectedDate, selectedGirderId);
	});
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
	
	$(".btns button").each(function(){
		$(this).removeClass("on");
	});
	$("#" + pageId).show();
	$("#btn" + pageId.substring(0,1).toUpperCase() + pageId.substring(1)).addClass("on");
	
	if(pageId == "survey"){
		$(".snb ul li:nth-child(3)").show();		
	} else {
		$(".snb ul li:nth-child(3)").hide();
	}
}
