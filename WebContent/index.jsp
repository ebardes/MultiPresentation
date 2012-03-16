<%@page import="org.bardes.state.*,org.bardes.entities.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<title>Choose Display</title>
    	<script type="text/javascript" src="prototype.js"></script>
  </head>
  <body><% DB db = new DB(); Show show = db.getShow(); %>
  
  	<h1>Choose Display</h1>
  	<ul>
  		<li><a href="operator.jsp">Operator's Display</a></li>
  		<% for (int p = 1; p <= show.getMaxProjectors(); p++) { %>
  		<li><a href="display.jsp?projectorId=<%= p %>">Live Display #<%= p %></a></li>
  		<% } %>
  	</ul>
  </body>
</html> 
