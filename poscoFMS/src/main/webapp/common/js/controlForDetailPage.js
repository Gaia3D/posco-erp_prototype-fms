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


