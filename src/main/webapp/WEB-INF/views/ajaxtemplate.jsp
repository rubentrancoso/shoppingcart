<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>


<%@ page import="com.company.shoppingcart.core.Functions"  %>

<% 
	Functions.init(request, out);
%>
${result}