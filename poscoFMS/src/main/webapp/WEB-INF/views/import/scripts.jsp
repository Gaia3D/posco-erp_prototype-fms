<%@page import="kr.posco.erp.poscofms.util.StringUtil"%>
<%@page import="java.net.URLDecoder"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
	// context root를 찾기 위한 프로세스... (context가 변경되더라도 바로 적용할 수 있도록)
	HttpSession session  = request.getSession();
	String contextRoot = session.getServletContext().getContextPath();
	
	// windows와 linux의 context 결과값은 서로 다르다!! 맨뒤의 "/"를 일괄적으로 붙이기 위하여 조건문으로 확실히 한다!!
	if(!"/".equals(contextRoot.substring(contextRoot.length()-1, contextRoot.length())) ){
		contextRoot += "/";
	}
	
	System.out.println(request.getParameter("login"));
	System.out.println(request.getParameter("code"));
	
	String userEmail = (String)session.getAttribute("userEmail");
	String userName = (String)session.getAttribute("userName");
	
	if(!StringUtil.isEmpty(userEmail))
		userEmail = URLDecoder.decode(userEmail, "UTF-8");
	
	if(!StringUtil.isEmpty(userName))
		userName = URLDecoder.decode(userName, "UTF-8");
	
	String error = request.getParameter("error");
%>

	<script src="<%=contextRoot%>common/js/jquery-2.2.3.min.js"></script>
    <script>
    	function goMenu(url){
    		$("#topMenu > li").each(function(){
    			$(this).removeClass("on");
    		});
    		$("."+url).addClass("on");
    		location.href="<%=contextRoot%>" + url + ".posco";
    	}
    </script>
    
    <!-- 그래프 생성 JS -->
    <script src="<%=contextRoot%>common/js/chart.js"></script>
    <script>
	function showGraph(){
		$(".layer").show();
		
		var ctx = $("#timeGraph");
	    var data = {
	   	    labels: ["1", "2", "3", "4", "5", "6", "7"],
	   	    datasets: [
	   	        {
	   	            label: "시계열 그래프",
	   	            fill: false,
	   	            lineTension: 0.1,
	   	            backgroundColor: "rgba(75,192,192,0.4)",
	   	            borderColor: "rgba(75,192,192,1)",
	   	            borderCapStyle: 'butt',
	   	            borderDash: [],
	   	            borderDashOffset: 0.0,
	   	            borderJoinStyle: 'miter',
	   	            pointBorderColor: "rgba(75,192,192,1)",
	   	            pointBackgroundColor: "#fff",
	   	            pointBorderWidth: 1,
	   	            pointHoverRadius: 5,
	   	            pointHoverBackgroundColor: "rgba(75,192,192,1)",
	   	            pointHoverBorderColor: "rgba(220,220,220,1)",
	   	            pointHoverBorderWidth: 2,
	   	            pointRadius: 1,
	   	            pointHitRadius: 10,
	   	            data: [-5, -10, 10, -34, null, 1, 17],
	   	            spanGaps: true,
	   	        }
	   	    ]
	   	};
	    
	    var myLineChart = new Chart(ctx, {
	        type: 'line',
	        data: data,
	        options: {
	            scales: {
	                xAxes: [{
	                    display: true,
	                    position: 'bottom'
	                }],
	                yAxes: [{
	                    ticks: {
	                        max: 50,
	                        min: -50,
	                        stepSize: 5
	                    }
	                }]
	            }
	        }
	    });
	}
 	</script>
    
