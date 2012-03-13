<%@page import="org.bardes.state.*" %><%
	DB db = new DB();
	out.print(db.getCues());
%>