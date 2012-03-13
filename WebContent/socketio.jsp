<%@page language="java" import="org.bardes.html.*" %>
<html>
<head>
<script type="text/javascript" src="prototype.js"></script>
<script type="text/javascript">
var ws;

document.observe("dom:loaded", function() {
    function log(text) {
        $("log").innerHTML = (new Date).getTime() + ": " + (!Object.isUndefined(text) && text !== null ? text.escapeHTML() : "null") + $("log").innerHTML;
    }

    if (!window.WebSocket) {
        alert("FATAL: WebSocket not natively supported. This demo will not work!");
    }

    var url = "<%= new BodyHelper(request).webSocketURL() %>";
    ws = new WebSocket(url);
    ws.onmessage = function(e) {
    	$('msg').innerHTML = e.data;
    };
    
});

</script>
</head>
<body>
<div id="msg">
Message Area
</div>
</body>
</html>