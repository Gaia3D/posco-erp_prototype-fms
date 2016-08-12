
function showGraph(title, dates, history, maxVal) {
	
	$(".layer").show();
	
	var ctx = $("#timeGraph");
	$(ctx).empty;
	var data = {
		labels : dates,
		datasets : [ {
			label : title + " 시계열 그래프",
			fill : false,
			lineTension : 0.1,
			backgroundColor : "rgba(75,192,192,0.4)",
			borderColor : "rgba(75,192,192,1)",
			borderCapStyle : 'butt',
			borderDash : [],
			borderDashOffset : 0.0,
			borderJoinStyle : 'miter',
			pointBorderColor : "rgba(75,192,192,1)",
			pointBackgroundColor : "#fff",
			pointBorderWidth : 1,
			pointHoverRadius : 5,
			pointHoverBackgroundColor : "rgba(75,192,192,1)",
			pointHoverBorderColor : "rgba(220,220,220,1)",
			pointHoverBorderWidth : 2,
			pointRadius : 5,
			pointHitRadius : 10,
			data : history,
			spanGaps : true,
		} ]
	};

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
						max : 50,
						min : -50,
						stepSize : 5
					}
				} ]
			}
		}
	});
	
	
}
