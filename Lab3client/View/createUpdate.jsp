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
<h2>Update Category</h2>


<div style="margin:20px; left:20px">
<label> Category Name</label>
<select name="categoryname">
 <%
 String[] results=(String [])session.getAttribute("categoryName");
 for(int i=0;i<results.length;i++)
   {%>
           <option><%=results[i]%></option>
           
     
   <%}%>
</select><br>
<label>Description</label><br>
<textarea rows="5" cols="100" placeholder="Enter the description" name="categorydescription"></textarea><br>
<label>Range(1-5)</label><br>

<input type="range" min="1" max="5" onchange="range1(this.value)" name="categoryratings"> <span id="valBox" style="position:relative; bottom:6px"></span><br>

<label>Reviews</label><br>
<textarea rows="5" cols="100" placeholder="Enter the description" name="categoryreviews" ></textarea><br><br>
<!-- <input type="submit" name="createcategory" formaction="createcategory" value="createcategory"> -->
<input type="submit" name="createupdate" formaction="createupdate" value="Update">
<button type="reset" >Reset</button>

</div>
</form>

<script type="text/javascript" >

function goBack() {
	document.location.href="SuccessLoginAdmin.jsp";
}

</script>
</body>
</html>