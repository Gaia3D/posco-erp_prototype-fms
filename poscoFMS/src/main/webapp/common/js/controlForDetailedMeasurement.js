/**
 * 
 */

//function refreshDetailPageToNewMeasurement(date, girderId){
function refreshDetailPageToNewMeasurement(result){
	
	setGirderMeasurementData(result);
	
}

function setGirderMeasurementData(result){
	console.log(result);
	$("#horizontalDeformationAtStart span").text(result.horizontalDeformationAtStart);
	$("#horizontalDeformationAtMid span").text(result.horizontalDeformationAtMid);
	$("#horizontalDeformationAtLast span").text(result.horizontalDeformationAtLast);
	drawStraight(result.horizontalDeformationAtStart, result.horizontalDeformationAtMid, result.horizontalDeformationAtLast);
	
	$("#verticalDeformationAtStart span").text(result.verticalDeformationAtStart);
	$("#verticalDeformationAtMid span").text(result.verticalDeformationAtMid);
	$("#verticalDeformationAtLast span").text(result.verticalDeformationAtLast);
	drawCurve(result.verticalDeformationAtStart, result.verticalDeformationAtMid, result.verticalDeformationAtLast);
}

/**
 * 진직도 직선 그리는 함수
 * @param x
 * @param y
 * @param z
 * @returns
 */
function drawStraight(x,y,z){
	context1.clearRect(0, 0, canvas1.width, canvas1.height);
	var straight1 = x;
	var straight2 = y;
	var straight3 = z;
	
	var interval = 100 ;
	
	var modVal1 = straight1 / interval * 150 + 145 ; //보정 계수 포함. 아래도 마찬가지
	var modVal2 = straight2 / interval * 150 + 145 ;
	var modVal3 = straight3 / interval * 150 + 145 ;
	

    context1.strokeStyle = "darkorange";
    context1.lineWidth = 10;
    context1.lineCap = "miter";
    context1.lineJoin = "round";
    
	context1.beginPath();
	context1.moveTo(modVal1,150);
	context1.lineTo(modVal2,75);
	context1.lineTo(modVal3,0);
	context1.stroke();
	context1.closePath();
	//console.log(modVal1 + "_" + modVal2 + "_" + modVal3);
}


/**
 * 굴곡도 직선 그리는 함수
 * @param x
 * @param y
 * @param z
 * @returns
 */
function drawCurve(x,y,z){
	context2.clearRect(0, 0, canvas2.width, canvas2.height);
	var curve1 = x;
	var curve2 = y;
	var curve3 = z;
	
	var interval = 100 ;
	
	var modVal1 = 70 - curve1 / interval * 150 ; //보정 계수 포함. 아래도 마찬가지
	var modVal2 = 70 - curve2 / interval * 150 ;
	var modVal3 = 70 - curve3 / interval * 150 ;
	

    context2.strokeStyle = "darkorange";
    context2.lineWidth = 3;
    context2.lineCap = "miter";
    context2.lineJoin = "round";
    
	context2.beginPath();
	context2.moveTo(0, modVal1);
	context2.lineTo(150, modVal2);
	context2.lineTo(300, modVal3);
	context2.stroke();
	context2.closePath();
	//console.log(modVal1 + "_" + modVal2 + "_" + modVal3);
}

function makeTimeSeriesPageTrigger()
{
	$(".straight a").click(
		function()
		{
			var classString = $(this).prop('class');
			if(classString.includes('first'))
				requestHorizontalDeformationHistory(0);
			else if(classString.includes('middle'))
				requestHorizontalDeformationHistory(1);
			else if(classString.includes('last'))
				requestHorizontalDeformationHistory(2);
		}
	);
	
	
	$(".curve a").click(
		function()
		{
			var classString = $(this).prop('class');
			if(classString.includes('first'))
				requestVerticalDeformationHistory(0);
			else if(classString.includes('middle'))
				requestVerticalDeformationHistory(1);
			else if(classString.includes('last'))
				requestVerticalDeformationHistory(2);
		}
	);
}

function requestHorizontalDeformationHistory(positionType)
{
	console.log('horizontal clicked : ' + positionType);
	
	$.ajax({
		url : contextRoot + "HorizontalDeformationHistory.posco",
		type : "GET",
		dataType : "json",
		data : {
			girderId : selectedGirderId,
			positionType : positionType
		},
		async : true,
		success : horizontalDeformationHistoryArriven,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function horizontalDeformationHistoryArriven(result)
{
	showGraph('진직도', result.date, result.history);
}

function requestVerticalDeformationHistory(positionType)
{
	console.log('vertical clicked : ' + positionType);
	
	$.ajax({
		url : contextRoot + "verticalDeformationHistory.posco",
		type : "GET",
		dataType : "json",
		data : {
			girderId : selectedGirderId,
			positionType : positionType
		},
		async : true,
		success : verticalDeformationHistoryArriven,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function verticalDeformationHistoryArriven(result)
{
	showGraph('굴곡도', result.date, result.history);
}

