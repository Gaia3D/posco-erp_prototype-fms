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
		out.print("<html><head><script>history.back();</script></head></html>");
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
<script src="<%=contextRoot%>common/js/controlForDetailPage.js"></script>
<script src="<%=contextRoot%>common/js/controlForDetailedMeasurement.js"></script>
<script src="<%=contextRoot%>common/js/controlForDetailedInspection.js"></script>
<script src="<%=contextRoot%>common/js/controlForDetailedTimeSeries.js"></script>

<c:if test="${kind == 'inspection'}">
<script>
$(document).ready(function(){
	console.log('page loading : inspection first');
	onLoad('inspection');
});
</script>
</c:if>
<c:if test="${kind == 'measurement'}">
<script>
$(document).ready(function(){
	console.log('page loading : measurement first');
	onLoad('measurement');
});
</script>
</c:if>

</head>

<body>

<div id="header_wrap">
<jsp:include page="../import/top.jsp" >
	<jsp:param value="detail" name="menu"/>
</jsp:include>
</div>
<!-- END HEADER -->

<input id="selectedGirderId" type="hidden" value="${selectedGirderId}">
<input id="measurementDate" type="hidden" value="${selMeasurementDate}">

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
                <select id="selectDate">
                  <c:forEach var="date" items="${measurementDates}">
	    			<option value="${date}" ${date == selMeasurementDate?'selected':''}>${date}</option>
                  </c:forEach>
                </select>
            </li>
        </ul>
    </div>
    <!-- END NAV --> 
    <div class="contents">
    	<div class="subindex xy">
    		<c:forEach var="girderInfo" items="${girderInfoList}">
    			<a id="${girderInfo.girderId}" style="top:${girderInfo.positionY + 63}px;left:${girderInfo.positionX + 58}px;" 
    			title="${girderInfo.girderId}" ${girderInfo.girderId == selectedGirderId?'class="on"':''}></a>
    		</c:forEach>
        </div>
        <!-- END INDEX -->
        <div class="view">
        	<div class="title">
            	<h3>11~12</h3>
                <div class="btns">
                	<button id="btnSurvey" type="button" class="on">측량</button>
                    <button id="btnCheck" type="button"">점검</button>
                </div>
            </div>
            
            <!-- 측량 -->
            <div id="survey" class="measure_wrap">
                <!-- 진직도 -->
                <div class="straight">
                    <h4>진직도</h4>
                    <a href="#" title="시계열조회" id="horizontalDeformationAtLast" class="first"><span>-10</span></a>
                    <a href="#" title="시계열조회" id="horizontalDeformationAtMid" class="middle warn"><span>-0</span></a>
                    <a href="#" title="시계열조회" id="horizontalDeformationAtStart" class="last"><span>23</span></a>
                    <canvas id="canvStraight" style="width:150px;height:400px;margin:57px;">weqrasdfzxcv</canvas>
                    <script>
                    	var canvas1= document.getElementById("canvStraight");
                    	var context1 = canvas1.getContext('2d');
                    </script>
                </div>
                <!-- 굴곡도 -->
                <div class="curve">
                    <h4>굴곡도</h4>
                    <a href="#" title="시계열조회" id="verticalDeformationAtStart" class="first"><span>-10</span></a>
                    <a href="#" title="시계열조회" id="verticalDeformationAtMid" class="middle"><span>-0</span></a>
                    <a href="#" title="시계열조회" id="verticalDeformationAtLast" class="last warn"><span>23</span></a>
                    <canvas id="canvCurve" style="width:400px;height:140px;margin:51px;"></canvas>
                    <script>
                    	var canvas2= document.getElementById("canvCurve");
                    	var context2 = canvas2.getContext('2d');
                    </script>
                </div>

                <!-- 표 -->
                <div class="measure_list">
                    <h4>기준상세</h4>
                    <!-- 측정값이 경고일때 해당 TR에 class="warn"을 적용 -->
                    <table summary="기준상세표">
                        <thead>
	                        <tr>
	                            <th>구분</th>
	                            <th>기준</th>
	                            <th>측정값</th>
	                            <th>판정결과</th>
	                        </tr>
                        </thead>
                        <tr id="horizontalDeformation" >
                            <th>진직도</th>
                            <td>L/500</td>
                            <td>L/1200</td>
                            <td></td>
                        </tr>
                        <tr id="reverseCamber">
                            <th>역캠버</th>
                            <td>L/500</td>
                            <td>L/1200</td>
                            <td></td>
                        </tr>
                        <tr id="heightDifference">
                            <th>단차</th>
                            <td>L/500</td>
                            <td>L/1200</td>
                            <td></td>
                        </tr>
                        <tr id="spanTest">
                            <th>span</th>
                            <td>L/500</td>
                            <td>L/1200</td>
                            <td></td>
                        </tr>
                    </table>
                </div>
                
                <div class="standard">
                    <h3>점검기준</h3>
                     <table summary="점검기준표">
                            <thead>
	                            <tr>
	                                <th>NO</th>
	                                <th>검사항목</th>
	                                <th>검사방법</th>
	                                <th>허용오차</th>
	                            </tr>
                            </thead>
                            <tr>
                                <th>1</th>
                                <td>SPAN</td>
                                <td rowspan="2"><img src="<%=contextRoot%>common/images/girder_span.png" alt="거더 스팬"></td>
                                <td rowspan="2">SPANx(1/1000)<br>이내</td>
                            </tr>
                            <tr>
                                <th>2</th>
                                <td>좌우RAIL의<br>수평차</td>
                            </tr>
                            <tr>
                                <th>3</th>
                                <td>GIRDER진직도<br>(좌우방향의 굽힘)</td>
                                <td><img src="<%=contextRoot%>common/images/girder_straight.png" alt="거더 진직도"></td>
                                <td rowspan="2">1/500</td>
                            </tr>
                            <tr>
                                <th>4</th>
                                <td>GIRDER굴곡도<br>(상하방향의 굽힘)</td>
                                <td><img src="<%=contextRoot%>common/images/girder_curve.png" alt="거더 굴곡도"></td>
                            </tr>
                        </table>
                </div>
                <!-- END 점검기준 -->
            </div>
            <!-- END 측량 -->
            
            <!-- 점검 -->
            <div id="check" class="measure_wrap" style="display:none;">
            	<ul class="check_drawing">
                	<li id="marker1" style="top:152px; left:329px;" title="C/G 하부 플래지와 웨브 접합부">1</li>
                    <li id="marker2" style="top:67px; left:170px;" title="C/G상부 플래지와 웨브 접합부">2</li>
                    <li id="marker3" style="top:96px; left:247px;" title="C/G 웨브(플랜지)">3</li>
                    <li id="marker4" style="top:77px; left:385px;" title="C/G 상부 수직 스티프너와 웨브 접합부" >4</li>
                    <li id="marker5" style="top:147px; left:244px;" title="C/G 하부 수평스티프너와 플랜지, 웨브 부재균열">5</li>
                    <li id="marker6" style="top:99px; left:107px;" title="C/G 수평스티프너와 웨브(플랜지) 접합부">6</li>
                    <li id="marker7" style="top:59px; left:50px;" title="C/G 엔드 플레이트와 웨브 접합부">7</li>
                    <li id="marker8" style="top:55px; left:710px;" title="C/G 수평볼트">8</li>
                    <li id="marker9" style="top:154px; left:47px;" title="C/G 수직볼트">9</li>
                    <li id="marker10" style="top:24px; left:40px;" title="C/G T바 플레이트와 웨브 접합부 및 불트">10</li>
                    <li id="marker11" style="top:147px; left:699px;" title="C/G 연결부분 하부 기둥을 접부">11</li>
                    <li id="marker12" style="top:151px; left:4px;" title="C/G 크레인 거더 하부와 기둥 상부 사이 라이너">12</li>
                    <li id="marker13" style="top:310px; left:325px;" title="C/G 상부 플래지와 가세트 플레이트 접합부">13</li>
                    <li id="marker14" style="top:562px; left:206px;" title="C/G 하부 수평 스티프너와 가세트 플레이트 접합부">14</li>
                    <li id="marker15" style="top:318px; left:367px;"title="C/G 상부 가세트 플레이트와 수평 례티스 및 볼트" >15</li>
                    <li id="marker16" style="top:566px; left:249px;" title="C/G 하부 가세트 플레이트와 수평 례티스 및 볼트">16</li>
                    <li id="marker17" style="top:311px; left:513px;" title="B/G 삼현재와 가세트 플레이트 접합부">17</li>
                    <li id="marker18" style="top:560px; left:513px;" title="B/G 하현재와 가세트 플레이트 접합부">18</li>
                    <li id="marker19" style="top:316px; left:472px;" title="B/G 상현재와 수평례티스 접합부 및 볼트">19</li>
                    <li id="marker20" style="top:566px; left:472px;" title="B/G 하현재와 수평례티스 접합부 및 볼트">20</li>
                    <li id="marker21" style="top:314px; left:661px;" title="B/G 상현재">21</li>
                    <li id="marker22" style="top:560px; left:595px;" title="B/G 하현재">22</li>
                    <li id="marker23" style="top:347px; left:232px;" title="C/G 상부 X앵클 가세트 플레이트와 수직 례티스 및 볼트">23</li>
                    <li id="marker24" style="top:458px; left:227px;" title="C/G 하부 X앵클 가세트 플레이트와 수직 례티스 및 볼트">24</li>
                    <li id="marker25" style="top:348px; left:531px;" title="B/G 상부 X앵클 가세트 플레이트와 수직 례티스 및 볼트">25</li>
                    <li id="marker26" style="top:457px; left:530px;" title="B/G 하부 X앵클 가세트 플레이트와 수직 례티스 및 볼트">26</li>
                    <li id="marker27" style="top:372px; left:597px;" title="B/G 삼현재와 수직가세트 플례이트 접합부">27</li>
                    <li id="marker28" style="top:428px; left:597px;" title="B/G 하현재와 수직가세트 플례이트 접합부">28</li>
                    <li id="marker29" style="top:348px; left:185px;" title="C/G 삼부 수직 스티프너와 가세트 플례이트 접합부">29</li>
                    <li id="marker30" style="top:460px; left:183px;" title="C/G 하부 수직 스티프너와 가세트 플례이트 접합부">30</li>
                    <li id="marker31" style="top:345px; left:124px;" title="?">31</li>
                </ul>
            	
                <div class="check_list">
                    <h3>점검</h3>
                     <table summary="점검표">
                            <thead>
                            	<tr>
                                    <th rowspan="2">날짜</th>
                                    <th colspan="3">항목</th>
                                    <th colspan="3">균열</th>
                                    <th colspan="2">볼트</th>
                                    <th colspan="2">보수불가</th>
                                </tr>
                                <tr>
                                    <th>유형</th>
                                    <th>위치</th>
                                    <th>개소</th>
                                    <th>CRG</th>
                                    <th>BG</th>
                                    <th>Colume</th>
                                    <th>풀림</th>
                                    <th>절손</th>
                                    <th>풀림</th>
                                    <th>절손</th>
                                </tr>
                            </thead>
                            
                        </table>
                </div>
                <!-- END 점검기준 -->
            </div>
            <!-- END 점검 -->
        </div>
        <!-- END VIEW -->
    </div>
    <!-- END CONTENTS -->   
</div>
<!-- END WRAP --> 

<div class="layer" style="display:none;">
	 <div class="contents">
     	<p class="close">
        	<button type="button" title="닫기" onclick="$('.layer').hide();">레이어닫기</button>
        </p>
    	<h4>A11-1 </h4>
        <div class="graph">
        	<canvas class="graph" id="timeGraph" ></canvas>
        </div>
    </div>
    <!-- END CONTENTS --> 
</div>
<!-- END LAYER -->
</body>
</html>

<%		
	}
%>