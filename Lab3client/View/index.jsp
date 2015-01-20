<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel='stylesheet' href='./style.css' />
 <link rel="shortcut icon" type="image/x-icon" href="jboss.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Yelp</title>
</head>
<body>
<body>
	<form id="form1" action="" method="post">
		<div id="head">
			<h1 style="color: white; margin: 30px">Yelp Clone</h1>
			<label
				style="color: white; position: relative; left: 320px; bottom: 30px">User
				ID</label> <input type="text" name="loginid"
				style="position: relative; left: 350px; width: 200px; height: 30px; bottom: 30px">
			<label
				style="color: white; position: relative; left: 370px; bottom: 30px">Password</label>
			<input type="password" name="password"
				style="position: relative; left: 400px; width: 200px; height: 30px; bottom: 30px">
			<input type="submit" name="login" formaction="loginUser"
				style="position: relative; left: 420px; width: 100px; height: 30px; bottom: 30px"
				value="SignIn"> <input type="submit" name="login"
				formaction="loginAdmin"
				style="position: relative; left: 420px; width: 180px; height: 30px; bottom: 30px"
				value="SignInasAdmin"> <br>
			<h4 style="color: red;">

				<%
					if (request.getAttribute("msg") != null)
						out.print(request.getAttribute("msg"));
				%>
			</h4>
		</div>

		<div></div>


		<div id="content">
			<p>Welcome to Yelp Application</p>
Lorem ipsum dolor sit amet, consectetur adipiscing e sed do eiusmod
tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex
ea c consequat. Duis aute irure dolor in reprehenderit in velit esse
cillum dolore eu fugiat nulla pariatur. E sint occaecat cupidatat non
proident, sunt in culpa deserunt mollit anim id est laborum
		</div>
		<div id="register">
			<br>New Users Register Here<br>
			<br>
			<br>
			<br>
			<div id="errormsg" style="color: red"></div>
			<div id="al">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label
					id="al">First Name:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" name="firstname" id="firstname"
					placeholder="Enter First Name" pattern="[A-Za-z]{20}" title="cannot be empty"><br>
				<br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label
					id="al">Last Name:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" name="lastname" id="lastname"
					placeholder="Enter Last Name"  pattern="[A-Za-z]{20}" title="cannot be empty"> <br>
				<br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label id="al">Email Id:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="email" name="emailid" id="emailid"
					placeholder="Enter Email ID" pattern="[A-Za-z @]{20}" title="Should be in proper email format example pbustudy@gmail.com"> <br>
				<br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label
					id="al">Password :</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="password" name="password" id="password"
					placeholder="Enter password" pattern="[A-Za-z]{20}" title="cannot be empty"><br>
				<br>
				<br>


				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" name="signup" formaction="signup"
					value="SignUp" onclick="validate()">
			</div>
		</div>

	</form>
	<script type="text/javascript">
		function validate() {
			var flag = true;
			var msg="";
			var firstname = document.forms["form1"]["firstname"].value;
			var lastname = document.forms["form1"]["lastname"].value;
			var emailid = document.forms["form1"]["emailid"].value;
			var password = document.getElementById("password").value;
			alert(password)
			if (firstname == null || firstname == "") {
				flag = false;
				msg=msg+"Firstname is empty!"
			}
			if (lastname == null || lastname == "") {
				flag = false;
				msg=msg+"Lastname is empty!"
			}
			if (emailid == null || emailid == "") {
				flag = false;
				msg=msg+"Emailid is empty!"
			}
			if (password == null || password == "") {
				flag = false;
				msg=msg+"Password is empty!"
			}
			if (flag) {
				alert(flag);
				document.getElementById("form1").submit();

			} else {
				alert(flag);
				alert(msg);
				document.getElementById("errormsg").innnerHTML="msg";

			}

		}
	</script>

</body>
</body>
</html>