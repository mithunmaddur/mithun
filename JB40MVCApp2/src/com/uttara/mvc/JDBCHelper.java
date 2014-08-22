package com.uttara.mvc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCHelper {

	public static void close(ResultSet rs)
	{
		try
		{
			if(rs!=null)
				rs.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void close(Statement s)
	{
		try
		{
			if(s!=null)
				s.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void close(Connection con)
	{
		try
		{
			if(con!=null)
				con.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()
	{
		
		Connection con = null; 
		
		try {		
			System.out.println(Class.forName(Constants.DRIVERNAME));
			String url = Constants.URL;
			String uid = Constants.UID;
			String pwd = Constants.PWD;	
			
			con = DriverManager.getConnection(url,uid,pwd);
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return con;
	}
	
	
}
