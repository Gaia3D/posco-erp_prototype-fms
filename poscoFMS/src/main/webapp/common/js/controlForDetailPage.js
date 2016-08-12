/**
 * 
 */

$(document).ready();

function onLoad()
{
	$("#measurementDate").change(function(){
			selDate = this.value;
			location.href=contextRoot + "detail.posco?selDate=" + selDate;
	});
}