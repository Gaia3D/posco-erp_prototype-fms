/**
 * 
 */

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
	
	$("#selectDate").change(
		function()
		{
			selectedDate = this.value;
			console.log('Measurement date changed : ' + selectedDate);
		
			requestMeasurementData();
		}
	);
	
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
	
	requestNewDataForDetailPage();
	
	if(pageType == 'inspection')
	{
		goPage('check');
	}
	else if(pageType == 'measurement')
	{
		goPage('survey');
	}

}

function requestNewDataForDetailPage()
{
	$.ajax({
		url : contextRoot + "getGirderDetailedData.posco",
		type : "GET",
		dataType : "json",
		data : {
			girderId : selectedGirderId,
			date : selectedDate
		},
		async : true,
		success : detailedGirderDataArriven,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function requestMeasurementData()
{
	$.ajax({
		url : contextRoot + "girderMeasurement.posco",
		type : "GET",
		dataType : "json",
		data : {
			girderId : selectedGirderId,
			date : selectedDate
		},
		async : true,
		success : girderMeasurementDataArriven,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function detailedGirderDataArriven(result)
{
	console.log('All girder data arriven');
	refreshDetailPageToNewMeasurement(result.measurement);
	refreshDetailPageToNewInspection(result.inspection);
}

function girderMeasurementDataArriven(result)
{
	console.log('Girder measurement data arriven');
	refreshDetailPageToNewMeasurement(result);
}

function makeGirderIndexMapHandler()
{
	$('.subindex a').click(
		function()
		{
			$('.subindex a').removeClass('on');
			$(this).addClass('on');
			
			var girderId = $(this).prop('title');
			console.log('Girder Selection Changed : ' + girderId);
			selectedGirderId = girderId;
			
			requestNewDataForDetailPage();
		}
	);
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
	
	// 시간 콤보 박스 show/hide
	if(pageId == "survey"){
		$(".snb ul li:nth-child(3)").show();		
	} else {
		$(".snb ul li:nth-child(3)").hide();
		$(".check_drawing li").removeClass("warn");
	}
}
