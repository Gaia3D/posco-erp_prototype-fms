/**
 * 
 */

//function refreshDetailPageToNewMeasurement(date, girderId){
function refreshDetailPageToNewMeasurement(result){
	
	$("#horizontalDeformation")
		.removeClass("warn").addClass(result.horizontalDeformationTestResult == 'pass'?"":result.horizontalDeformationTestResult == 'failure'?"warn":"unchecked")
		.find("td")[1].innerText = result.horizontalDeformationCalculated;
	$("#reverseCamber")
		.removeClass("warn").addClass(result.reverseCamberTestResult == 'pass'?"":result.reverseCamberTestResult == 'failure'?"warn":"unchecked")
		.find("td")[1].innerText = result.reverseCamberCalculated;
	$("#heightDifference")
		.removeClass("warn").addClass(result.heightDifferenceTestResult == 'pass'?"":result.heightDifferenceTestResult == 'failure'?"warn":"unchecked")
		.find("td")[1].innerText = result.heightDifferenceBetweenThisAndPreviousGirder;
	$("#spanTest")
		.removeClass("warn").addClass(result.spanTestResult == 'pass'?"":result.spanTestResult == 'failure'?"warn":"unchecked")
		.find("td")[1].innerText = result.spanCalculated;
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
	
	
	var interval = 70 ;
	
	var modVal1 = 0;
	var modVal2 = 0;
	var modVal3 = 0;

	if(girderId && girderId.indexOf("A") > -1){
		modVal1 = 73 - straight1 / interval * 75 ; //보정 계수 포함. 아래도 마찬가지
		modVal2 = 73 - straight2 / interval * 75 ;
		modVal3 = 73 - straight3 / interval * 75 ;
	} else {
		modVal1 = straight1 / interval * 75 + 73 ; //보정 계수 포함. 아래도 마찬가지
		modVal2 = straight2 / interval * 75 + 73 ;
		modVal3 = straight3 / interval * 75 + 73 ;
	}
	
	$(canvas1).attr("width", "150px");
	$(canvas1).attr("height", "400px");
	$(canvas1).css("margin", "57px");
	
    context1.strokeStyle = "000";
    context1.lineWidth = 3;
    context1.lineCap = "miter";
    context1.lineJoin = "round";
    
	context1.beginPath();

	if(straight2 < 9999){
		if(straight1 == 9999){
			context1.moveTo(modVal2,200);
		} else {
			context1.moveTo(modVal1,400);
			context1.lineTo(modVal2,200);
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
	
	var modVal1 = 55 - curve1 / interval * 75 ; //보정 계수 포함. 아래도 마찬가지
	var modVal2 = 55 - curve2 / interval * 75 ;
	var modVal3 = 55 - curve3 / interval * 75 ;
	

	$(canvas2).attr("width", "400px");
	$(canvas2).attr("height", "141px");
	$(canvas2).css("margin", "51px");
	//context2.canvas.top = 51;
	//context2.canvas.left = 51;

	context2.strokeStyle = "000";
    context2.lineWidth = 3;
    context2.lineCap = "miter";
    context2.lineJoin = "round";
    
	context2.beginPath();
	
	if(curve2 < 9999){
		if(curve1 == 9999){
			context2.moveTo(200, modVal2);
		} else {
			context2.moveTo(0, modVal1);
			context2.lineTo(200, modVal2);
		}
		if(curve3 < 9999){
			context2.lineTo(400, modVal3);
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
			
			$("body").css("overflow-y","hidden");
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
			
			$("body").css("overflow-y","hidden");
		}
	);
}

function requestHorizontalDeformationHistory(positionType)
{
	console.log('horizontal clicked : ' + positionType);
	$("div.contents>h4").html(selectedGirderId +" - "+positionType)
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
	showGraph('진직도', result.dates, result.history, result.maxVal);
}

function requestVerticalDeformationHistory(positionType)
{
	console.log('vertical clicked : ' + positionType);
	$("div.contents>h4").html(selectedGirderId +" - "+positionType)
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
	showGraph('굴곡도', result.dates, result.history, result.maxVal);
}

