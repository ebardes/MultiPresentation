<%@page import="org.bardes.html.*,org.bardes.entities.*,org.bardes.state.*,java.util.*" contentType="text/html" pageEncoding="UTF-8"%><html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<% BodyHelper h = new BodyHelper(request); %>
<script type="text/javascript" src="prototype.js"></script>
<script type="text/javascript" src="mp.js"></script>
<script type="text/javascript">
function hide(id)
{
	var z = document.getElementById(id);
	z.style.opacity = 0;
}
function show(id)
{
	var z = document.getElementById(id);
	z.style.opacity = 1;
}
</script>
</head>
<body onload="onLiveLoad('<%= new BodyHelper(request).webDisplaySocketURL() %>');">
<div id="info" style="display:none"></div>
<%
	int projectorId = Integer.parseInt(request.getParameter("projectorId"));

	DB db = new DB();
	Show show = db.getShow();
	List<Cue> cues = db.getCues();
  	for (Cue q : cues) {
	  Slide s = q.getSlide(projectorId);
	  if (s == null) continue;
	  
	  Slide.Type type = s.getContentType();
	  if (type == null || type == Slide.Type.TRACKED || type == Slide.Type.BLANK) continue;
	  
	  String file = s.getContentFile();
	  
%><div id="q<%= q.getCue() %>" class="slide"><%= h.slideContent(s) %></div>
<% } %>
</body>
</html>