<%@page import="org.bardes.html.BodyHelper" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<meta http-equiv="Page-Enter" content="blendTrans(Duration=5)" />
    	<title>GlassFish JSP Page</title>
    	<script type="text/javascript" src="prototype.js"></script>
    	<script type="text/javascript" src="mp.js"></script>
  </head><%
  	BodyHelper h = new BodyHelper(request);
  %>
  <body onload="onLoad();"><%= h.body() %>
  <div id="items"></div>
  </body>
</html> 
