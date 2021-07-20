package beans;

import javax.mail.internet.*;

import org.mindrot.jbcrypt.BCrypt;

import javax.mail.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class Mail {
	
//  private final static String MAILER_VERSION = "Java";
  
  public static final String username = "chefanpepchef@gmail.com";
  public static final String password = "mdpanpep";
  private static String link = "localhost:8080/anpep/connexion?user=";
  private String fromEmail = "chefanpepchef@gmail.com";
  private String toEmail;
  private String subject;
  private String object;
  
  public Mail(String fromEmail, String toEmail) {
	  this.toEmail = toEmail;
	  this.fromEmail = fromEmail;
	  subject = "";
	  object = "";
  }
  
  public Mail() {
	  subject = "";
	  object = "";
  }
  
  public String getObject() {
	  return object;
  }
    
  public String getFromEmail() {
	return fromEmail;
}

public void setFromEmail(String fromEmail) {
	this.fromEmail = fromEmail;
}

public String getToEmail() {
	return toEmail;
}

public void setToEmail(String toEmail) {
	this.toEmail = toEmail;
}

public void sendEmail() {
  Properties prop = new Properties();
  prop.put("mail.smtp.auth", true);
  prop.put("mail.smtp.starttls.enable",true);
  prop.put("mail.smtp.host","smtp.gmail.com");
  prop.put("mail.smtp.port",587);
  
  Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
	  protected PasswordAuthentication getPasswordAuthentication() {
		  
		  return new PasswordAuthentication(username,password);
	  }
  });
  
  MimeMessage msg = new MimeMessage(session);
  try {
	msg.setFrom(new InternetAddress(getFromEmail()));
	msg.addRecipient(Message.RecipientType.TO, new InternetAddress(getToEmail()));
	msg.setSubject("Le sujet ici");
	msg.setText("contenue du mail");
	Transport.send(msg);
	System.out.println("Email a �t� envoy�");
} catch (AddressException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (MessagingException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
  }
  
  public void main(String[] args) {
	  sendEmail();
}
  
  // format du lien email : localhost8080:/anpep/inscription?user=codeuser&time=codetime
  public void inscriptionformerMail() {
	  subject = "Inscription sur le service Anpep d'émargment en ligne ";
	  object = "Bienvenue au service d'émargement en ligne de l'Anpep cher formateur,"
	  		+ "Veuillez cliquer sur le lien ci-dessous pour vous inscrire"
	  		+ "Attention cet email s'autodétruira à minuit.."
	  		+ "(Je ne déconne pas, inscris toi vite ou ca va barder)";
	  object += "\n " + link + getCodedLinkFormer() + "&time=" + getCodedTime();
  }
  
  private String getCodedTime() { // retourne la date du jour hashée
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
	String date = ymd.format(cal.getTime());
	return BCrypt.hashpw(date, BCrypt.gensalt());
}

public void inscriptionInternMail() {
	  subject = "Inscription sur le service Anpep d'émargment en ligne ";
	  object = "Bienvenue au service d'émargement en ligne de l'Anpep cher stagiaire,"
		  		+ "\nVeuillez cliquer sur le lien ci-dessous pour vous inscrire"
		  		+ "\nAttention cet email s'autodétruira à minuit."
		  		+ "(Je ne déconne pas, inscris toi vite ou ca va barder)";
	  object += "\n " + link + getCodedLinkIntern() + "&time=" + getCodedTime();
  }
  
  public String getCodedLinkFormer() {
	  return BCrypt.hashpw("1", BCrypt.gensalt());
  }
  
  public String getCodedLinkIntern() {
	  return BCrypt.hashpw("2", BCrypt.gensalt());
  }
}