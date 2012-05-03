<html>
<head>
<title>Upload slide</title>
</head>
<body>
<form action="fileupload" method="post" enctype="multipart/form-data">
<label for="cue">Cue: </label>
<input id="cue" name="cue" size="10" />
<br/>
<label for="display">Display: </label>
<input id="display" name="display" size="10" />
<br>
<label for="type">Type: </label>
<select id="type" name="type">
<option value="IMAGE">Image</option>
<option value="MOVIE">Clip</option>
</select>
<br/>
<label for="file">File: </label>
<input id="file" name="file" type="file" />

<br/>
<input type="submit" />
</form>
</body>
</html>