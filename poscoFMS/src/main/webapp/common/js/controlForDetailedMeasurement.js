/**
 * 
 */

function refreshDetailPageToNewMeasurement(date, girderId){
	
	$.ajax({
		url : contextRoot + "girderMeasurement.posco",
		type : "GET",
		dataType : "json",
		data : {
			girderId : girderId,
			date : date
		},
		async : true,
		success : setGirderMeasurementData,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
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
	
	var curve1 = x;
	var curve2 = y;
	var curve3 = z;
	
	var interval = 100 ;
	
	var modVal1 = 70 - curve1 / interval * 150 ; //보정 계수 포함. 아래도 마찬가지
	var modVal2 = 70 - curve2 / interval * 150 ;
	var modVal3 = 70 - curve3 / interval * 150 ;
	

    context2.strokeStyle = "purple";
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

