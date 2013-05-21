<%@page import="com.company.shoppingcart.core.Functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<h1>
	Detail 
</h1>

<P>see the details</P>

<div class="detail">
	<div class="info">
		<div class="name"><h2>${item[1]}</h2></div>
		<div class="price">U$ ${item[2]}</div>
		<div class="quantity" id="available">${item[3]} available</div>
	</div>
	<div class="image"><img src="<%= Functions.get_the_context() %>resources/images/${item[4]}"></div>
	<div class="order">
	
			<input id="quantity" type="text" name="quantity" value="0" maxlength="3" size="3">
			<input id="add" type="submit" name="add_to_cart" value="add to cart">
			
	</div>
	<div id="message">&nbsp;</div>
</div>
<div style="clear: both;"></div>

	<script>
		$.ajaxSetup ({  
		    cache: false  ,
		    error: function(jqXHR, exception) {
	            if (jqXHR.status === 0) {
	            	$('#message').text(jqXHR.status);
	                //alert('Not connect.\n Verify Network.');
	            } else if (jqXHR.status == 404) {
	            	$('#message').text(jqXHR.status);
	                //alert('Requested page not found. [404]');
	            } else if (jqXHR.status == 500) {
	            	$('#message').text(jqXHR.status);
	                //alert('Internal Server Error [500].');
	            } else if (exception === 'parsererror') {
	            	$('#message').text(jqXHR.status);
	                //alert('Requested JSON parse failed.');
	            } else if (exception === 'timeout') {
	            	$('#message').text(jqXHR.status);
	                //alert('Time out error.');
	            } else if (exception === 'abort') {
	            	$('#message').text(jqXHR.status);
	                //alert('Ajax request aborted.');
	            } else {
	            	$('#message').text(jqXHR.responseText);
	               //alert('Uncaught Error.\n' + jqXHR.responseText);
	            }
	        }		   
		}); 
		
		$(document).ready( function() {
			$("#add").click( function() {
				add_to_cart();
			});
		});
	
		function add_to_cart() {
			  $.ajax({
			        url: '/shoppingcart/ajax/add_to_cart/${code}',
			        type: 'GET',
			        data: {
						order_quantity: $('#quantity').val()
			        },
			        success: function(data) {
			        	objJSON = $.parseJSON(data);
			        	$('#available').text(objJSON.available + ' available');
			            $('#message').text(objJSON.message);
			        }
			    });
		}
	</script>

