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
<form id="form1" action="" method="post">
<div id="head">
<h1 style="color: white; margin: 30px">Yelp Clone</h1>

<input type="submit" style="position:relative; left:1100px; width:100px; height:30px; bottom: 30px" value="Sign Out" formaction="logout">
</div>
<br><button type="button" onclick="goBack()">Go Back</button>
<h2>Delete Category</h2>


<div style="margin:20px; left:20px">

<%-- <select name="categoryname">
 <%
 String[] results=(String [])session.getAttribute("categoryName");
 for(int i=0;i<results.length;i++)
   {%>
           <option><%=results[i]%></option>
           
     
   <%}%>
</select> --%>
<h3 style="color: blue;"><% out.print(request.getAttribute("msg"));%></h3>
<!-- <input type="submit" name="createdelete" formaction="createdelete" value="Delete"> -->


</div>
</form>

<script type="text/javascript" >

function goBack() {
	document.location.href="SuccessLoginAdmin.jsp";
}

</script>
</body>
</html>