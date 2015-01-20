package Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;

import entity.ManagedBeanUser;
import entity.ManagedBeanAdmin;


public class DatabaseConnection  {

	Connection con = null;
	Statement stmt = null;

	DatabaseConnection(){		
		try {			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("hello inside db construt");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yelp","root","$pBu195986");
			stmt = con.createStatement();
			if(!con.isClosed())
				System.out.println("Successfully Connected!!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ManagedBeanUser signInUser(String emailid, String pwd){
		ManagedBeanUser arylst=new ManagedBeanUser();
		//	int rowcount;
		try {
			String query="Select * from user where emailid='"+emailid+"'"+"and password='"+pwd+"'";
			System.out.println("inside Sign in");
			System.out.println(emailid+pwd);
			//String query = "Insert into customer (username, password) values ('" + userName + "', '" + pwd + "')";
			ResultSet rs=stmt.executeQuery(query);
			System.out.println("+++"+rs);

			if(rs.isBeforeFirst())
			{
				System.out.println("my if");
				while(rs.next()){

					arylst.setFirstname(rs.getString("firstname"));
					arylst.setLastname(rs.getString("lastname"));
					arylst.setEmailid(rs.getString("emailid"));
					arylst.setLogintime(rs.getString("logintime"));

					System.out.println(rs.getString("firstname"));
					System.out.println("Signin  Successful");




					arylst.setReturnmsg("True");
				}
				Date logintime=new Date();
				String queryTimeUpdate="update User set logintime='"+logintime+"' where emailid='"+emailid+"' and password='"+pwd+"' ";
				int i = 0;
				try {
					i = stmt.executeUpdate(queryTimeUpdate);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(i>0)
				{
					System.out.println("Time updated sucessfully");
				}
			}
			else{
				arylst.setReturnmsg("False");
				//result="false: The data could not be inserted in the database.";
				System.out.println("Signin  failed");
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//throw new SQLException("Entered user name or password is wrong");
			e.printStackTrace();
		}


		return arylst;
	}



	public String[] viewAdminCatecory(){
		String rest[]=new String [10];
		try {
			/*List<ManagedBeanAdmin> result=new ArrayList<ManagedBeanAdmin>();

		ManagedBeanAdmin mb=new ManagedBeanAdmin();*/

			int rowcount=0;
			String query2="Select * from adminreviewsnew";
			System.out.println("inside view admin Category");
			ResultSet rs=stmt.executeQuery(query2);
			while(rs.next()){

				rowcount++;

			}
			rs.beforeFirst();
			System.out.println(rowcount);
			rest=new String[rowcount];
			System.out.println(rest[0]);

			int i=0;
			while(rs.next()){

				rest[i]=rs.getString("ename");
				System.out.println(rest[i]);
				System.out.println(rs.getString("ename"));

				i++;


			}

			System.out.println("after get string");


			if(rs.getRow()>=0){
				System.out.println("Signin  Successful");
			}
			else{
				//result="false: The data could not be inserted in the database.";
				System.out.println("Signin  failed");
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rest;
	}

	public String viewUserReviews(String email){
		String rest=new String();
		try {
			/*List<ManagedBeanAdmin> result=new ArrayList<ManagedBeanAdmin>();

		ManagedBeanAdmin mb=new ManagedBeanAdmin();*/

			int rowcount=0;
			String query2="Select * from userreviewsnew where emailid='"+email+"'";
			System.out.println("inside view userreviews Category");
			ResultSet rs=stmt.executeQuery(query2);
			while(rs.next()){

				rowcount++;

			}
			rs.beforeFirst();
			System.out.println(rowcount);

			System.out.println(rest);

			int i=0;
			while(rs.next()){

				rest=rest+"Category:"+" "+rs.getString("ename")+" "+"Description:"+" "+rs.getString("edescription")+" "+"Ratings:"+" "+rs.getString("eratings")+" "+"Reviews"+" "+rs.getString("ereviews")+"-";
				System.out.println(rest);
				i++;


			}

			System.out.println("after get string");


			if(rs.getRow()>=0){
				System.out.println("Signin  Successful");
			}
			else{
				//result="false: The data could not be inserted in the database.";
				System.out.println("Signin  failed");
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rest;
	}




	public String signUpUser(String firstname, String lastname,String emailid,String password){
		String result = "";
		String logintime=null;
		int rowcount;
		try {
			Date date=new Date();
			logintime=date.toString();
			SimpleDateFormat ft = 
					new SimpleDateFormat ("hh:mm:ss");
			ft.format(date);

			System.out.println("Date is "+logintime);

			String query = "Insert into user (firstname,lastname,emailid,password,logintime) values ('" + firstname + "', '" + lastname+"','" + emailid+"','"+password+"','"+logintime+"')";
			//String query="Insert into user() emailid='"+emailid+"'"+"and password='"+pwd+"'";

			rowcount=stmt.executeUpdate(query);
			if(rowcount > 0){
				result="true";
				System.out.println("Insert Successful");
			}
			else{
				result="false: The data could not be inserted in the database.";
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String createCategory(String ename, String edescription,String eratings, String ereviews, String mailid) {
		// TODO Auto-generated method stub
		String result="";
		String logintime=null;
		System.out.println("create cateogry");
		int rowcount;
		try {

			System.out.println("Date is "+logintime);

			String query = "Insert into adminreviewsnew (ename,edescription,eratings,ereviews,mailid) values ('" + ename+ "', '" +edescription+"','" +eratings+"','"+ereviews+"','"+mailid+"')";
			//String query="Insert into user() emailid='"+emailid+"'"+"and password='"+pwd+"'";
			//int i=0;
			System.out.println("hi");
			int i=stmt.executeUpdate(query);
			/*while(rs.next())
			{
				i++;
			}*/
			if( i> 0){
				result="true";
				System.out.println("Insert Successful");
			}
			else{
				result="false: The data could not be inserted in the database.";
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String deleteCategory(String ename) {
		// TODO Auto-generated method stub
		String result = "";
		String logintime=null;
		int rowcount;
		try {
			Date date=new Date();
			logintime=date.toString();
			SimpleDateFormat ft = 
					new SimpleDateFormat ("hh:mm:ss");
			ft.format(date);

			System.out.println("Date is "+logintime);

			String query = "Delete from adminreviewsnew where ename='"+ename+"'";
			//String query="Insert into user() emailid='"+emailid+"'"+"and password='"+pwd+"'";

			rowcount=stmt.executeUpdate(query);
			System.out.println(rowcount);
			if(rowcount > 0){
				result="true";
				System.out.println("Delete Successful");
			}
			else{
				result="false: The data could not be deleted from the database.";
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public String updateCategory(String ename, String edescription,String eratings,String ereviews,String mailid) {
		// TODO Auto-generated method stub
		String result = "";
		String logintime=null;
		int rowcount;
		try {
			Date date=new Date();
			logintime=date.toString();
			SimpleDateFormat ft = 
					new SimpleDateFormat ("hh:mm:ss");
			ft.format(date);

			System.out.println("Date is "+logintime);

			String query = "Update adminreviewsnew set ename='"+ename+"',"+"edescription='"+edescription+"',"+"eratings='"+eratings+"',"+"ereviews='"+ereviews+"',"+"mailid='"+mailid+"'"+"where mailid='"+mailid+"'"+"and ename='"+ename+"'";
			//String query="Insert into user() emailid='"+emailid+"'"+"and password='"+pwd+"'";

			rowcount=stmt.executeUpdate(query);
			if(rowcount > 0){
				result="true";
				System.out.println("Update Successful");
			}
			else{
				result="false: The data could not be inserted in the database.";
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public ManagedBeanUser signInAdmin(String emailid, String pwd){
		ManagedBeanUser arylst=new ManagedBeanUser();
		//	int rowcount;
		try {
			String query="Select * from admin where emailid='"+emailid+"'"+"and password='"+pwd+"'";
			System.out.println("inside Sign in");
			System.out.println(emailid+pwd);
			//String query = "Insert into customer (username, password) values ('" + userName + "', '" + pwd + "')";
			ResultSet rs=stmt.executeQuery(query);
			System.out.println("+++"+rs);

			if(rs.isBeforeFirst())
			{
				
				while(rs.next()){

					arylst.setFirstname(rs.getString("firstname"));
					arylst.setLastname(rs.getString("lastname"));
					arylst.setEmailid(rs.getString("emailid"));
					arylst.setLogintime(rs.getString("logintime"));

					System.out.println(rs.getString("firstname"));
					System.out.println("Signin  Successful");




					arylst.setReturnmsg("True");
				}
				Date logintime=new Date();
				String queryTimeUpdate="update admin set logintime='"+logintime+"' where emailid='"+emailid+"' and password='"+pwd+"' ";
				int i = 0;
				try {
					i = stmt.executeUpdate(queryTimeUpdate);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(i>0)
				{
					System.out.println("Time updated sucessfully");
				}
			}
			else{
				arylst.setReturnmsg("False");
				//result="false: The data could not be inserted in the database.";
				System.out.println("Signin  failed");
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//throw new SQLException("Entered user name or password is wrong");
			e.printStackTrace();
		}


		return arylst;
	}



}
