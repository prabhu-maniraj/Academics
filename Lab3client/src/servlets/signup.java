package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.ManagedBeanAdmin;

/**
 * Servlet implementation class servlet_signup_as_lender
 */
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
ManagedBeanAdmin user = new ManagedBeanAdmin();
	private Connection connection;
	private Session session;
	private Queue queue;
	private Queue sendqueue;
	private MessageConsumer consumer;
    
    public signup() {
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest Request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest Request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		out.println("servlet_signup");
	
			System.out.println("inside the servlet");
			String fname=request.getParameter("fname");
			String lname=request.getParameter("lname");
			String emailId=request.getParameter("email");
			String pass=request.getParameter("pass");
			String time_log=request.getParameter("time_log");
			try
			{
				MessageProducer mp=session.createProducer(queue);
				
				ObjectMessage om=session.createObjectMessage();
				
				sendqueue=session.createTemporaryQueue();
				consumer=session.createConsumer(sendqueue);
				
				//ObjectMessage tm=session.createObjectMessage();
				om.setJMSReplyTo(sendqueue);

				user.setFirstName(fname);
				user.setLastName(lname);
				user.setEmailid(emailId);
				user.setPassword(pass);
				user.setLoginTime(time_log);
				
				om.setJMSCorrelationID("signUpUser");
				
				if(emailId!=null && emailId!="")
				{
					System.out.println("inside if loop");
				
					mp.send(om);
					
					System.out.println("mp sended");
					
					ObjectMessage reply=(ObjectMessage)consumer.receive();
					
					out.println(reply.getStringProperty("signup"));
					String result=reply.getStringProperty("signup");
					
					if(result.equalsIgnoreCase("true")){
						
						HttpSession session=request.getSession();
						session.setAttribute("UserSession", session);
						out.println("welcome to simple market place "+fname);
						String destination = "/index.jsp";
						 
						RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
						rd.forward(request, response);
					}
					else
					{
						response.sendRedirect("/index.jsp");
					}
				}
				else
				{
					out.println("in else loop");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

	}

}
