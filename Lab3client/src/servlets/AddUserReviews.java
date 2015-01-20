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
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.ManagedBeanAdmin;
import entity.ManagedBeanUser;


//import Connection.Signup_cmdProxy;



/**
 * Servlet implementation class servlet_signup
 */

public class AddUserReviews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Signup_cmdProxy proxy=new Signup_cmdProxy();
	ManagedBeanUser user = new ManagedBeanUser();
	private Connection connection;
	private Session session;
	private Topic myTopic;
	private Topic sendTopic;
	private MessageConsumer consumer;
    /**
     * @see HttpServlet#HttpServlet()
     */
	public AddUserReviews() {
		super();
		Properties properties = new Properties();
		properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		properties.put(Context.URL_PKG_PREFIXES, "org.jnp.interfaces");
		properties.put(Context.PROVIDER_URL, "localhost");

		try {
			InitialContext jndiContext = new InitialContext(properties);
			ConnectionFactory confactory=(ConnectionFactory)jndiContext.lookup("XAConnectionFactory");
			myTopic=(Topic) jndiContext.lookup("myTopic");
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
		out.println("servlet_add_product");
		HttpSession httpsession = (HttpSession) request.getSession();
			System.out.println("inside the servlet");
			String emailid=request.getParameter("email");
			try
			{
				MessageProducer mp=session.createProducer(myTopic);
				
				ObjectMessage om=session.createObjectMessage();
				
				sendTopic=session.createTemporaryTopic();
				consumer=session.createConsumer(sendTopic);
				
				//ObjectMessage tm=session.createObjectMessage();
				om.setJMSReplyTo(sendTopic);

				
				user.setEmailid(emailid);;
		;
				om.setJMSCorrelationID("addUserReviews");
				
				if(emailid!=null)
				{
					System.out.println("inside if loop");
				
					mp.send(om);
					
					System.out.println("mp sended");
					
					ObjectMessage reply=(ObjectMessage)consumer.receive();
					
					out.println(reply.getStringProperty("addUserReviews"));
					String result=reply.getStringProperty("addUserReviews");
					
					if(result.equalsIgnoreCase("true")){
						
						HttpSession session=request.getSession();
						session.setAttribute("UserSession", session);
						//out.println("welcome to simple market place "+fname);
						String destination = "/userAddReviewsSuccess.jsp";
						 
						RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
						rd.forward(request, response);
					}
					else
					{
						response.sendRedirect("/userAddReviews.jsp");
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
