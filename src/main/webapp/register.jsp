<html>
<link rel="stylesheet" href="css/register.css">
<body>
	<h2>Registration Form</h2>
	<p>Restrictions: Username and Password length>4 but length <20 and can only use letters or numbers</p>

	<form action ="register" method="post">
	
		<label for="email"> Email:</label>
		<input type="text" id="email" name="email"><br>
		
		<label for="username"> Username:</label>
		<input type="text" id="username" name="username"><br>
		
		<label for="password"> Password:</label>
		<input type="password" id="password" name="password"><br>
		
		<input type="submit" value="Submit">
	</form><br>
	<a href="index.jsp">Login Here</a>
</body>
</html>
