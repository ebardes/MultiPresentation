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
<div>
<input type="checkbox" onclick="toggleEdit(this);" id="toggleedit"/>
<label for="toggleedit">Show editing tools</label>
</div>
<div class="edittools">
<a href="upload.jsp">Add/Update Slide</a>
<br/>
<input type="button" onclick="refresh(0);" value="refresh" />
</div>
<table class="operator">
<tr>
<th>Cue</th>
<% for (int p = 1; p <= show.getMaxProjectors(); p++) { %><th>Projector <%= p %></th><% } %>
<th> </th></tr>
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
<td class="edittools">
<input type="button" onclick="deleteCue(<%=q%>)" value="Delete Cue <%= q %>" />
</td>
</tr><% } %>
</table>
<div id='info' style='color: #808080;'>Connecting ...</div>
</body>
</html>