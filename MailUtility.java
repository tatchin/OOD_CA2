package ca2;

import java.util.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

/**
  * <i>Provides static methods to send emails to single/multiple recipients with/without attachment.</i>
 *   @author     NMCG
 *   @version    1.0            
*/
public class MailUtility {
	
/**************************************************************************************************************************************
*														CLASS VARIABLES
**************************************************************************************************************************************/
	
	    //variables defining logon details for senders email account
	 	private static String USERNAME = "teetatchin@gmail.com"; //insert your gmail address
	    private static String PASSWORD  = "tatchin123";        //insert your password
	    //port and hostname for gmail server
	    private static String HOSTNAME = "smtp.gmail.com";
	    private static int HOSTPORT = 465; //Yahoo uses 25
	    //properties of the connection
	    private static Properties properties = getProperties();
	    
/**************************************************************************************************************************************
*														GETTERS & SETTERS
**************************************************************************************************************************************/    
	    /**
		 * @return the uSERNAME
		 */
		public static String getUSERNAME() {
			return USERNAME;
		}

		/**
		 * @param uSERNAME the uSERNAME to set
		 */
		public static void setUSERNAME(String uSERNAME) {
			USERNAME = uSERNAME;
		}

		/**
		 * @return the pASSWORD
		 */
		public static String getPASSWORD() {
			return PASSWORD;
		}

		/**
		 * @param pASSWORD the pASSWORD to set
		 */
		public static void setPASSWORD(String pASSWORD) {
			PASSWORD = pASSWORD;
		}

		/**
		 * @return the hOSTNAME
		 */
		public static String getHOSTNAME() {
			return HOSTNAME;
		}

		/**
		 * @param hOSTNAME the hOSTNAME to set
		 */
		public static void setHOSTNAME(String hOSTNAME) {
			HOSTNAME = hOSTNAME;
		}

		/**
		 * @return the hOSTPORT
		 */
		public static int getHOSTPORT() {
			return HOSTPORT;
		}

		/**
		 * @param hOSTPORT the hOSTPORT to set
		 */
		public static void setHOSTPORT(int hOSTPORT) {
			HOSTPORT = hOSTPORT;
		}

		/**
		 * @param properties the properties to set
		 */
		public static void setProperties(Properties properties) {
			MailUtility.properties = properties;
		}
		
		/**
	     * <i>Initialises the connection properties (i.e. host, port, uname, password).</i>          
	    */
	    private static Properties getProperties()
	    {
	    	//set the properties expected by the GMail server
	        Properties properties = new Properties();
	        properties.put("mail.smtps.host", HOSTNAME);
	        properties.put("mail.transport.protocol", "smtps");
	        properties.put("mail.smtps.quitwait", "false");
	        properties.put("mail.smtps.auth", "true");
	        return properties;
	    }
   
/**************************************************************************************************************************************
*														FUNCTIONALITY
**************************************************************************************************************************************/
	    
	    /**
	     * <i>Sends an email to one recipient address.</i>
	     *   @param      recipient 			Recipient address
	     *   @param      subject    		Email subject    
	     *   @param      content 			Email content  
	     *   @param      contentType		Content type e.g. text/plain or text/html               
	    */
	    public static void send(String recipient, String subject, String content,  String contentType) 
	    {
	    	//get rid of any white space before and after string because of user entry error e.g. "john@hotmail.com   "
	    	recipient.trim();
	    	contentType.trim();

	        //create a session object with the connection parameters defined above - note no connection exists until connect() is invoked
	        Session mailSession = Session.getDefaultInstance(properties);
	        
	        //uncomment to see debug content in the Console window
	        //mailSession.setDebug(true);
	        
	        //set the message contents
	        MimeMessage message = new MimeMessage(mailSession);
	        try
	        {
		        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		        message.setSubject(subject);
		        message.setContent(content, contentType);	  
		        
		        //connect to the remote server
		        Transport transport = mailSession.getTransport();
		        transport.connect (HOSTNAME, HOSTPORT, USERNAME, PASSWORD);
		        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		        transport.close();
	        }
	        catch(Exception e)
	        {
	        	 System.out.println("Something unexpected happened...");
	        	 e.printStackTrace();
	        }
	        
	        System.out.println("Done");
	    }
	    
