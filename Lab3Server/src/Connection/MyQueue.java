package Connection;

import java.io.IOException;
import java.util.Properties;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entity.ManagedBeanAdmin;
import entity.ManagedBeanUser;

public class MyQueue implements MessageListener {

	/**
	 * @param args
	 */
	ManagedBeanAdmin admin  =new ManagedBeanAdmin();
	ManagedBeanUser user=new ManagedBeanUser();
	//Student student=new Student();
	//Lender lender=new Lender();
	DatabaseConnection dbconnect=new DatabaseConnection();
	private Connection connection;
	private Session session;
	private Topic counterTopic;
	private Queue queue;
	private MessageConsumer consumer;
	ManagedBeanUser result;
	public MyQueue()
	{
		try
		{
			Properties properties = new Properties();
		    properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		    properties.put(Context.URL_PKG_PREFIXES, "org.jnp.interfaces");
		    properties.put(Context.PROVIDER_URL, "localhost");
		    
			InitialContext jndiContext = new InitialContext(properties);
			
			ConnectionFactory confactory = (ConnectionFactory)jndiContext.lookup("XAConnectionFactory");
			Queue queue = (Queue)jndiContext.lookup("myqueue");
			
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
	
	public void sendreply(Message request, String temp1)
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
				if(request.getJMSCorrelationID().equalsIgnoreCase("signInUser"))
				{
					MessageProducer MP = session.createProducer(null);
					Destination reply = request.getJMSReplyTo();
					ObjectMessage om = session.createObjectMessage();
					
					System.out.println("output of data"+temp1);
				//	om.setStringProperty("signInUser", temp);
					MP.send(reply, om);
				}
				if(request.getJMSCorrelationID().equalsIgnoreCase("signUpUser"))
				{
					MessageProducer MP = session.createProducer(null);
					Destination reply = request.getJMSReplyTo();
					ObjectMessage om = session.createObjectMessage();
					
					System.out.println("output of data"+temp1);
					//om.setByteProperty("signUpnUser", temp);
					MP.send(reply, om);
				}
				if(request.getJMSCorrelationID().equalsIgnoreCase("signInAdmin"))
				{
					MessageProducer MP = session.createProducer(null);
					Destination reply = request.getJMSReplyTo();
					ObjectMessage om = session.createObjectMessage();
					
					System.out.println("output of data"+temp1);
				//	om.setStringProperty("signInAdmin", temp);
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
				//	TM.setText(temp);
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
			
            System.out.println("Queue: inside queue ");
            
            
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
				if((msg.getJMSCorrelationID()).equalsIgnoreCase("signInUser"))
				{
					System.out.println("inside object comparison for lender");
					user=(ManagedBeanUser)msg.getObject();
					result=dbconnect.signInUser(user.getEname(),user.getEmailid());
					ManagedBeanUser temp =result;
					sendreply(message, temp);
				}
				if((msg.getJMSCorrelationID()).equalsIgnoreCase("signUpUser"))
				{
					System.out.println("inside object comparison for lender");
					user=(ManagedBeanUser)msg.getObject();
					result=dbconnect.signUpUser(user.getFirstname(),user.getEmailid(),user.getEmailid(),user.getPassword());
					ManagedBeanUser temp =result;
					sendreply(message, temp);
				}
				if((msg.getJMSCorrelationID()).equalsIgnoreCase("signInAdmin"))
				{
					System.out.println("inside object comparison for add_product");
					admin=(ManagedBeanAdmin)msg.getObject();
					result=dbconnect.signInAdmin(admin.getEmailid(),admin.getEname());
					ManagedBeanUser temp =result;
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
	            if((msg.getJMSCorrelationID()).equalsIgnoreCase("signInUser"))
				{
					System.out.println("inside text comparison");
					String [] data = msg.getText().split(";;");
					ManagedBeanUser temp = dbconnect.signInAdmin(data[0], data[1]);
					String temp1 = result.toString();
					sendreply(message, temp1);
				}
	            if((msg.getJMSCorrelationID()).equalsIgnoreCase("signUpUser"))
				{
					System.out.println("inside text comparison");
					String [] data = msg.getText().split(";;");
					ManagedBeanUser temp = dbconnect.signUpUser(data[0], data[1],data[2],date[3]);
					//String temp = new Boolean(result).toString();
					sendreply(message, temp);
				}
	            
	            if((msg.getJMSCorrelationID()).equalsIgnoreCase("signInAdmin"))
				{
					System.out.println("inside text comparison");
					String [] data = msg.getText().split(";;");
					ManagedBeanUser temp = dbconnect.signInAdmin(data[0], data[1]);
					//String temp = new Boolean(result).toString();
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
		new MyQueue();
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
