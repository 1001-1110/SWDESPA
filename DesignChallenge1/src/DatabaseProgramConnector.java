/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Ashen One
 */
public class DatabaseProgramConnector implements DatabaseConnector{
	//com.mysql.jdbc.Driver
	//jdbc:mysql://127.0.0.1:3306/
	//root
	//july
	//SWDESPA
	private final String DRIVER_NAME;
	private final String URL;
	private final String USERNAME;
	private final String PASSWORD;
	private final String DATABASE;
	
	public DatabaseProgramConnector(String DRIVER_NAME, String URL, String USERNAME, String PASSWORD, String DATABASE) {
		this.DRIVER_NAME = DRIVER_NAME;
		this.URL = URL;
		this.USERNAME = USERNAME;
		this.PASSWORD = PASSWORD;
		this.DATABASE = DATABASE;
	}
	
	public Connection getConnection () {
		try {
			Class.forName(DRIVER_NAME);
			Connection connection = DriverManager.getConnection(
					URL + 
					DATABASE + "?autoReconnect=true&useSSL=false", 
					USERNAME,
					PASSWORD);
			return connection;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
