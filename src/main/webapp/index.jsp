<h1>Demo : Write Into Volume</h1>
<form action="volumeServlet" method="post">
	<table style="with: 50%">
		<tr>
			<td>Mount Path</td>
			<td><input type="text" name="volume_name" /></td>
		</tr>
		<tr>
			<td>File Name</td>
			<td><input type="text" name="file_name" /></td>
		</tr>
		<tr>
			<td>Contents To Add</td>
			<td><input type="text" name="contents_add" size="50"/></td>
		</tr>
	</table>
	<input type="submit" value="Write-Into" />
	<INPUT TYPE="RESET" value="RESET-FFF">
</form>

