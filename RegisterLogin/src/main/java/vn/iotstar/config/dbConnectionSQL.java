package vn.iotstar.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class dbConnectionSQL  {
	
	private final String servername = "localhost";
	private final String dbname = "TaiKhoan";
	private final String user = "sa";
	private final String pass = "123456";
	private final String port_num = "1433";
	private final String instance = "";
	
	public Connection getConnection()
	{
		Connection conn = null;
		try
		{
			String url = "jdbc:sqlserver://"+servername+":"+port_num+"\\" + instance + ";databaseName=" + dbname+ ";encrypt=true;trustServerCertificate=true;";
			if (instance == null || instance.trim().isEmpty())
			{
				url = "jdbc:sqlserver://"+servername+":"+port_num+";databaseName="+dbname+ ";encrypt=true;trustServerCertificate=true;";
				
			}
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url,user,pass);
		}catch (SQLException e) {
		    e.printStackTrace();		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static void main(String[] args)
	{
		try {
			Connection conn = new dbConnectionSQL().getConnection();
			System.out.println(conn );
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
