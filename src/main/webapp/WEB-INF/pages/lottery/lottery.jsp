<%@page import="java.util.LinkedList"%>
<%@page import="java.net.http.HttpRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link href="<%= request.getContextPath() %>/style/myStyle.css" rel="stylesheet"/>
</head>
<body>
	<h2>樂透選號</h2>
	<form action="exLottery" method="post" class="container mt-5">
	  <div class="row justify-content-center">
	    <div class="col-md-6">
	      <div class="card p-4">
	        <h5 class="card-title text-center mb-4">樂透號碼產生器</h5>
	        <div class="mb-3">
	          <label for="groups" class="form-label">數組</label>
	          <input type="text" class="form-control" name="groups" placeholder="請輸入整數" value="${ groups }">
	        </div>
	        <div class="mb-3">
	          <label for="filterNum" class="form-label">排除的數字</label>
	          <input type="text" class="form-control" name="filterNum" placeholder="每個數字需用空格隔開" value="${ filterNum }">
	        </div>
	        <div class="d-grid gap-2">
	          <button type="submit" class="btn btn-primary">送號</button>
	        </div>
	      </div>
	    </div>
	  </div>
	</form>
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
	<a href="logout">登出</a>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
</body>
</html>
