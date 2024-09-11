package vn.iotstar.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnectionMySQL {
	private final String servername = "localhost";
	private final String dbname = "demo";
	private final String user = "root";
	private final String pass = "123456";
	private final String port_num = "3306";


	public Connection getConnection() {
		Connection conn = null;
		try {
			String url = "jdbc:mysql://"+servername+":"+port_num+"/databasename"+dbname;
			Class.forName("com.mysql.cj.jdbc.mysql");
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		try {
			Connection conn = new dbConnectionSQL().getConnection();
			System.out.println(conn);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
