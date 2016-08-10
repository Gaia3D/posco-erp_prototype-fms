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
%>
<!DOCTYPE html>
<html>
<body>
<h2>Hello World!</h2>
<h3 style="cursor:pointer" onclick="location.href='<%=contextRoot%>login';">로그인 페이지 링크</h3>
<h3 style="cursor:pointer" onclick="location.href='<%=contextRoot%>home';">레이아웃 페이지 링크</h3>
<h3 style="cursor:pointer" onclick="location.href='<%=contextRoot%>detail';">상세 페이지 링크</h3>
<h3 style="cursor:pointer" onclick="location.href='<%=contextRoot%>report';">보고서 페이지 링크</h3>
<h4>이 파일을 제거하면 정상적으로 로그인 페이지가 가장 먼저 로딩됩니다.!!!</h4>
</body>
</html>
