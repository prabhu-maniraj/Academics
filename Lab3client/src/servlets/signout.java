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


//import Connection.Signup_cmdProxy;



public class signout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Signup_cmdProxy proxy=new Signup_cmdProxy();
	
    
	//Customer customer=new Customer();
	 private Connection connection;
	 private Session session;
	 private javax.jms.Queue queue;
	 private javax.jms.Queue replyqueue;
	 private MessageConsumer consumer;
    
    public signout() {
        super();
        Properties properties = new Properties();
	    properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
	    properties.put(Context.URL_PKG_PREFIXES, "org.jnp.interfaces");
	    properties.put(Context.PROVIDER_URL, "localhost");
	    
		try {
			InitialContext jndiContext = new InitialContext(properties);
			ConnectionFactory confactory=(ConnectionFactory)jndiContext.lookup("XAConnectionFactory");
			queue=(Queue) jndiContext.lookup("queue/myqueue");
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("servlet_signout");
		System.out.println("inside the servlet");
		HttpSession httpsession = (HttpSession) request.getSession();
		
		String email=(String) httpsession.getAttribute("email");
		
		try{
				//String user = request.getParameter("userName");
				//String pass = request.getParameter("password");
				MessageProducer MP;
				MP = session.createProducer(queue);
				TextMessage TM = session.createTextMessage("");
				replyqueue = session.createTemporaryQueue();
				consumer = session.createConsumer( replyqueue );
				TM.setJMSReplyTo(replyqueue);
				TM.setText(email);
				TM.setJMSCorrelationID("signout");
				MP.send(TM);
				System.out.println("mp sended");
				TextMessage Reply = (TextMessage)consumer.receive();
				String result = Reply.getText();
				
				if(result.equals("true"))
				{
					httpsession.invalidate();
					response.sendRedirect("http://localhost:8080/YelpApplicationJMSClient/view/index.jsp");
				}
				else
				{
					/*String destination="Login.jsp";
					request.setAttribute("errorMsg", "Invalid Username Password");
					RequestDispatcher rd = request.getRequestDispatcher(destination);
					rd.forward(request, response);*/
					System.out.println("not signed out submitted");
					response.sendRedirect("http://localhost:8080/YelpApplicationJMSClient/view/index.jsp");
				}
			
		}catch (Exception e){
			System.out.println(e);
		}
	}

}
