var timerId;
var currentCue = '';
var savedurl;

function timedFunc() 
{
	if(document.ws)
	{
		document.ws.send('ping:'+currentCue);
	}
	else
	{
		var ws = new WebSocket(savedurl);
		document.ws = ws;
		
		ws.onmessage = msg;
		
		ws.onerror = function() {
			alert('error');
		};
		
		ws.onclose = function() {
			document.ws = null;
			$('info').innerHTML = 'Disconnected';
		};
		
		$('info').innerHTML = 'Connected';
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
		} catch (e) {}
		try {
			hide(currentCue);
		} catch (e) {}
		
		currentCue = q;
	}
}

function onLiveLoad(url)
{
	savedurl = url;
	timerId = setInterval(timedFunc, 10000);
}

function goCue(cue)
{
	new Ajax.Request("go/"+cue);
}