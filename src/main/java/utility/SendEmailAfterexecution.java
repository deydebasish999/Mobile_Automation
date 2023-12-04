package utility;

import java.io.File;
import java.util.Properties;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Authenticator;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;


public class SendEmailAfterexecution {
	
	public static void sendmail() throws AddressException, MessagingException, InterruptedException{

		ConfigFileReader configreader = new ConfigFileReader();
		  //String[] to={"mail address","mail address"};
		  String to="debasishdey@kpmg.com";
		  final String user=configreader.getEmailUsername();
		  final String password=configreader.getEmailPassword(); 

		  //Get the session object     
		  Properties properties = System.getProperties();  
		  properties.put("mail.smtp.auth", "true"); 
		  properties.put("mail.smtp.starttls.enable", "true"); 
		  properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		  properties.put("mail.smtp.port", "587");
		  //properties.put("mail.debug", "true");
		  
		  

	  
		Authenticator authenticator = new Authenticator() { protected PasswordAuthentication getPasswordAuthentication() {  
			   return new PasswordAuthentication(user,password);
		}};
		
		Session session =Session.getDefaultInstance(properties, authenticator);
		  
		  	//compose message     

		    MimeMessage message = new MimeMessage(session);  
		    message.setFrom(new InternetAddress(user));  
		    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		    message.setSubject("Mobile Automation Execution Status");  

		    //create MimeBodyPart object and set your message text     
		    BodyPart messageBodyPart1 = new MimeBodyPart();  
		    messageBodyPart1.setText("Please find the Test Result in the attachment");  

		    //create new MimeBodyPart object and set DataHandler object to this object      
		    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  MimeBodyPart messageBodyPart3 = new MimeBodyPart(); MimeBodyPart messageBodyPart4 = new MimeBodyPart(); MimeBodyPart messageBodyPart5 = new MimeBodyPart(); 

		    File f3=new File(System.getProperty("user.dir")+"\\testReports\\SparkReport.html");
		    DataSource source4 = new FileDataSource(f3);
		    messageBodyPart5.setDataHandler(new DataHandler(source4));    
		    messageBodyPart5.setFileName(f3.getName()); 


		    //create Multipart object and add MimeBodyPart objects to this object      
		    Multipart multipart = new MimeMultipart();  
		    multipart.addBodyPart(messageBodyPart1);   
		    multipart.addBodyPart(messageBodyPart5);  

		    //set the multiplart object to the message object  
		    message.setContent(multipart);  
		    //send message  
		    Transport.send(message); 

		   System.out.println("message sent....");  
		    }

}

