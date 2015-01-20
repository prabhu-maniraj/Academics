 package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.ManagedBeanAdmin;

//import Connection.Signup_cmdProxy;



public class signinAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Signup_cmdProxy proxy=new Signup_cmdProxy();
	
    
ManagedBeanAdmin admin = new ManagedBeanAdmin();
	 private Connection connection;
	 private Session session;
	 private Queue queue;
	 private Queue sendQueue;
	 private MessageConsumer consumer;
    
    public signinAdmin() {
        super();
        Properties properties = new Properties();
	    properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
	    properties.put(Context.URL_PKG_PREFIXES, "org.jnp.interfaces");
	    properties.put(Context.PROVIDER_URL, "localhost");
	    
		try {
			InitialContext jndiContext = new InitialContext(properties);
			ConnectionFactory confactory=(ConnectionFactory)jndiContext.lookup("XAConnectionFactory");
			queue=(Queue) jndiContext.lookup("queue");
			connection=confactory.createConnection();
			session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			connection.start();
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JMSException e) {
			// TODO: handle exception
			e.printStackTrace();
	}

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("servlet_signin");
		System.out.println("inside the servlet");
		//String fname=request.getParameter("fname");
		//String lname=request.getParameter("lname");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		//boolean result=false;
		try{
			System.out.println("inside the srvlet");
			//String userid=request.getParameter("userid");
			//String pass=request.getParameter("pass");
			if(email.contains(";") || password.contains(";"))
			{
				System.out.println("sorry it contains ;");
				response.sendRedirect("http://localhost:8080/YelpApplicationJMSClient/view/index.jsp");
			}
			
			try
			{			
				//String user = request.getParameter("userName");
				//String pass = request.getParameter("password");
				MessageProducer MP;
				MP = session.createProducer(queue);
				TextMessage TM = session.createTextMessage("");
				sendQueue = session.createTemporaryQueue();
				consumer = session.createConsumer(sendQueue);
				TM.setJMSReplyTo(sendQueue);
				TM.setText(email+";;"+password);
				TM.setJMSCorrelationID("signInAdmin");
				MP.send(TM);
				System.out.println("mp sended");
				TextMessage Reply = (TextMessage)consumer.receive();
				String result = Reply.getText();
				HttpSession httpsession = (HttpSession) request.getSession();
				if(result.startsWith("false"))
				{
					/*httpsession.setAttribute("userSession", session);
					httpsession.setAttribute("uniqueId",user);
					TM.setJMSReplyTo(replyqueue);
					TM.setText("marketSignIn;"+ user + ";" + pass);
					MP.send(TM);
					Reply = (TextMessage)consumer.receive();
					String lastLogin = Reply.getText();
					//String lastLogin=proxy.getsetDate(user);
					httpsession.setAttribute("lastlogin",lastLogin);
					String destination="Home"; 
					RequestDispatcher rd = request.getRequestDispatcher(destination);
					rd.forward(request, response);*/
					String [] data = result.split(";;");
					System.out.println("signed in submitted");
					HttpSession session=request.getSession();
					session.setAttribute("email", email);
					session.setAttribute("time_log", data[1]);
					response.sendRedirect("http://localhost:8080/YelpApplicationJMSClient/view/SuccessLoginAdmin.jsp");
				}
				else
				{
					/*String destination="Login.jsp";
					request.setAttribute("errorMsg", "Invalid Username Password");
					RequestDispatcher rd = request.getRequestDispatcher(destination);
					rd.forward(request, response);*/
					System.out.println("not signed it submitted");
					response.sendRedirect("http://localhost:8080/YelpApplicationJMSClient/view/index.jsp");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			/*HttpSession session=request.getSession();
			if(result){
				
				String[] details=new String[5];
				//details=proxy.fetch_userdetails(userid);
				session.setAttribute("email", email);
				session.setAttribute("time_log", details[4]);
				//out.println("welcome to simple market place "+fname);
				String destination = "../index.jsp";
				
				Cookie fname = new Cookie("fname", details[0]);
				Cookie lname = new Cookie("lname", details[1]);
				response.addCookie(fname);
				response.addCookie(lname);
				 
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/servlet_order_buyer");
				dispatcher.forward(request, response);
				response.sendRedirect("http://localhost:8080/JMS_SimpleMarketPlaceClient/index.jsp");
			}
			else
			{
				System.out.println("login failed");
				session.setAttribute("error", "login failed");
				response.sendRedirect("http://localhost:8080/JMS_SimpleMarketPlaceClient/view/signin.jsp");
			}*/
		}catch (Exception e){
			System.out.println(e);
		}
	}

}
