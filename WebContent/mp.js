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
			if (q != currentCue)
				hide(currentCue);
		} catch (e) {}
		
		currentCue = q;
	}
	if (e.data.startsWith('startclip:'))
	{
		var q = e.data.substring(10);
//		try {
			var mov = document.getElementById(q);
			mov.play();
//		} catch (e) {}
	}
	if (e.data.startsWith('refresh'))
	{
		$('info').innerHTML = 'Refresh';
		
		history.go(0);
	}
	if (e.data.startsWith('curcue:'))
	{
		var q = e.data.substring(7);
		
		if (q != currentCue)
		{
			var tr = document.getElementById('trq_'+q);
			tr.style.backgroundColor = '#300050';
			
			try
			{
				if (currentCue != '')
				{
					tr = document.getElementById('trq_'+currentCue);
					tr.style.backgroundColor = '#000000';
				}
			} catch (e) {}
		}
		
		currentCue = q;
	}
}

function onLiveLoad(url)
{
	savedurl = url;
	timerId = setInterval(timedFunc, 10000);
}

function makeblank(cue, projector)
{
	new Ajax.Request("blank/"+cue+","+projector, {
		onSuccess: function(transport) {
			history.go(0);
		}
	});
}

function maketrack(cue, projector)
{
	new Ajax.Request("track/"+cue+","+projector, {
		onSuccess: function(transport) {
			history.go(0);
		}
	});
}

function goCue(cue)
{
	new Ajax.Request("go/"+cue);
}

function refresh(display)
{
	new Ajax.Request("refresh/"+display);
}

function operatorKey(event)
{
	if (event.keyCode == 32 /* space */ || event.keyCode == 40 /* key down */)
	{
		goCue('next');
		event.bubbles = false;
	}
	else if (event.keyCode == 38 /* key up */)
	{
		goCue('prev');
		event.bubbles = false;
	}
}
