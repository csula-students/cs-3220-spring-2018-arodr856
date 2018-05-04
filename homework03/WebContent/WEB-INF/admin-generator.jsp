<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' type='text/css' href='../app.css'>
<title>Insert title here</title>
</head>
<body>

	<h1>Incremental Game Framework</h1>
	<div class="nav-bar">
		<nav class='nav-b'> <a href="#" class="nav-item">Game
			Information</a> | <a href="generators" class="nav-item">Generators</a> |
		<a href="events" class="nav-item">Events</a> | <a href="LogOut"
			class="nav-item">Log out</a> </nav>
	</div>
	<div class="container">
		<div class="left-side">
			<form class="event-form" action="generators" method="post">
				<label for="generatorName">Generator Name:</label> <input
					type="text" name="name" id="generatorName" class="form-input">
				<label for="generatorRate">Generator Rate</label> <input
					type="number" name="rate" id="generatorRate" class="form-input">
				<label for="baseCost">Base Cost</label> <input type="number"
					name="baseCost" id="baseCost" class="form-input"> <label
					for="unlock">Unlock At</label> <input type="number" name="unlock"
					id="unlock" class="form-input"> <label
					for="generatorDescription">Generator Description</label>
				<textarea id="generatorDescription" name="descTextArea"></textarea>
				<button class="form-submit" type="submit">Add</button>
			</form>
		</div>
		<div class="right-side">
			<table class="event-table">
				<tr>
					<th>Name</th>
					<th>Rate</th>
					<th>Cost</th>
					<th>Unlock At</th>
					<th>Action</th>
				</tr>
				<c:forEach items='${generators}' var='gen'>
					<tr>
						<td>${gen.getName()}</td>
						<td>${gen.getRate()}</td>
						<td>${gen.getBaseCost()}</td>
						<td>${gen.getUnlockAt()}</td>
						<td><a href='../admin/EditGenerator?id=${gen.getId()}'>Edit</a>
							<a href='../admin/DeleteGenerator?id=${gen.getId()}'>delete</a></td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>

</body>
</html>