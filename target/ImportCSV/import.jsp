<html>
<head>
    <title>Import</title>
</head>
<body>
<form enctype="multipart/form-data" method="POST" action="import.do">
    <p><b>CSV File:</b></p>

    <p><input type="file" name="csvfile" id="csvfile" size="100"></p>
    <input type="submit" value="Upload">

    <p>${success}</p>
</form>
</body>
</html>
