package it.polito.tdp.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {

	static private final String jdbcUrl = "jdbc:mysql://localhost/iscritticorsi?user=root&password";
	static private Connection connection = null;
	
	public static Connection getConnection(){
		try{
			if(connection == null){
				connection = DriverManager.getConnection(jdbcUrl);
			}
			return connection;
		} catch (SQLException e){
			e.printStackTrace();
			throw new RuntimeException("Errore nel db", e);
		}
		
		
	}
	
}
