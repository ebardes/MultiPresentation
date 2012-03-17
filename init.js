var sys = new org.bardes.config.DefCue();

function q(cue, opts)
{
	sys.define(cue, opts);
	return sys;
}

function img(name)
{
	return 1;
}

var blank = new org.bardes.config.Blank();
function track(){};