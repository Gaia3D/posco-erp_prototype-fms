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
	
	//System.out.println(request.getParameter("login"));
	//System.out.println(request.getParameter("code"));
	
	String userEmail = (String)session.getAttribute("userEmail");
	String userName = (String)session.getAttribute("userName");
	
	if(!StringUtil.isEmpty(userEmail))
		userEmail = URLDecoder.decode(userEmail, "UTF-8");
	
	if(!StringUtil.isEmpty(userName))
		userName = URLDecoder.decode(userName, "UTF-8");
	
	String error = request.getParameter("error");
	
	if(StringUtil.isEmpty(userEmail)){
%>
<script>
history.back();
</script>
<%		
	} else {
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<title>RUNWAY시설물 관리시스템</title>
<link rel="stylesheet" href="<%=contextRoot%>common/css/style.css">
<jsp:include page="../import/scripts.jsp" />
</head>
<body>  
<div id="header_wrap">
<jsp:include page="../import/top.jsp" >
	<jsp:param value="home" name="menu"/>
</jsp:include>
</div>
<!-- END HEADER -->

<div id="wrap">
	<div class="snb">
    	<ul>
        	<li>
            	<label>제강</label>
                <select>
                  <!-- <option>1제강</option> -->
                  <option>2제강</option>
                  <!-- <option>3제강</option> -->
                </select>
            </li>
            <li>
            	<label>라인</label>
                <select>
                  <option>AB LINE</option>
                  <!-- <option>CD LINE</option> -->
                  <!-- <option>EF LINE</option> -->
                </select>
            </li>
            <li>
            	<label>측량일</label>
                <select id="measurementDate" onchange="relaodGirderStatus()">
                  <c:forEach var="date" items="${measurementDates}">
	    			<option value="${date}" ${date == selMeasurementDate?'selected':''}>${date}</option>
                  </c:forEach>
                </select>
            </li>
        </ul>
    </div>
    <!-- END NAV --> 
    <div class="contents">
    	<ul class="count">
        	<li class="measure"><label>측량</label><span>${measurementErrorCount}</span>건</li>
        	<li class="check"><label>점검</label><span>${inspectionErrorCount}</span>건</li>
        </ul>
        
    	<div class="index xy">
    		<c:forEach var="status" items="${allGirderStatus}">
    			<c:choose>
    				<c:when test="${not status.measurementPass && status.inspectionPass}">
    					<p style="top:${status.positionY + 117}px; left:${status.positionX + 435}px;" title="${status.girderId}">
            				<a href="detail.posco?girderId=${status.girderId}&selDate=${selMeasurementDate}&kind=measurement" class="measure">측량경고</a>
            			</p>
    				</c:when>
    				<c:when test="${not status.inspectionPass && status.measurementPass}">
	    				<p style="top:${status.positionY + 117}px; left:${status.positionX + 435}px;" title="${status.girderId}">
			            	<a href="detail.posco?girderId=${status.girderId}&selDate=${selMeasurementDate}&kind=inspection" class="check">측량경고</a>
			            </p>
    				</c:when>
    				<c:when test="${not status.measurementPass && not status.inspectionPass}">
	    				<p class="both" style="top:${status.positionY + 117}px; left:${status.positionX + 435}px;" title="${status.girderId}">
			            	<a href="detail.posco?girderId=${status.girderId}&selDate=${selMeasurementDate}&kind=measurement" class="measure">측량경고</a>
			                <a href="detail.posco?girderId=${status.girderId}&selDate=${selMeasurementDate}&kind=inspection" class="check">측량경고</a>
			            </p>
    				</c:when>
    				<c:otherwise>
    					<a href="detail.posco?girderId=${status.girderId}&selDate=${selMeasurementDate}&kind=measurement" style="top:${status.positionY + 117}px; left:${status.positionX + 435}px;" title="${status.girderId}"></a>
				    </c:otherwise>
            	</c:choose>
            </c:forEach>
        </div>
        <!-- END INDEX -->
    </div>
    <!-- END CONTENTS -->   
</div>
<!-- END WRAP --> 
</body>
</html>

<%		
	}
%>
