<%@page import="org.bardes.html.*,org.bardes.entities.*,org.bardes.state.*,java.util.*" contentType="text/html" pageEncoding="UTF-8"%><html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<% BodyHelper h = new BodyHelper(request); %>
<script type="text/javascript" src="prototype.js"></script>
<script type="text/javascript" src="mp.js"></script>
</head>
<%

String cue = request.getParameter("cue");
if (cue != null)
{
	DisplayPool.goCue(cue);
}
else
{
	cue = "";
}

%>
<body>
<form>
<input name="cue" value="<%= cue %>" />
<br/>
<input type="submit" value="GO" />
</form>
</body>
</html>