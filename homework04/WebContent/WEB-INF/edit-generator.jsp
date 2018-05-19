<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../app.css ">
<title>Edit-Generator</title>
</head>
<body>

	<h1>Edit Generator</h1>
	<div class="nav-bar"></div>
	<div class="container">
		<div class="left-side">
			<form class="event-form" action="../admin/EditGenerator?id=${gen.getId()}" method="post">
					<label for="generatorName">Generator Name:</label> 
					<input type="text" name="name" id="generatorName" class="form-input" value="${gen.getName()}"> 
					
					<label for="generatorRate">Generator Rate</label>
					<input type="number" name="rate" id="generatorRate" class="form-input" value="${gen.getRate()}"> 
					
					<label for="baseCost">Base Cost</label> 
					<input type="number" name="baseCost" id="baseCost" class="form-input" value="${gen.getBaseCost()}">
					 
					<label for="unlock">Unlock At</label> 
					<input type="number" name="unlock" id="unlock"
					class="form-input" value="${gen.getUnlockAt()}"> 
					
					<label for="generatorDescription">Generator Description</label>
					<textarea id="generatorDescription" name="descTextArea">${gen.getDescription()}</textarea>
				
					<button class="form-submit" type="submit">Edit</button>
			</form>
		</div>
	</div>

</body>
</html>