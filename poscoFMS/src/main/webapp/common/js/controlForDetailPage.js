/**
 * 
 */

$(document).ready(onLoad);

var selectedGirderId = "";
var selectedDate = "";
var pageMode = "";

function onLoad(pageType)
{
	pageMode = pageType;
	selectedGirderId = $("#selectedGirderId").val();
	selectedDate = $("#measurementDate").val();
	
	console.log('initial girder id = ' + selectedGirderId);
	console.log('initial date = ' + selectedDate);
	
	makeGirderIndexMapHandler();
	
	$("#measurementDate").change(function(){
		selectedDate = this.value;
		
		refreshDetailPageToNewMeasurement(selectedDate, selectedGirderId);
	});
	
	$("#btnSurvey").click(
		function()
		{
			console.log('change into measurement');
			pageMode = 'measurement';
			goPage('survey');
		}
	);
	
	$("#btnCheck").click(
		function()
		{
			console.log('change into inspection');
			pageMode = 'inspection';
			goPage('check');
			
		}
	);
	
	if(pageType == 'inspection')
	{
		goPage('check');
	}
	else if(pageType == 'measurement')
	{
		goPage('survey');
	}
}

function makeGirderIndexMapHandler()
{
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
