<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Product</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>

	<form class="container mt-4 form-submit">
		<div class="form-group">
			<label for="formGroupExampleInput2">Category ID</label> 
			<select class="form-control form-control-sm" name="categoryId">
				<option value="">-- Choose Option --</option>
				<c:forEach var="category" items="${categorys}">
					<option class="optionId" value="${category.id}">${category.name}</option>
				</c:forEach>
			</select>
		<input type="hidden" id="categoryIdHidden" value="${product.categoryId}">
		</div>
		
		<div class="form-group">
			<label for="id">ID</label> 
			<input type="text" class="form-control" id="id" name="id" value="${product.id}">
		</div>
		
		<div class="form-group">
			<label for="name">Name</label> 
			<input type="text" class="form-control" id="name" name="name" value="${product.name}">
		</div>
		
		<div class="form-group">
			<label for="quantity">Quantity</label> 
			<input type="text" class="form-control" id="quantity" name="quantity" value="${product.quantity}">
		</div>
		
		<div class="form-group">
			<label for="price">Price</label> 
			<input type="text" class="form-control" id="price" name="price" value="${product.price}">
		</div>
		
		<div class="form-group">
			<label for="image">Image</label> 
			<input type="text" class="form-control" id="image" name="image" value="${product.image}">
		</div>
		
		<button type="submit" class="btn btn-primary btn-update">Update Product</button>
		
	</form>
	<a type="hidden" class="redirect-home" href="/api-product"></a>
	<script>
		var $ = document.querySelector.bind(document);
		var $$ = document.querySelectorAll.bind(document);
		
		var optionId = Array.from($$('.optionId'));
		var categoryIdHidden = $('#categoryIdHidden');
		var btnUpdate = $('.btn-update');
		var formSubmit = $('.form-submit');
		var redirectHome = $('.redirect-home');
		
		console.log(optionId);
		
		console.log(categoryIdHidden);
		
		optionId.forEach((element)=>{
			if(element.value == categoryIdHidden.value){
				element.setAttribute('selected', 'selected');
			} else {
				element.removeAttribute('selected');
			}
		});
		
		function updateData(data) {
			fetch('http://localhost:8080/api-product',{
				method: 'PUT',
				headers:{
					'Content-Type': 'application/json'
				},
				redirect: 'follow',
				body: JSON.stringify(data)
			})
			.then((response) => {response.json()})
			.then((data) => {console.log(data)});
		}
		
		btnUpdate.addEventListener('click', (e)=>{
			e.preventDefault();
			var form = new FormData(formSubmit);
			
			var id = form.get('id');
			var name = form .get('name');
			var quantity = form.get('quantity');
			var price = form.get('price');
			var image = form.get('image');
			var categoryId = form.get('categoryId');
			
			var data = {id, name, quantity, price, image, categoryId};
			
			updateData(data);
			
			redirectHome.click();
		});
		
	</script>


























	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>