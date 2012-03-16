var timerId;
var currentCue = '';

function timedFunc() 
{
	if(document.ws)
	{
		document.ws.send('ping:'+currentCue);
	}
}

function onLiveLoad(url)
{
	var ws = new WebSocket(url);
	document.ws = ws;
	
	ws.onmessage = function() {
		
	};
	
	timerId = setInterval(timedFunc, 20000);
}