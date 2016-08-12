/**
 * 
 */

function refreshDetailPageToNewMeasurement(result)
{
}



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
	context1.moveTo(modVal1,0);
	context1.lineTo(modVal2,75);
	context1.lineTo(modVal3,150);
	context1.stroke();
	context1.closePath();
	//console.log(modVal1 + "_" + modVal2 + "_" + modVal3);
}



function drawCurve(x,y,z){
	
	var curve1 = x;
	var curve2 = y;
	var curve3 = z;
	
	var interval = 100 ;
	
	var modVal1 = curve1 / interval * 150 + 70 ; //보정 계수 포함. 아래도 마찬가지
	var modVal2 = curve2 / interval * 150 + 70 ;
	var modVal3 = curve3 / interval * 150 + 70 ;
	

    context2.strokeStyle = "darkorange";
    context2.lineWidth = 10;
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

