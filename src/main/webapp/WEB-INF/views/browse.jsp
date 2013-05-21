<%@page import="com.company.shoppingcart.core.Functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<h1>
	Browse  
</h1>

<P>browse products</P>

<div class="inventory">
	<c:forEach var="item" items="${inventory}">
		<c:if test="${item[3]>0}">
		      <div class="item">
			      <div class="name"><h3><a href="<%= Functions.get_the_context() %>details/?code=${item[0]}">${item[1]}</a></h3></div>
			      <div class="price">U$ ${item[2]}</div>
			      <div class="quantity">${item[3]} available</div>
			      <div class="image"><a href="<%= Functions.get_the_context() %>details/?code=${item[0]}"><img src="<%= Functions.get_the_context() %>resources/images/${item[4]}"></a></div>
 				  <div class="file">${item[4]}</div>
		      </div>
		</c:if>
	</c:forEach>
	<div style="clear:both;"></div>
</div>

