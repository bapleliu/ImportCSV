<html>
<head>
    <title>Import</title>
</head>
<body>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <td colspan="2" bgcolor="#D3EDF6" align="center"><a href="index.jsp"> Menu:</a></td>
        <td colspan="2" bgcolor="#D3EDF6" valign="top" align="center"><a href="overview.do">List overview</a></td>
        <td colspan="2" bgcolor="#D3EDF6" valign="top" align="center"><a href="import.jsp">Import CSV</a></td>
    </tr>
</table>
<form enctype="multipart/form-data" method="POST" action="import.do">
    <p><b>CSV File:</b></p>

    <p><input type="file" name="csvfile" id="csvfile" size="100"></p>
    <input type="submit" value="Upload">

    <p>${success}</p>
</form>
</body>
</html>