	    /**
	     * <i>Sends an email to multiple recipient addresses.</i>
	     *   @param      toRecipients 		String array of TO recipient addresses
	     *   @param      ccRecipients 		String array of CC recipient addresses (pass null to omit CC list)
	     *   @param      bccRecipients 		String array of BCC recipient addresses (pass null to omit BCC list)
	     *   @param      subject    		Email subject    
	     *   @param      content 			Email content  
	     *   @param      contentType		Content type e.g. text/plain or text/html               
	    */
		//send a text format email to many To and CC recipients
	    public static void send(String[] toRecipients, String[] ccRecipients, String[] bccRecipients, String subject, String content, String contentType) 
	    {
	    	//use trim() to get rid of any white space before and after string because of user entry error e.g. "john@hotmail.com   "
	    	contentType.trim();
	    	
	        //create a session object with the connection parameters defined above - note no connection exists until connect() is invoked
	        Session mailSession = Session.getDefaultInstance(properties);
	 	      
	        //uncomment to see debug content in the Console window
	        //mailSession.setDebug(true);
	        
	        //set the message contents
	        MimeMessage message = new MimeMessage(mailSession);
	        
	        try
	        {
		        //add the To recipients
		        for(int i=0;i<toRecipients.length;i++)
		        {
		        	//use trim() to get rid of any white space before and after string because of user entry error e.g. "john@hotmail.com   "
		        	message.addRecipient(Message.RecipientType.TO, new InternetAddress(toRecipients[i].trim()));
		        }
		        //add the CC recipients
		        for(int i=0;i<ccRecipients.length;i++)
		        {
		        	//use trim() to get rid of any white space before and after string because of user entry error e.g. "john@hotmail.com   "
		        	message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccRecipients[i].trim()));
		        }
		        //add the BCC recipients
		        for(int i=0;i<ccRecipients.length;i++)
		        {
		        	//use trim() to get rid of any white space before and after string because of user entry error e.g. "john@hotmail.com   "
		        	message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bccRecipients[i].trim()));
		        }
		        message.setSubject(subject);
		        message.setContent(content, contentType);	  
		        
		        //connect to the remote server
		        Transport transport = mailSession.getTransport();
		        transport.connect (HOSTNAME, HOSTPORT, USERNAME, PASSWORD);
		        //get all recipients - To and CC
		        transport.sendMessage(message, message.getAllRecipients());
		        transport.close();
	        }
	        catch(Exception e)
	        {
	        	 System.out.println("Something unexpected happened...");
	        	 e.printStackTrace();
	        }
	        System.out.println("Done");
	    }
	    
	    
	    /**
	     * <i>Sends an email to one recipient address with one or more attachments.</i>
	     *   @param      recipient 			Recipient address
	     *   @param      subject    		Email subject    
	     *   @param      content 			Email content  
	     *   @param      fileNames		    An array of file name in absolute (e.g. "c:\\folder\\file.txt") or relative file path (e.g. "file.txt") path format                
	    */
	    public static void send(String recipient, String subject, String content, String[] fileNames)
	    {
	    	
	    	//use trim() to get rid of any white space before and after string because of user entry error e.g. "john@hotmail.com   "
	    	recipient.trim();
	    	
	        //create a session object with the connection parameters defined above - note no connection exists until connect() is invoked
	        Session mailSession = Session.getDefaultInstance(properties);
	 	      
	        //uncomment to see debug content in the Console window
	        //mailSession.setDebug(true);
	        
	        //set the message contents
	        MimeMessage message = new MimeMessage(mailSession);
	        try
	        {
		        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		        message.setFrom(new InternetAddress(USERNAME));
		        message.setSubject(subject);
		        
		     
		        // create a message that will have two part 
		        MimeBodyPart messageBodyPart = new MimeBodyPart();

		        //add content to the first part
		        messageBodyPart.setText(content);
		        Multipart multipart = new MimeMultipart();
		        multipart.addBodyPart(messageBodyPart);
		      		 
		        //we can add multiple file attachments with an array of filenames
		        for(int i =0; i < fileNames.length; i++)
		        {
		        	
		        	//add attachment to second part
			        messageBodyPart = new MimeBodyPart();
			        DataSource source = new FileDataSource(fileNames[i].trim());
			        messageBodyPart.setDataHandler(new DataHandler(source));
			        
			        //parse out file name (e.g. "test.txt" from full path e.g. "c:\temp\test.txt") otherwise file will appear in email named c:\temp\test.txt
			        //why is a double "\\" required here???
			        messageBodyPart.setFileName(fileNames[i].substring(fileNames[i].lastIndexOf("\\") + 1));
			        
			        //replace the line above with this one to see the difference when we pass an absolute path (e.g. "c:\temp\test.txt")
			        //messageBodyPart.setFileName(fileName);
			        
			        multipart.addBodyPart(messageBodyPart);
		        }

		        
		        //add both parts to the message
		        message.setContent(multipart);
		        
		        //connect to the remote server
		        Transport transport = mailSession.getTransport();
		        transport.connect (HOSTNAME, HOSTPORT, USERNAME, PASSWORD);
		        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		        transport.close();
	        }
	        catch(Exception e)
	        {
	        	 System.out.println("Something unexpected happened...");
	        	 e.printStackTrace();
	        }
	        
	        System.out.println("Done");
	    }
	    
	    /**
	     * <i>Sends an email to multiple recipient addresses with one or more attachments.</i>
	     *   @param      toRecipients 		String array of TO recipient addresses
	     *   @param      ccRecipients 		String array of CC recipient addresses (pass null to omit CC list)
	     *   @param      bccRecipients 		String array of BCC recipient addresses (pass null to omit BCC list)
	     *   @param      subject    		Email subject    
	     *   @param      content 			Email content  
	     *   @param      fileNames		    An array of file name in absolute (e.g. "c:\\folder\\file.txt") or relative file path (e.g. "file.txt") path format          
	    */
	    
	    public static void send(String[] toRecipients, String[] ccRecipients, String[] bccRecipients, String subject, String content, String[] fileNames)
	    {
	    	   	
	        //create a session object with the connection parameters defined above - note no connection exists until connect() is invoked
	        Session mailSession = Session.getDefaultInstance(properties);
	 	      
	        //uncomment to see debug content in the Console window
	        //mailSession.setDebug(true);
	        
	        //set the message contents
	        MimeMessage message = new MimeMessage(mailSession);
	        try
	        {
	        	 //add the To recipients
		        for(int i=0;i<toRecipients.length;i++)
		        {
		        	//use trim() to get rid of any white space before and after string because of user entry error e.g. "john@hotmail.com   "
		        	message.addRecipient(Message.RecipientType.TO, new InternetAddress(toRecipients[i].trim()));
		        }
		        //add the CC recipients
		        for(int i=0;i<ccRecipients.length;i++)
		        {
		        	//use trim() to get rid of any white space before and after string because of user entry error e.g. "john@hotmail.com   "
		        	message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccRecipients[i].trim()));
		        }
		        //add the BCC recipients
		        for(int i=0;i<ccRecipients.length;i++)
		        {
		        	//use trim() to get rid of any white space before and after string because of user entry error e.g. "john@hotmail.com   "
		        	message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bccRecipients[i].trim()));
		        }
		        
		        message.setFrom(new InternetAddress(USERNAME));
		        message.setSubject(subject);
		        
		     
		        // create a message that will have two parts
		        MimeBodyPart messageBodyPart = new MimeBodyPart();

		        //add content to the first part
		        messageBodyPart.setText(content);
		        Multipart multipart = new MimeMultipart();
		        multipart.addBodyPart(messageBodyPart);
		      		 
		        //we can add multiple file attachments with an array of filenames
		        for(int i =0; i < fileNames.length; i++)
		        {
		        	
		        	//add attachment to second part
			        messageBodyPart = new MimeBodyPart();
			        DataSource source = new FileDataSource(fileNames[i].trim());
			        messageBodyPart.setDataHandler(new DataHandler(source));
			        
			        //parse out file name (e.g. "test.txt" from full path e.g. "c:\temp\test.txt") otherwise file will appear in email named c:\temp\test.txt
			        //why is a double "\\" required here???
			        messageBodyPart.setFileName(fileNames[i].substring(fileNames[i].lastIndexOf("\\") + 1));
			        
			        //replace the line above with this one to see the difference when we pass an absolute path (e.g. "c:\temp\test.txt")
			        //messageBodyPart.setFileName(fileName);
			        
			        multipart.addBodyPart(messageBodyPart);
		        }

		        
		        //add both parts to the message
		        message.setContent(multipart);
		        
		        //connect to the remote server
		        Transport transport = mailSession.getTransport();
		        transport.connect (HOSTNAME, HOSTPORT, USERNAME, PASSWORD);
		        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		        transport.close();
	        }
	        catch(Exception e)
	        {
	        	 System.out.println("Something unexpected happened...");
	        	 e.printStackTrace();
	        }
	        
	        System.out.println("Done");
	    }
	    
	     
}
