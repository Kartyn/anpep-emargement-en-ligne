package servlets;

// [START simple_includes]
import java.io.ByteArrayInputStream;
import java.io.IOException;
// [START multipart_includes]
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
// [END simple_includes]
import javax.mail.internet.MimeMultipart;
import javax.servlet.annotation.WebServlet;
// [END multipart_includes]
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/mail")

@SuppressWarnings("serial")
public class Mail extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("jsuis sur la page mail");
		sendSimpleMail();
		
	}
	
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String type = req.getParameter("type");
    if (type != null && type.equals("multipart")) {
      resp.getWriter().print("Sending HTML email with attachment.");
      sendMultipartMail();
    } else {
      resp.getWriter().print("Sending simple email.");
      sendSimpleMail();
    }
  }

  private void sendSimpleMail() {
    // [START simple_example]
    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    try {
      Message msg = new MimeMessage(session);
      
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.Host", "test@yopmail.com");
      props.put("mail.smtp.debug", "true");
      props.put("mail.smtp.port", "587");
      
      msg.setFrom(new InternetAddress("test@yopmail.com", "test2@yopmail.com"));
      msg.addRecipient(Message.RecipientType.TO,
                       new InternetAddress("test3@yopmail.com", "Mr. User"));
      msg.setSubject("Your Example.com account has been activated");
      msg.setText("This is a test");
      
      
      Transport.send(msg);
      System.out.println("eh j'ai envoy� un mail");
    } catch (AddressException e) {
      System.out.println("erreur adresse");
    } catch (MessagingException e) {
    	 System.out.println("erreur msg");
    } catch (UnsupportedEncodingException e) {
    	 System.out.println("erreur encoding");
    }
    catch (Exception e) {
    	System.out.println("erreur global");
    }
    // [END simple_example]
  }

  private void sendMultipartMail() {
    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    String msgBody = "...";

    try {
      Message msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress("admin@example.com", "Example.com Admin"));
      msg.addRecipient(Message.RecipientType.TO,
                       new InternetAddress("user@example.com", "Mr. User"));
      msg.setSubject("Your Example.com account has been activated");
      msg.setText(msgBody);

      // [START multipart_example]
      String htmlBody = "";          // ...
      byte[] attachmentData = null;  // ...
      Multipart mp = new MimeMultipart();

      MimeBodyPart htmlPart = new MimeBodyPart();
      htmlPart.setContent(htmlBody, "text/html");
      mp.addBodyPart(htmlPart);

      MimeBodyPart attachment = new MimeBodyPart();
      InputStream attachmentDataStream = new ByteArrayInputStream(attachmentData);
      attachment.setFileName("manual.pdf");
      attachment.setContent(attachmentDataStream, "application/pdf");
      mp.addBodyPart(attachment);

      msg.setContent(mp);
      // [END multipart_example]

      Transport.send(msg);

    } catch (AddressException e) {
      // ...
    } catch (MessagingException e) {
      // ...
    } catch (UnsupportedEncodingException e) {
      // ...
    }
  }
}