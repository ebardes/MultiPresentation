<%@page import="org.bardes.html.BodyHelper" contentType="text/html" pageEncoding="UTF-8"%><html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<% BodyHelper h = new BodyHelper(request); %>
<style><%= h.getTransitionHeader() %></style>
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
<body>
<a href="#" onclick="hide('p002');show('p001');">001</a> <a href="#" onclick="hide('p001');show('p002');">002</a>
<div class="slide" id="p001"><img src="pages/Untitled.001.png" /></div>
<div class="slide" id="p002"><img src="pages/Untitled.002.png" /></div>
</body>
</html>