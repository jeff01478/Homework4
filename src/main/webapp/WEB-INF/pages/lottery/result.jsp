<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%= request.getContextPath() %>/style/myStyle.css" rel="stylesheet"/>
</head>
<body>
	<h2>樂透號碼結果</h2>
	<%
		TreeSet[] resultNumbers = (TreeSet[])session.getAttribute("resultLottery");
		if (resultNumbers != null) {
			for(TreeSet resultNum : resultNumbers) {
				out.println(resultNum + "</br></br>");
			}
		}
	%>
	<a href="reTry">再試一次</a><br/>
	<a href="logout">登出</a>
</body>
</html>