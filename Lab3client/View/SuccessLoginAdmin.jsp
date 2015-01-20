<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel='stylesheet' href='./style.css' />
 <link rel="shortcut icon" type="image/x-icon" href="jboss.ico" />
<title>Yelp</title>
</head>
<body>

<form id="form1" action="" method="post">
<div id="head">
<h1 style="color: white; margin: 30px">Yelp Clone</h1>

<input type="submit" style="position:relative; left:1100px; width:100px; height:30px; bottom: 30px" value="Sign Out" formaction="logout">

</div><br>
&nbsp;&nbsp;&nbsp;<input type="submit" name="createcategory" formaction="displaycategory" value="createcategory" style="border: none; background-color:green; color: white" >&nbsp;&nbsp;&nbsp;
<input type="submit" name="updatecategory" formaction="displayupdatecategory" value="updatecategory" style="border: none; background-color:green; color: white">
<input type="submit" name="deletecategory" formaction="displaydeletecategory" value="DeleteCategory" style="border: none; background-color:green; color: white">
	<div>
	
	<h4>Welcome&nbsp;&nbsp;&nbsp;!!<%= session.getAttribute("firstname") %>&nbsp;&nbsp;&nbsp;<%= session.getAttribute("lastname") %></h4>
	<h4>Last Logged in Time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= session.getAttribute("logintime") %><br></h4>
	</div>

<div id="content">


</div>
</form>
<form id="form2" action="" method="get">

<div>


</div>
</form>
<script type="text/javascript" >
form=document.getElementById("form1");
function logout()
{
	form.action="/signout";
	form.submit();
	
	}

</script>
</body>
</html>