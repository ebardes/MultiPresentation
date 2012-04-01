<%@page import="org.bardes.html.*,org.bardes.entities.*,org.bardes.state.*,java.util.*" contentType="text/html" pageEncoding="UTF-8"%><html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<% BodyHelper h = new BodyHelper(request); %>
<script type="text/javascript" src="prototype.js"></script>
<script type="text/javascript" src="mp.js"></script>
</head>
<body>
<% DB db = new DB();
Slide def = new Slide();
def.setContentType(Slide.Type.TRACKED);
Show show = db.getShow(); %>
<a href="upload.jsp">Add/Update Slide</a>
<table class="operator">
<tr>
<th>Cue</th>
<% for (int p = 1; p <= show.getMaxProjectors(); p++) { %><th>Projector <%= p %><br/><input type="button" onclick="refresh(<%=p%>)" value="refresh" /></th><% } %>
</tr>
<%
for (Cue c : db.getCues()) {
%><tr>
<td><input type="button" onclick="goCue(<%= c.getCue() %>);" value="Go" /> <%= c.getCue() %></td>
<% for (int p = 1; p <= show.getMaxProjectors(); p++) { Slide s = c.getSlide(p); if (s==null) s = def; %><td><%= h.thumbnail(s, show) %></td><% } %>
</tr><% } %>
</table>
</body>
</html>