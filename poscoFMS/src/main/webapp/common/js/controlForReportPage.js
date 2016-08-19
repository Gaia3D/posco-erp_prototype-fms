/**
 * 
 */
function onLoad(pageType)
{

	console.log('init reportpage!!!!');
	
	$("#downPdf").click(function(){
		
		console.log('download Pdf document');
		//pdfSave( $(".contents").html());

		//console.log("야호 성공!!!");
	});
	
	$("#printHtml").click(
		function()
		{
			console.log('print this document');
			//pageMode = 'inspection';
			//goPage('check');
			
		}
	);
	
}

function pdfSave(htmlValue){

	html2canvas(document.getElementById("contents"), {
		onrendered:function(canvas){
			var imgData = canvas.toDataURL('image/png');
			console.log('Report Image URL : ' + imgData);
			var doc = new jsPDF("p", "mm", [210, 297]);
			
			var height = canvas.height * 190 / canvas.width;  
			doc.addImage(imgData, 'PNG', 10, 10, 200, height+10);
			doc.save('sample-file.pdf');
			
		}
	});
	
	
}