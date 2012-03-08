var timerId;

function timedFunc() {
	var url = '/MultiPresentation/AJAX1';

	new Ajax.Request(url, {
		method : 'get',
		evalJSON: true,
		onSuccess : function(transport) {
			var notice = $('items');
			var z = transport.responseText.evalJSON();
			notice.innerHTML = z.date;
		}
	});
}

function onLoad() {
	timerId = setInterval(timedFunc, 2000);
}