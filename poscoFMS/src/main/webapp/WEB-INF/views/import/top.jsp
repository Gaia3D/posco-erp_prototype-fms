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

	<div class="header">
    	<h1>POSCO RUNWAY시설물 관리시스템</h1>
        <ul id="topMenu">
        	<li><a href="#" class="home" onclick="goMenu('home');">홈</a></li>
            <li><a href="#" class="detail" onclick="goMenu('detail');">상세조회</a></li>
            <li><a href="#" class="report" onclick="goMenu('report');">보고서</a></li>
        </ul>
        <p class="user">
        	<span>홍길동 님</span>
            <button type="button" onclick="goMenu('login');">로그아웃</button>
        </p>
    </div>

