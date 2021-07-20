package beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class Admin implements Crud<Admin>{

	private int idAdmin;
	private String email;
	private String password;
	
	public int getIdAdmin() {
		return idAdmin;
	}
	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}
	@Override
	public void insert() {
		String query = "INSERT INTO admin ("
				+ "email, password)"
				+ " VALUES (?,?);";
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			p.setString(1, getEmail());
			p.setString(2, getPassword());
			
			p.executeUpdate();
			
			ResultSet result = p.getGeneratedKeys();
			while (result.next())
				setIdAdmin(result.getInt(1));
			
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<?> selectAll() {
		return null;
	}
	
	@Override
	public Admin select() {
		String query = "SELECT id_admin, email, password FROM admin";
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			System.out.println("avant lexecute");
			ResultSet result = p.executeQuery();
			while (result.next()) {
				
				this.setEmail(result.getString("email"));
				this.setPassword(result.getString("password"));
				this.setIdAdmin(result.getInt("id_admin"));
			}
			System.out.println("apres lexecute");
			DbConnect.getConnector().close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return this;
	}
	@Override
	public void update() {
		String query = "update admin set `email` = ?, `password` = ?"
					+ " where id_admin = ?";
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			
			p.setString(1, getEmail());
			p.setString(2, getPassword());
			p.setInt(3, getIdAdmin());
			
			
			p.executeUpdate();
			
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void delete() {
		String query = "delete from admin"
				+ " where id_admin = ?;";
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			p.setInt(1, getIdAdmin());
			p.executeUpdate();
			
			DbConnect.getConnector().close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
