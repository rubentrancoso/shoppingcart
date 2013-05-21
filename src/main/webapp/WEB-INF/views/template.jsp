<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>


<%@ page import="com.company.shoppingcart.core.Functions"  %>

<% 
	Functions.init(request, out);
	Functions.appendStyle("resources/css/style.css");
	Functions.appendScript("//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js");
	Functions.appendLocalScript("resources/js/commom.js");
%>
<html>
	<head><% Functions.the_header(); %></head>
	<body>
		<div class="page">
			<div class="header">
				<jsp:include page="header.jsp"/>
			</div>
			<div style="clear: both;"></div>
			<div class="body">
				<jsp:include page='${body}.jsp' />
			</div>
			<div style="clear: both;"></div>
			<div class="footer">
				<jsp:include page="footer.jsp"/>
			</div>
		</div>
	<body>
</html>