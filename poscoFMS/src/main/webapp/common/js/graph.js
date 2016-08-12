
function showGraph(title, dates, history) {
	
	console.log('그래프 제목 : ' + title);
	console.log('x축  : ' + dates);
	console.log('y축  : ' + history);
	
	
	$(".layer").show();

	var ctx = $("#timeGraph");
	var data = {
		labels : [ "1", "2", "3", "4", "5", "6", "7" ],
		datasets : [ {
			label : "시계열 그래프",
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
			pointRadius : 1,
			pointHitRadius : 10,
			data : [ -5, -10, 10, -34, null, 1, 17 ],
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
