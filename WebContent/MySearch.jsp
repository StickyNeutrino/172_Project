<%@page import="lucene.App"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="lucene.App.*" %>

 <html>
 <head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <title>Search Engine!!</title>
 </head>
 <body>


<form action="index.jsp" method = "GET">
                                <input type="text" name = "query" placeholder="Enter a query!" required>
                                <input type="submit" name="submit" value="Search">
</form>

<% String mystring = request.getParameter("query");
%>
<style type="text/css">
table.example3 {background-color:white;border-collapse:collapse;width:100%;}
table.example3 th, table.example3 td {text-align:left;border:1px solid red;padding:5px;}
table.example3 th {background-color:white;}
table.example3 td:first-child {width:20%;}
<!-- CSS only -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
</style>
<table class="example3">
<tr>
<th colspan="5">Tweeter Search Results.... </th>
</tr>
<tr>
<td width="40%">
<%String[] results;
if (mystring == null) { 
}
else {
	results = App.search(mystring, 10);

	for (int i = 0; i < results.length; i++) {
		out.println(results[i] +"<br/><br/>");
		}
}
%>
</tr>
</table>

 </body>
 </html>
 
