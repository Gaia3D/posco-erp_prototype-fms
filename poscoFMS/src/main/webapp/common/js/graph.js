
function showGraph(title, dates, history, maxVal) {
	
	$(".layer").show();
	
	var canvas = $("#timeGraph")[0];
	
	var ctx = canvas.getContext('2d');
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
