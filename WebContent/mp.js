var timerId;
var currentCue = '';

function timedFunc() 
{
	if(document.ws)
	{
		document.ws.send('ping:'+currentCue);
	}
}

function msg(e)
{
	
	if (e.data.startsWith('changeslide:'))
	{
		var q = e.data.substring(12);
		$('info').innerHTML = q;
		try {
			show(q);
			hide(currentCue);
		} catch (e) {}
		
		currentCue = q;
	}
}

function onLiveLoad(url)
{
	var ws = new WebSocket(url);
	document.ws = ws;
	
	ws.onmessage = msg;
	
	ws.onerror = function() {
		alert('error');
	};
	
	ws.onclose = function() {
		alert('close');
	};
	
	timerId = setInterval(timedFunc, 20000);
}