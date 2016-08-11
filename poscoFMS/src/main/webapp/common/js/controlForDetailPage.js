/**
 * 
 */

$(document).ready();

function onLoad()
{
	$("#measurementDate").change(function(){
			selDate = $("#measurementDate").value;
			location.href=contextRoot + "detail.posco?selDate=" + selDate;
	});
}