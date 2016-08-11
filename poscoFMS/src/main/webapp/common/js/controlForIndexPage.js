/**
 * 
 */
$(document).ready(onLoad);

function onLoad()
{
	$("#measurementDate").change(function(){
			selDate = this.value;
			location.href=contextRoot + "home.posco?selDate=" + selDate;
	});
}

