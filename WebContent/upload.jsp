<html>
<head>
<title>Upload slide</title>
</head>
<body>
<form action="fileupload" method="post" enctype="multipart/form-data">
<label for="cue">Cue: </label>
<input id="cue" name="cue" size="10" />
<br/>
<label for="cue">Fade Time: </label>
<input id="fade" name="fade" size="10" value="1.0" />
<span>Changes fade time for all displays</span>
<br/>
<label for="display">Display: </label>
<input id="display" name="display" size="10" />
<br>
<label for="type">Type: </label>
<select id="type" name="type">
<option value="IMAGE">Image</option>
<option value="MOVIE">Movie</option>
<option value="BLANK">Blank</option>
<option value="TRACK">Track</option>
</select>
<br/>
<label for="file">File: </label>
<input id="file" name="file" type="file" />

<br/>
<input type="submit" />
</form>
</body>
</html>