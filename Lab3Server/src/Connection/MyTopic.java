package Connection;

import java.io.IOException;
import java.util.Properties;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entity.Customer;
import entity.ManagedBeanAdmin;
import entity.ManagedBeanUser;
import entity.Product;

public class MyTopic implements MessageListener {

	/**
	 * @param args
	 */
ManagedBeanAdmin admin= new ManagedBeanAdmin();
ManagedBeanUser user=new ManagedBeanUser();
	//Student student=new Student();
	//Lender lender=new Lender();
	DatabaseConnection dbconnect=new DatabaseConnection();
	private Connection connection;
	private Session session;
	private Topic myTopic;
	private Queue queue;
	private MessageConsumer consumer;
	boolean result;
	public MyTopic()
	{
		try
		{
			Properties properties = new Properties();
		    properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		    properties.put(Context.URL_PKG_PREFIXES, "org.jnp.interfaces");
		    properties.put(Context.PROVIDER_URL, "localhost");
		    
			InitialContext jndiContext = new InitialContext(properties);
			
			ConnectionFactory confactory = (ConnectionFactory)jndiContext.lookup("XAConnectionFactory");
			Queue queue = (Queue)jndiContext.lookup("topic/myTopic");
			
			connection = confactory.createConnection();
			
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			consumer = session.createConsumer(queue);
			consumer.setMessageListener(this);
			connection.start();
			
			System.out.println("Server started waiting for client requests");
		}
		catch(NamingException NE)
		{
			System.out.println("Naming Exception: "+NE);
		}
		catch(JMSException JMSE)
		{
			System.out.println("JMS Exception: "+JMSE);
			JMSE.printStackTrace();
		}
	}
	
