package servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import beans.Admin;
import beans.Day;
import beans.Formation;
import beans.HalfDay;
import beans.User;

/**
 * Servlet implementation class ServFormation
 */
@WebServlet("/formation")
public class ServFormation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServFormation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
ArrayList<User> interns = (ArrayList<User>) new User(true).selectAll();
		
		Workbook wb = new HSSFWorkbook();
		
		Sheet sheet = wb.createSheet("Emargement stagiaires");
		String[] columnNames = { "Date du jour", "Nom stagiaire","Prénom stagiaire","Présence le matin","Présence l'après-midi"}; // 5
		Font hF = wb.createFont();
		hF.setBold(true);
		hF.setColor(IndexedColors.PINK1.index);
		CellStyle headerStyle = wb.createCellStyle();
		headerStyle.setFont(hF);
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
		
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < columnNames.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columnNames[i]);
			cell.setCellStyle(headerStyle);
		}
		// remplissage des données
		for (int i = 0; i < interns.size(); i++) {

			Row r = sheet.createRow(i+1);
			r.createCell(0).setCellValue(interns.get(i).getAdress());
			r.createCell(1).setCellValue(interns.get(i).getEmail());
			r.createCell(2).setCellValue(interns.get(i).getSurname());
			r.createCell(3).setCellValue("oui");
			r.createCell(4).setCellValue("oui");
		}
		for (int i = 0; i < columnNames.length; i++) {
			sheet.autoSizeColumn(i);
		}
			Sheet sheet2 = wb.createSheet("deuxieme"); //pour voir
					
		// Write the output to a file
			System.out.println(System.getProperty("user.home") + "/Desktop");
		FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Patatus/Desktop/stagiaires.xls");
		wb.write(fileOut);
		fileOut.close();
		System.out.println("fini !!");
		
		
		Formation f = new Formation();
		Day d = new Day();
		User u = new User();
		HalfDay hd = new HalfDay();
		Admin a = new Admin();
//		if(request.getParameter("to") != null) {
//			f.setIdFormation(Integer.parseInt(request.getParameter("to")));
//			f.select();
//			d.setIdFormation(f.getIdFormation());
//			
//			ArrayList<Day> days = d.selectAllByFormation();
//			ArrayList<User> interns = u.selectAllInternsByFormation();
//			
//			
//		}
		
		
		
		
	//	ArrayList<User> interns = (ArrayList<User>) u.selectAll();
		request.setAttribute("interns", interns);
		
		Calendar c = Calendar.getInstance();
		request.setAttribute("datelocale", c.getTime());
		c.add(Calendar.DATE,1);
		request.setAttribute("datelocalep1", c.getTime());
		
		this.getServletContext().getRequestDispatcher("/jsp/formation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}