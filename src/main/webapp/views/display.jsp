<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Product</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<div>
		<c:if test="${empty USERMODEL}">
			<a href="/login">Đăng nhập</a>
		</c:if>
		<c:if test="${not empty USERMODEL}">
			<a href="#">Xin chào, ${USERMODEL.fullname}</a>
			<a href="/product-home?action=logout">Đăng xuất</a>
		</c:if>
	</div>
	<form action="" class="container mt-4">
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">ID</th>
		      <th scope="col">Image</th>
		      <th scope="col">Name</th>
		      <th scope="col">Quantity</th>
		      <th scope="col">Price</th>
		      <th scope="col">Category ID</th>
		      <th scope="col" class="text-center">Handle Tool</th>
		    </tr>
		  </thead>
		  <tbody>
		    <c:forEach varStatus="loop" var="product" items="${products}">
		    	<tr>
			      <th scope="row">${loop.count}</th>
			      <td>${product.id}</td>
			      <td><img style="width: 100px;" src="${product.image}"></td>
			      <td>${product.name}</td>
			      <td>${product.quantity}</td>
			      <td>${product.price}</td>
			      <td>${product.categoryId}</td>
			      <td class="text-center">
			      	<a href="/product-home?action=add" class="btn btn-link">Add</a>
			      	<a href="/product-home?action=edit&id=${product.id}" class="btn btn-link">Edit</a>
			      	<a href="/product-home" class="btn btn-link btn-delete">Delete</a>
			      	<input type="hidden" class="var-id" value="${product.id}">
			      	<a class="redirect-home" href="/product-home"></a>
			      </td>
			    </tr>
		    </c:forEach>
		  </tbody>
		</table>
	</form>
	
	<script>
		var $ = document.querySelector.bind(document);
		var $$ = document.querySelectorAll.bind(document);
		
		var btnDelete = Array.from($$('.btn-delete'));
		var varId = $('.var-id');
		var redirectHome = $('.redirect-home');
		
		function deleteData(data) {
			fetch('http://localhost:8080/api-product', {
				method: 'DELETE', // or 'PUT'
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify(data),
			})
			.then(response => response.json())
			.then(data => {
			  	console.log('Success:', data);
			})
			.catch((error) => {
			  	console.error('Error:', error);
			});
		}
		
		btnDelete.forEach((element)=>{
			element.addEventListener('click', (e)=>{
				var res = confirm('Do you want delete for field this?');
				if(res){
					var data = {id: varId.value};
					deleteData(data);
					redirectHome.click();
				} else {
					e.preventDefault();
				}
			});
		});
		console.log(btnDelete);
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