<%@page import="java.net.InetAddress" %>
<%@page import="java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
    border: 1px solid black;
}
</style>
</head>
<body>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<h3>Listing Files From Mount-Path : ${volume_name}</h3>

<table style="width:100%">
  <tr>
    <th>File Name</th>
    <th>Contents</th>
  </tr>
  <%
   Object obj = request.getAttribute("contents_output");
   System.out.println(obj);
   Map<String,String> mapp = ((Map<String,String>)obj);
   System.out.println(mapp);
   for (Map.Entry<String, String> entry : mapp.entrySet()) {
    String key = entry.getKey();
    String value = entry.getValue();
  %>
  <tr>
    <td width="20%">
		<%= key%>
	</td>
    <td width="80%">
    <textarea rows="5" style="border: none; width: 100%; -webkit-box-sizing: border-box; -moz-box-sizing: border-box; box-sizing: border-box;"> <%= value%> </textarea>
    
  </tr>
<% } %>
  
</table>
<input type=button value="Back to writing.." onCLick="history.back()">
</body>
</html>
Hello from Java!<%out.println(System.getenv("APAAS_CONTAINER_NAME"));%>
