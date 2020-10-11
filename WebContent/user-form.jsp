
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
	<div class="container col-md-5">
		<div class="card mtop-y">
			<div class="card-body">
				<c:if test="${supplier != null}">
				<form action="update" method="post">
					</c:if>
					<c:if test="${supplier == null}">
					<form action="insert" method="post">
						</c:if>

						<caption>
							<h2>
								<c:if test="${supplier != null}">
									Edit Supplier
								</c:if>
								<c:if test="${supplier == null}">
									Add New Supplier
								</c:if>
							</h2>
						</caption>

						<c:if test="${supplier != null}">
							<input type="hidden" name="id" value="<c:out value='${supplier.id}' />" />
						</c:if>

						<fieldset class="form-group">
							<label>Supplier Name</label> <input type="text"
																value="<c:out value='${supplier.name}' />" class="form-control"
																name="name" required="required">
						</fieldset>

						<fieldset class="form-group">
							<label>Supplier Email</label> <input type="text"
																 value="<c:out value='${supplier.email}' />" class="form-control"
																 name="email">
						</fieldset>

						<fieldset class="form-group">
							<label>Supplier Company</label> <input type="text"
																   value="<c:out value='${supplier.company}' />" class="form-control"
																   name="company">
						</fieldset>

						<button type="submit" class="btn btn-success">Save</button>
					</form>
			</div>
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
