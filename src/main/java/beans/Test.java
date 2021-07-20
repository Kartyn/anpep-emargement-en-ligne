package beans;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Test {

	public static void main(String[] args) {
//		Mail mail = new Mail();
//		mail.sendEmail();
		
		Calendar now = Calendar.getInstance();
		long nbOfSec = now.getTimeInMillis()/1000;
		
		System.out.println("nof of sec = " + nbOfSec);
		
		Mail m = new Mail();
		m.inscriptionInternMail();
		System.out.println(m.getObject());
		System.out.println(now.getTime());
		SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
		String date = ymd.format(now.getTime());
		System.out.println("format date =" + date);
	}
	
}
