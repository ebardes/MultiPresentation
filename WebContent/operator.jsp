<%@page import="org.bardes.html.*,org.bardes.entities.*,org.bardes.state.*,java.util.*" contentType="text/html" pageEncoding="UTF-8"%><html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<% BodyHelper h = new BodyHelper(request); %>
<script type="text/javascript" src="prototype.js"></script>
<script type="text/javascript" src="mp.js"></script>
</head>
<body onkeydown="operatorKey(event);" onload="onLiveLoad('<%= new BodyHelper(request).webOperatorSocketURL() %>');">
<% DB db = new DB();
Slide def = new Slide();
def.setContentType(Slide.Type.TRACKED);
Show show = db.getShow(); %>
<a href="upload.jsp">Add/Update Slide</a>
<input type="button" onclick="refresh(0);" value="refresh" />
<table class="operator">
<tr>
<th>Cue</th>
<% for (int p = 1; p <= show.getMaxProjectors(); p++) { %><th>Projector <%= p %></th><% } %>
</tr>
<%
for (Cue c : DisplayPool.getCues()) {
	Double q = c.getCue();
%><tr id="trq_<%=q%>">
<td><input class="gobutton" type="button" onclick="goCue(<%= q %>);" value="Go" /> <%= q %></td>
<% for (int p = 1; p <= show.getMaxProjectors(); p++) { Slide s = c.getSlide(p); if (s==null) s = def;
%><td>
<div class="edittools">
<input type="button" value="blank" onclick="makeblank(<%=q%>,<%=p%>);" />
<input type="button" value="track" onclick="maketrack(<%=q%>,<%=p%>);" />
</div>
<div class="thumbnail"><%= h.thumbnail(s) %></div>
</td><%
} %>
</tr><% } %>
</table>
<div id='info' style='color: #808080;'>Status</div>
</body>
</html>