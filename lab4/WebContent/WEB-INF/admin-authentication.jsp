<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<title>Login</title>
</head>
<body>


	<h1>Hello login servlet!</h1>
	<div class="container">
		<form action='../admin/auth' method='POST'>
			<label for='userName'>Username: </label> <input type='text'
				name='userName' id='userName'> <label for='password'>Password:
			</label> <input type='password' name='password' id='password'>
			<button>Login</button>
		</form>
	</div>

</body>
</html>