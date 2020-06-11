<%@page import="cs172.TweetLucene"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cs172.TweetLucene.*" %>

 <html>
 <head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <title>Search JSP</title>
 </head>
 <body>

<form action="Index.jsp" method = "GET">
                                <input type="text" name = "query" placeholder="Search for tweet..." required>
                                <input type="submit" name="submit" value="Search">
</form>

<% String mystring = request.getParameter("query");
%>
<style type="text/css">
table.example3 {background-color:transparent;border-collapse:collapse;width:100%;}
table.example3 th, table.example3 td {text-align:left;border:1px solid black;padding:5px;}
table.example3 th {background-color:AntiqueWhite;}
table.example3 td:first-child {width:20%;}
</style>
<table class="example3">
<tr>
<th colspan="2">Tweet Results</th>
</tr>
<tr>
<td width="20%">
yuh

</tr>
</table>

 </body>
 </html>
 
