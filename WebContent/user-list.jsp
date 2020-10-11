<%@ page import="supplier.web.Common" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Isuru Pharmacy Store Keeper Dashboard</title>
	<link rel="stylesheet" href="<%=Common.url%>/assets/styles.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

<%--	<header>--%>
<%--		<nav class="navbar navbar-expand-md navbar-dark"--%>
<%--			style="background-color: tomato">--%>
<%--			<div>--%>
<%--				<a href="https://www.javaguides.net" class="navbar-brand"> Supplier--%>
<%--					Management</a>--%>
<%--			</div>--%>

<%--			<ul class="navbar-nav">--%>
<%--				<li><a href="<%=request.getContextPath()%>/list"--%>
<%--					class="nav-link">Suppliers</a></li>--%>
<%--			</ul>--%>
<%--		</nav>--%>
<%--	</header>--%>

<input type="checkbox" id="nav_animation">


<header>

	<label for="nav_animation">
		<i class="fas fa-bars" id="sidebar_btn"></i>
	</label>

	<div class="company_name">
		<h3>Isuru <span>Pharmacy</span> </h3>
	</div>

	<div class="logout">
		<a href="#" class="logout_btn">Logout</a>
	</div>

</header>

<div class="sidenav">
	<center>
		<img src="<%=Common.url%>/assets/st1.png" class="user_type" alt=""/>
		<h4>Assistant</h4>
	</center>
	<a href="<%=request.getContextPath()%>/list"><i class="fa fa-archive" aria-hidden="true"></i><span>Supplier</span></a>
	<a href="<%=request.getContextPath()%>/new"><i class="fa fa-plus" aria-hidden="true"></i><span>Add Supplier</span></a>
	<a href="<%=request.getContextPath()%>/report-one"><i class="fa fa-plus" aria-hidden="true"></i><span>Supplier Report</span></a>
<%--	<a href="#"><i class="fa fa-archive" aria-hidden="true"></i><span>Stocks</span></a>--%>
<%--	<a href="#"><i class="fa fa-plus" aria-hidden="true"></i><span>Add Stocks</span></a>--%>
<%--	<a href="#"><i class="fa fa-cogs" aria-hidden="true"></i><span>Update Stocks</span></a>--%>
<%--	<a href="#"><i class="fa fa-database" aria-hidden="true"></i><span>Orders</span></a>--%>
<%--	<a href="#"><i class="fa fa-table" aria-hidden="true"></i><span>View Orders</span></a>--%>
<%--	<a href="#"><i class="fa fa-cogs" aria-hidden="true"></i><span>Update Orders</span></a>--%>

</div>

<div class="workspace">
	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Suppliers</h3>
			<hr>
			<div class="container text-left mtop-x">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Supplier</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Company</th>
					<th>Actions</th>
				</tr>
				</thead>
				<tbody>
				<!--   for (Todo todo: todos) {  -->
				<c:forEach var="supplier" items="${listUser}">

					<tr>
						<td><c:out value="${supplier.id}" /></td>
						<td><c:out value="${supplier.name}" /></td>
						<td><c:out value="${supplier.email}" /></td>
						<td><c:out value="${supplier.company}" /></td>
						<td><a href="edit?id=<c:out value='${supplier.id}' />">Edit</a>
							&nbsp;&nbsp;&nbsp;&nbsp; <a
									href="delete?id=<c:out value='${supplier.id}' />">Delete</a></td>
					</tr>
				</c:forEach>
				<!-- } -->
				</tbody>

			</table>
		</div>

	</div>
</div>

<footer>
	<div class="info">
		<h5>Email	IsuruPharmacy@gmail.com<span id="sp1">Address        43/A,</span></h5>
		<h5>Contact No	011 8567890<span id="sp2">New Kandy Road,</span></h5>
		<h5>WhatApp	077 3456766<span id="sp3">Battaramulla</span></h5>
		<h5>Viber	071 5568232</h5>
	</div>

</footer>
</body>
</html>
