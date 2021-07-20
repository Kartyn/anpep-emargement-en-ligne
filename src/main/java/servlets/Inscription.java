package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Servlet implementation class Inscription
 */

public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("user") != null) {
			if (BCrypt.checkpw("1",request.getParameter("user"))) { // inscription former
				System.out.println("vous etes un formateur");
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
				String date = ymd.format(cal.getTime());
				 if (request.getParameter("time") != null) {
					 System.out.println("ca essaye");
					 System.out.println(date);
				 }
				 if (request.getParameter("time") != null && BCrypt.checkpw(date, request.getParameter("time"))){
					 System.out.println("yay le formateur peut senregistrer");
				 }
				
				
			}
			else if (BCrypt.checkpw("2",request.getParameter("user"))) { // inscription intern
				System.out.println("vous etes un stagiaire");
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
				String date = ymd.format(cal.getTime());
				 if (request.getParameter("time") != null && BCrypt.checkpw(date, request.getParameter("time"))){
					 System.out.println("yay le stagiaire peut senregistrer");
				 }
			}

		}
		else {
			System.out.println("Vous n'avez rien à faire ici !");
		}
		this.getServletContext().getRequestDispatcher("/jsp/inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