	public void sendreply(Message request, String data)
	{
		try 
		{
			/*MessageProducer MP = session.createProducer(null);
			Destination reply = request.getJMSReplyTo();
			ObjectMessage om = session.createObjectMessage();
			
			System.out.println("output of data"+data);
			om.setStringProperty("signup", data);
			MP.send(reply, om);*/
			
			if(request instanceof ObjectMessage)
			{
				/*if(request.getJMSCorrelationID().equalsIgnoreCase("signup_as_student"))
				{
					MessageProducer MP = session.createProducer(null);
					Destination reply = request.getJMSReplyTo();
					ObjectMessage om = session.createObjectMessage();
					
					System.out.println("output of data"+data);
					om.setStringProperty("signup_as_student", data);
					MP.send(reply, om);
				}*/
				if(request.getJMSCorrelationID().equalsIgnoreCase("viewAdminCatecory"))
				{
					MessageProducer MP = session.createProducer(null);
					Destination reply = request.getJMSReplyTo();
					ObjectMessage om = session.createObjectMessage();
					
					System.out.println("output of data"+data);
					om.setStringProperty("viewAdminCatecory", data);
					MP.send(reply, om);
				}
				if(request.getJMSCorrelationID().equalsIgnoreCase("viewUserReviews"))
				{
					MessageProducer MP = session.createProducer(null);
					Destination reply = request.getJMSReplyTo();
					ObjectMessage om = session.createObjectMessage();
					
					System.out.println("output of data"+data);
					om.setStringProperty("viewUserReviews", data);
					MP.send(reply, om);
				}
				if(request.getJMSCorrelationID().equalsIgnoreCase("createCategory"))
				{
					MessageProducer MP = session.createProducer(null);
					Destination reply = request.getJMSReplyTo();
					ObjectMessage om = session.createObjectMessage();
					
					System.out.println("output of data"+data);
					om.setStringProperty("createCategory", data);
					MP.send(reply, om);
				}
				if(request.getJMSCorrelationID().equalsIgnoreCase("deleteCategory"))
				{
					MessageProducer MP = session.createProducer(null);
					Destination reply = request.getJMSReplyTo();
					ObjectMessage om = session.createObjectMessage();
					
					System.out.println("output of data"+data);
					om.setStringProperty("deleteCategory", data);
					MP.send(reply, om);
				}
				if(request.getJMSCorrelationID().equalsIgnoreCase("updateCategory"))
				{
					MessageProducer MP = session.createProducer(null);
					Destination reply = request.getJMSReplyTo();
					ObjectMessage om = session.createObjectMessage();
					
					System.out.println("output of data"+data);
					om.setStringProperty("updateCategory", data);
					MP.send(reply, om);
				}
				if(request.getJMSCorrelationID().equalsIgnoreCase("addUserReviews"))
				{
					MessageProducer MP = session.createProducer(null);
					Destination reply = request.getJMSReplyTo();
					ObjectMessage om = session.createObjectMessage();
					
					System.out.println("output of data"+data);
					om.setStringProperty("addUserReviews", data);
					MP.send(reply, om);
				}
				else
				{
					System.out.println("not an obj mesg.");
				}
			}
			if(request instanceof TextMessage)
			{
				try 
				{
					MessageProducer MP = session.createProducer(null);
					Destination reply = request.getJMSReplyTo();
					TextMessage TM = session.createTextMessage();
					TM.setText(data);
					MP.send(reply, TM);
				}
				catch (JMSException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		catch (JMSException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void onMessage(Message message) {
		
		if (message instanceof ObjectMessage) {
			ObjectMessage msg = (ObjectMessage) message;
			
			try {
			
            System.out.println("Queue: I received an ObjectMessage ");
            
            
				//customer customer_details = (customer) msg.getObject();
				/*if((msg.getJMSCorrelationID()).equalsIgnoreCase("signup"))
				{
					System.out.println("inside object comparison");
					result=dbconnect.signup_method(customer_details.getUserid(), customer_details.getFirstname(), customer_details.getLastname(), customer_details.getPassword(), customer_details.getEmail(), customer_details.getCity());
					String temp = new Boolean(result).toString();
					sendreply(message, temp);
				}*/
				/*if((msg.getJMSCorrelationID()).equalsIgnoreCase("signup_as_student"))
				{
					System.out.println("inside object comparison");
					student=(Student)msg.getObject();
					result=dbconnect.signup_as_student(student.getFirstName(), student.getLastName(), student.getEmailId(), student.getPassword(), student.getAddress(), student.getSchoolName(), student.getUniversityName(), student.getSchoolGPA(), student.getUGradGPA(), student.getGradGPA(), student.getSAT(), student.getGRE(), student.getTOEFL(), student.getExtraActivities());
					String temp = new Boolean(result).toString();
					sendreply(message, temp);
				}*/
				if((msg.getJMSCorrelationID()).equalsIgnoreCase("viewAdminCatecory"))
				{
					String[] result;
					admin=(ManagedBeanAdmin)msg.getObject();
					result=dbconnect.viewAdminCatecory();
					String temp = result.toString();
					sendreply(message, temp);
				}
				if((msg.getJMSCorrelationID()).equalsIgnoreCase("viewUserReviews"))
				{
					String result;
					System.out.println("inside view reviews");
					user=(ManagedBeanUser)msg.getObject();
					result=dbconnect.viewUserReviews(user.getEmailid());
					String temp = new Boolean(result).toString();
					sendreply(message, temp);
				}
				if((msg.getJMSCorrelationID()).equalsIgnoreCase("createCategory"))
				{
					String result;
					System.out.println("inside create category");
					admin=(ManagedBeanAdmin)msg.getObject();
					result=dbconnect.createCategory(admin.getEname(),admin.getEdescription(),admin.getEratings(),admin.getEreviews(),admin.getEmailid());
					String temp = new Boolean(result).toString();
					sendreply(message, temp);
				}
				if((msg.getJMSCorrelationID()).equalsIgnoreCase("deleteCategory"))
				{
					String result;
					System.out.println("inside delete category");
					admin=(ManagedBeanAdmin)msg.getObject();
					result=dbconnect.deleteCategory(admin.getEname());
					String temp = new Boolean(result).toString();
					sendreply(message, temp);
				}
				if((msg.getJMSCorrelationID()).equalsIgnoreCase("deleteCategory"))
				{
					String result;
					System.out.println("inside deletecategory");
					admin=(ManagedBeanAdmin)msg.getObject();
					result=dbconnect.updateCategory(admin.getEname(),admin.getEdescription(),admin.getEratings(),admin.getEreviews(),admin.getEmailid());
					String temp = new Boolean(result).toString();
					sendreply(message, temp);
				}
			}
			if((msg.getJMSCorrelationID()).equalsIgnoreCase("addUserReviews"))
			{
				String result;
				System.out.println("inside add user reviews");
				admin=(ManagedBeanAdmin)msg.getObject();
				result=dbconnect.updateCategory(admin.getEname(),admin.getEdescription(),admin.getEratings(),admin.getEreviews(),admin.getEmailid());
				String temp = new Boolean(result).toString();
				sendreply(message, temp);
			}
				else
				{
					System.out.println("no obj. found");
				}
				
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } 
		if (message instanceof TextMessage) 
		{
			TextMessage msg = (TextMessage) message;
			try {
				
	            System.out.println("Queue: I received an TextMessage ");
	            if((msg.getJMSCorrelationID()).equalsIgnoreCase("viewAdminCatecory"))
				{"))
				{
					System.out.println("inside text view admin categories");
					String [] data = msg.getText().split(";;");
					String temp = dbconnect.viewAdminCatecory(data[0], data[1]);
					//String temp = new Boolean(result).toString();
					sendreply(message, temp);
				}
	            
	            if((msg.getJMSCorrelationID()).equalsIgnoreCase("viewUserReviews"))
				{
					System.out.println("inside user reviews");
					String email=msg.getText();
					boolean result = dbconnect.update_timelog_method(email);
					String temp = new Boolean(result).toString();
					sendreply(message, temp);
				}
	            
	            if((msg.getJMSCorrelationID()).equalsIgnoreCase("createCategory"))
				{
					System.out.println("inside create categories");
					String email=msg.getText();
					boolean result = dbconnect.update_timelog_method(email);
					String temp = new Boolean(result).toString();
					sendreply(message, temp);
				}
	            
	            if((msg.getJMSCorrelationID()).equalsIgnoreCase("deleteCategory"))
				{
					System.out.println("inside delete categories");
					String email=msg.getText();
					boolean result = dbconnect.update_timelog_method(email);
					String temp = new Boolean(result).toString();
					sendreply(message, temp);
				}
	            
	            if((msg.getJMSCorrelationID()).equalsIgnoreCase("viewUserReviews"))
				{
					System.out.println("inside user reviews");
					String email=msg.getText();
					boolean result = dbconnect.update_timelog_method(email);
					String temp = new Boolean(result).toString();
					sendreply(message, temp);
				}
	            
	            if((msg.getJMSCorrelationID()).equalsIgnoreCase("deleteCategory"))
				{
					System.out.println("inside delete category");
					String email=msg.getText();
					boolean result = dbconnect.update_timelog_method(email);
					String temp = new Boolean(result).toString();
					sendreply(message, temp);
				}
	            if((msg.getJMSCorrelationID()).equalsIgnoreCase("addUserReviews"))
	 				{
	 					System.out.println("inside addUserReviews");
	 					String email=msg.getText();
	 					boolean result = dbconnect.update_timelog_method(email);
	 					String temp = new Boolean(result).toString();
	 					sendreply(message, temp);
	 				}
					
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		/*else {
            System.out.println("Not valid message for this Queue MDB");
        }*/
		
	}
	
	public static void main(String[] args) 
	{
		new MyTopic();
		while (true) {
			try {
				//answer = (char) inputStreamReader.read();
			} catch (Exception e) {
				System.out.println("I/O exception: "
						+ e.toString());
			}
		}
	}

}
