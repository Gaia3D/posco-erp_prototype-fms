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
			selDate = this.value;
			location.href=contextRoot + "detail.posco?selDate=" + selDate;
			
			refreshDetailPageToNewMeasurement(selDate, selectedGirderId);
	});
}


