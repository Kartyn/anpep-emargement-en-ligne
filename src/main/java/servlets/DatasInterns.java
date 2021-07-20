package servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.ArrayList;

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


import beans.User;

/**
 * Servlet implementation class DatasInterns
 */


public class DatasInterns extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatasInterns() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<User> interns = (ArrayList<User>) new User(true).selectAll();
		
		Workbook wb = new HSSFWorkbook();
		
		Sheet sheet = wb.createSheet("Emargement stagiaires");
		String[] columnNames = { "Date du jour", "Nom stagiaire","Prénom stagiaire","Présence le matin","Présence l'après-midi"}; // 5
		Font hF = wb.createFont();
		hF.setBold(true);
		hF.setColor(IndexedColors.AQUA.index);
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

			Row r = sheet.createRow(i);
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
		FileOutputStream fileOut = new FileOutputStream("/user/Patatus/Desktop/stagiaires.xls");
		wb.write(fileOut);
		fileOut.close();
		System.out.println("fini !!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	//	if (request.getParameter("allinterns").equals("allinterns")) { // tous les interns
			ArrayList<User> interns = (ArrayList<User>) new User(true).selectAll();
			
			Workbook wb = new HSSFWorkbook();
			
			Sheet sheet = wb.createSheet("Emargement stagiaires");
			String[] columnNames = { "Date du jour", "Nom stagiaire","Prénom stagiaire","Présence le matin","Présence l'après-midi"}; // 5
			Font hF = wb.createFont();
			hF.setBold(true);
			hF.setColor(IndexedColors.AQUA.index);
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

				Row r = sheet.createRow(i);
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
			FileOutputStream fileOut = new FileOutputStream("/user/Patatus/Desktop/stagiaires.xls");
			wb.write(fileOut);
			fileOut.close();
			System.out.println("fini !!");
			
			
	//	}
	//	else if (request.getParameter("allinterns").equals("oneintern")) {	// un intern en particulier
	//		int idIntern = Integer.parseInt(request.getParameter("intern"));
	//		User i = new User(true);
			
			
		//}
		
		this.getServletContext().getRequestDispatcher("/jsp/formation.jsp").forward(request, response);
	}

}
