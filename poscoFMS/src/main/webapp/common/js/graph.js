
function showGraph(title, dates, history, maxVal) {
	
	$(".layer").show();
	
	var canvas = $("#timeGraph")[0];
	
//	var editDates = [];
//	var editHistory = [];
//	
//	var date1 = ""
//	var date2 = ""
//	
//	for(count in dates){
//		date = dates[count];
//		if (date2 != ""){
//			date1 = date2;
//			date2 = date;
//			
//			splitDate1 = date1.split('-');
//			splitDate2 = date2.split('-');
//			
//			yaerData1 = Number(splitDate1[0]);
//			monthData1 = Number(splitDate1[1]);
//			dayData1 = Number(splitDate1[2]);
//			yaerData2 = Number(splitDate2[0]);
//			monthData2 = Number(splitDate2[1]);
//			dayData2 = Number(splitDate2[2]);
//			
//			while(true){
//				monthData1 += 1
//				if(yaerData1 < yaerData2){
//					if(monthData1 < 13){
//						editDates.push(String(yaerData1)+"-"+monthData1);
//						editHistory.push(null);
//					}else{
//						monthData1 -= 12;
//						yaerData1 += 1;
//						if(yaerData1==yaerData2 && monthData1==monthData2){
//							editDates.push(date2);
//							editHistory.push(history[count]);
//							break;
//						}else{
//							editDates.push(String(yaerData1)+"-"+monthData1);
//							editHistory.push(null);
//						}
//					}
//				}else{
//					if(monthData1 < monthData2){
//						editDates.push(String(yaerData1)+"-"+monthData1);
//						editHistory.push(null);
//					}else{
//						editDates.push(date2);
//						editHistory.push(history[count]);
//						break;
//					}
//				}
//			}
//		}else{
//			date2 = date;
//			editDates.push(date2);
//			editHistory.push(history[count]);
//		}
//	}
	
	var ctx = canvas.getContext('2d');
	var data = {
		labels : dates,
		datasets : [ {
			label : title + " 시계열 그래프",
			fill : false,
			lineTension : 0.1,
			backgroundColor : "rgba(64,127,227,1)",
			borderColor : "rgba(64,127,227,1)",
			borderCapStyle : 'butt',
			borderDash : [],
			borderDashOffset : 0.0,
			borderJoinStyle : 'miter',
			pointBorderColor : "rgba(64,127,227,1)",
			pointBackgroundColor : "#fff",
			pointBorderWidth : 1,
			pointHoverRadius : 5,
			pointHoverBackgroundColor : "rgba(64, 127, 227,1)",
			pointHoverBorderColor : "rgba(64,127,227,0.5)",
			pointHoverBorderWidth : 2,
			pointRadius : 5,
			pointHitRadius : 10,
			data : history,
			spanGaps : true,
		} ]
	};
	
	yMax = maxVal + 10;
	var remainder = yMax % 5;
	if(remainder!=0)
		yMax = yMax - remainder + 5;
	yMin = yMax*(-1);
	
	var myLineChart = new Chart(ctx, {
		type : 'line',
		data : data,
		options : {
			scales : {
				xAxes : [ {
					display : true,
					position : 'bottom'
				} ],
				yAxes : [ {
					ticks : {
						max : yMax,
						min : yMin,
						stepSize : 5
					}
				} ]
			}
		}
	});
	
	$("div.layer>div>p button").click(function() {
		$(".layer").hide();
		myLineChart.destroy()
	})
}
