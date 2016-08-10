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
	String msg = (String)session.getAttribute("msg");
	session.setAttribute("msg", null);
	
	if(!StringUtil.isEmpty(userEmail))
		userEmail = URLDecoder.decode(userEmail, "UTF-8");
	
	if(!StringUtil.isEmpty(userName))
		userName = URLDecoder.decode(userName, "UTF-8");
	
	String error = request.getParameter("error");
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

<body class="login">
	<div class="login_wrap">
    	<h1>POSCO RUNWAY시설물 관리시스템</h1>
    	<% if(!StringUtil.isEmpty(msg)){%>
    	<h2><%=msg %><br /></h2>
    	<%} %>

        <form action="<%=contextRoot %>goLogin.posco" method="POST">
        	<p>
        		<input type="text" name="userId" placeholder="아이디">
            </p>
            <p>
            	<input type="password" name="userPw" placeholder="비밀번호">
            </p>
            <button type="submit">로그인</button>
        </form>


    </div>  
</body>
</html>