<%@page import="org.bardes.html.*" %>
<%
WSS wss = WSS.getInstance();

wss.send("001", request.getParameter("msg"));
%>