
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
	       	if(window.location.pathname == "/shoppingcart/cart/") {
	       		render_cart();
        	};
        	
			$("#empty_cart").click( function(e) {
				e.preventDefault();
				empty_cart();
			});
		});
	
		function render_cart() {
			  $.ajax({
			        url: '/shoppingcart/ajax/render_cart/',
			        type: 'GET',
			        data: {
			        	asynchronous:true 
			        },
			        success: function(data) {;
			        	objJSON = $.parseJSON(data);
			        	$.each(objJSON, function() {
			        		render_data(this);
			        	});
			        }
			  });
		}
		
		function render_data(json) {
			$.each(json, function() {
				render_item(this);
        	});

			var data = document.getElementById('data');
			var name = document.createElement('div');
			name.setAttribute('style', 'clear:both;');
			data.appendChild(name);
			
			$(".buttom_add").click( function(e) {
				e.preventDefault();
				var code = $(this).attr('name');
				add_one(code);
			});

			$(".buttom_remove").click( function(e) {
				e.preventDefault();
				var code = $(this).attr('name');
				remove_one(code);
			});

		}
		
		function render_item(json) {
			var data = document.getElementById('data');
			
			var item = document.createElement('div');
			item.setAttribute('class', 'item');
			item.setAttribute('id', 'code' + json.id );

			var image = document.createElement('div');
			image.setAttribute('class', 'image');
			item.appendChild(image);
			image.innerHTML = '<img src="/shoppingcart/resources/images/' + json.image + '">';

			var name = document.createElement('div');
			name.setAttribute('class', 'name');
			item.appendChild(name);
			name.innerHTML = '<h3>' + json.name + '</h3>';
			
			var code = document.createElement('div');
			code.setAttribute('class', 'code');
			item.appendChild(code);
			code.innerHTML = 'code: ' + json.id;

			var price = document.createElement('div');
			price.setAttribute('class', 'price');
			item.appendChild(price);
			price.innerHTML = 'US$ ' + json.price;

			var quantity = document.createElement('div');
			quantity.setAttribute('class', 'quantity');
			item.appendChild(quantity);
			quantity.innerHTML = 'quantity: ' + json.quantity;
			
			var adjust = document.createElement('div');
			adjust.setAttribute('class', 'adjust');
			item.appendChild(adjust);

			var buttom_add = document.createElement('input');
			buttom_add.setAttribute('class', 'buttom_add');
			buttom_add.setAttribute('type', 'submit');
			buttom_add.setAttribute('value', '+');
			buttom_add.setAttribute('name', json.id);
			adjust.appendChild(buttom_add);

			var buttom_remove = document.createElement('input');
			buttom_remove.setAttribute('class', 'buttom_remove');
			buttom_remove.setAttribute('type', 'submit');
			buttom_remove.setAttribute('value', '-');
			buttom_remove.setAttribute('name', json.id);
			adjust.appendChild(buttom_remove);

			
			data.appendChild(item);

		}
		
		function empty_cart() {
			  $.ajax({
			        url: '/shoppingcart/ajax/empty_cart/',
			        type: 'GET',
			        data: {
			        	asynchronous:true
			        },
			        success: function(data) {
			        	if(window.location.pathname == "/shoppingcart/cart/") {
			        		$('.cart').html('');
			        	};
			        	alert(data);
			        }
			    });
		}

		function add_one(code) {
			  $.ajax({
			        url: '/shoppingcart/ajax/add_to_cart/' + code,
			        type: 'GET',
			        data: {
			        	asynchronous:true, 
			        	order_quantity: 1
			        },
			        success: function(data) {
			        	objJSON = $.parseJSON(data);
			        	var taken = objJSON.quantity;
			            $('#message').text(objJSON.message);
			            update_item_view(code, taken);
			        }
			    });
		}
		
		function update_item_view(code, taken) {
			if(taken > 0) {
				$('#code' + code).find('.quantity').text('quantity: ' + taken);
			} else {
				$('#code' + code).remove();
			}
		}
		
		function remove_one(code) {
			  $.ajax({
			        url: '/shoppingcart/ajax/remove_from_cart/' + code,
			        type: 'GET',
			        data: {
			        	asynchronous:true, 
			        	order_quantity: 1
			        },
			        success: function(data) {
			        	objJSON = $.parseJSON(data);
			        	var taken = objJSON.quantity;
			        	 $('#message').text(objJSON.message);
			        	 update_item_view(code, taken);
			        }
			    });
		}
		