<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function showSuccessMessage(message) {
	    alert(message);
	}
</script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link href="<%= request.getContextPath() %>/style/myStyle.css" rel="stylesheet"/>
</head>
<body>
	<br/>
	<div class="card" style="width: 18rem; margin: auto; padding: 2rem;">
	  <div class="card-body pt-0">
	    <h5 class="card-title" style="padding-bottom: 1rem">帳號註冊</h5> 
	    <form:form action="/homework4/addPerson" method="POST">
	      <div class="mb-3">
	        <form:label path="userName" class="form-label">帳號</form:label>
	        <form:input path="userName" type="text" class="form-control" name="userName" value="${ registerUserName }"/>
	      </div>
	      <div class="mb-3">
	        <form:label path="password" class="form-label">密碼</form:label>
	        <form:input path="password" type="password" class="form-control" name="password" value="${ registerPassword }"/>
	      </div>
	      <div class="mb-3">
	        <label class="form-label">確認密碼</label>
	        <input type="password" class="form-control" name="confirmPassword" value="${ confirmPassword }"/>
	      </div>
	      <button type="submit" class="btn btn-primary">送出</button>
	    </form:form>
	  </div>
	</div>
	<ul style="color: red; font-size: 0.8em">
		<table style="margin: auto">
			<% 
				LinkedList<String> errorMsgs = (LinkedList)session.getAttribute("errors");
				if(errorMsgs != null) {
					for(String error : errorMsgs) {
						out.println("<tr><td><li>" + error + "</li></td></tr>");
					}
				}
			%>
		</table>
	</ul>
	<% session.removeAttribute("registerUserName"); %>
	<% session.removeAttribute("registerPassword"); %>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
</body>
</html>