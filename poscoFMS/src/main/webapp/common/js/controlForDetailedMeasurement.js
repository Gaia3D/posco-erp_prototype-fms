/**
 * 
 */

//function refreshDetailPageToNewMeasurement(date, girderId){
function refreshDetailPageToNewMeasurement(result){
	
	setGirderMeasurementData(result);
	
}

function setGirderMeasurementData(result){
	//console.log(result);
	$("#horizontalDeformationAtStart")
		.removeClass("warn").addClass(result.horizontalDeformationAtStart<0?"warn":"")
		.find("span").text(result.horizontalDeformationAtStart==9999?"-":result.horizontalDeformationAtStart);
	$("#horizontalDeformationAtMid")
		.removeClass("warn").addClass(result.horizontalDeformationAtMid<0?"warn":"")
		.find(" span").text(result.horizontalDeformationAtMid==9999?"-":result.horizontalDeformationAtMid);
	$("#horizontalDeformationAtLast")
		.removeClass("warn").addClass(result.horizontalDeformationAtLast<0?"warn":"")
		.find(" span").text(result.horizontalDeformationAtLast==9999?"-":result.horizontalDeformationAtLast);
	drawStraight(result.girderId, result.horizontalDeformationAtStart, result.horizontalDeformationAtMid, result.horizontalDeformationAtLast);
	
	$("#verticalDeformationAtStart")
		.removeClass("warn").addClass(result.verticalDeformationAtStart<0?"warn":"")
		.find("span").text(result.verticalDeformationAtStart==9999?"-":result.verticalDeformationAtStart);
	$("#verticalDeformationAtMid")
		.removeClass("warn").addClass(result.verticalDeformationAtMid<0?"warn":"")
		.find("span").text(result.verticalDeformationAtMid==9999?"-":result.verticalDeformationAtMid);
	$("#verticalDeformationAtLast")
		.removeClass("warn").addClass(result.verticalDeformationAtLast<0?"warn":"")
		.find("span").text(result.verticalDeformationAtLast==9999?"-":result.verticalDeformationAtLast);
	drawCurve(result.girderId, result.verticalDeformationAtStart, result.verticalDeformationAtMid, result.verticalDeformationAtLast);
}

/**
 * 진직도 직선 그리는 함수
 * @param x
 * @param y
 * @param z
 * @returns
 */
function drawStraight(girderId, x,y,z){
	context1.clearRect(0, 0, canvas1.width, canvas1.height);
	var straight1 = x;
	var straight2 = y;
	var straight3 = z;
	
	var interval = 100 ;
	
	var modVal1 = 0;
	var modVal2 = 0;
	var modVal3 = 0;

	if(girderId && girderId.indexOf("A") > -1){
		modVal1 = 145 - straight1 / interval * 150 ; //보정 계수 포함. 아래도 마찬가지
		modVal2 = 145 - straight2 / interval * 150 ;
		modVal3 = 145 - straight3 / interval * 150 ;
	} else {
		modVal1 = straight1 / interval * 150 + 145 ; //보정 계수 포함. 아래도 마찬가지
		modVal2 = straight2 / interval * 150 + 145 ;
		modVal3 = straight3 / interval * 150 + 145 ;
	}
	
    context1.strokeStyle = "darkorange";
    context1.lineWidth = 10;
    context1.lineCap = "miter";
    context1.lineJoin = "round";
    
	context1.beginPath();

	if(straight2 < 9999){
		if(straight1 == 9999){
			context1.moveTo(modVal2,75);
		} else {
			context1.moveTo(modVal1,150);
			context1.lineTo(modVal2,75);
		}
		if(straight3 < 9999){
			context1.lineTo(modVal3,0);
		}
		context1.stroke();
	}
		
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
function drawCurve(girderId, x,y,z){
	context2.clearRect(0, 0, canvas2.width, canvas2.height);
	var curve1 = x;
	var curve2 = y;
	var curve3 = z;
	
	var interval = 100 ;
	
	var modVal1 = 60 - curve1 / interval * 150 ; //보정 계수 포함. 아래도 마찬가지
	var modVal2 = 60 - curve2 / interval * 150 ;
	var modVal3 = 60 - curve3 / interval * 150 ;
	

    context2.strokeStyle = "darkorange";
    context2.lineWidth = 3;
    context2.lineCap = "miter";
    context2.lineJoin = "round";
    
	context2.beginPath();
	
	if(curve2 < 9999){
		if(curve1 == 9999){
			context2.moveTo(150, modVal2);
		} else {
			context2.moveTo(0, modVal1);
			context2.lineTo(150, modVal2);
		}
		if(curve3 < 9999){
			context2.lineTo(300, modVal3);
		}
		context2.stroke();
	}
	
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
				requestHorizontalDeformationHistory(2);
			else if(classString.includes('middle'))
				requestHorizontalDeformationHistory(1);
			else if(classString.includes('last'))
				requestHorizontalDeformationHistory(0);
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
	console.log('horizontal history arriven');
	showGraph('진직도', result.dates, result.history);
}

function requestVerticalDeformationHistory(positionType)
{
	console.log('vertical clicked : ' + positionType);
	
	$.ajax({
		url : contextRoot + "VerticalDeformationHistory.posco",
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
	console.log('vertical history arriven');
	showGraph('굴곡도', result.dates, result.history);
}

