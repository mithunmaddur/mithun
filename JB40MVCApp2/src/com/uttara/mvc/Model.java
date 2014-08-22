package com.uttara.mvc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model {

	public String register(RegBean rb) {
		// first perform user input validations. if success move forward. else return msg to CS
		// then perform business validations (use helper to get conn to db). if success, move forward. else return msg to CS
		// apply busines logic. if success, return so to CS. else return msg.
		System.out.println("inside Models register(). going to invoke validate() on bean");
		String result = rb.validate();
		System.out.println("after validation of bean with result = "+result);
		if(result.equals(Constants.SUCCESS))
		{
			System.out.println("user input validations have succeeded. going to perform business valid...");
			
			Connection con = null; PreparedStatement ps=null;ResultSet rs = null;
			try
			{
				con = JDBCHelper.getConnection();
				if(con==null)
					return "problem with your forecast. database not working. contact somebody!";
				else
				{
					System.out.println("connection established "+con);
					ps  = con.prepareStatement("select * from customer where email = ?");
					ps.setString(1, rb.getEmail());
					ps.execute();
					rs = ps.getResultSet();
					if(rs.next())
					{
						return "Yo you are using an already registered email id. try again mister. illandre...";
					}
					else
					{
						System.out.println("business validations have succeeded");
						ps = con.prepareStatement("insert into customer(name,email,pass) values(?,?,?)");
						ps.setString(1, rb.getUname());
						ps.setString(2, rb.getEmail());
						ps.setString(3, rb.getPass());
						ps.execute();
						
						return Constants.SUCCESS;		
					}
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return "oops.theres been a db operation problem. msg = "+e.getMessage();
			}
			finally
			{
				JDBCHelper.close(rs);
				JDBCHelper.close(ps);
				JDBCHelper.close(con);
				
			}
			
				
		}
		else
			return result;
		
		

	}

	public String authenticate(LoginBean rb) {
		
		System.out.println("inside Models authenticate(). going to invoke validate() on bean");
		String result = rb.validate();
		System.out.println("after validation of bean with result = "+result);
		if(result.equals(Constants.SUCCESS))
		{
			System.out.println("user input validations have succeeded. going to perform business valid...");
			
			Connection con = null; PreparedStatement ps=null;ResultSet rs = null;
			try
			{
				con = JDBCHelper.getConnection();
				if(con==null)
					return "problem with your forecast. database not working. contact somebody!";
				else
				{
					System.out.println("connection established "+con);
					ps  = con.prepareStatement("select * from customer where email = ? and pass = ?");
					ps.setString(1, rb.getEmail());
					ps.setString(2, rb.getPass());
					ps.execute();
					rs = ps.getResultSet();
					if(rs.next())
					{
						return Constants.SUCCESS;	
					}
					else
					{
						System.out.println("business validations has failed");
						return "boss who are you is not known. try again";		
					}
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return "oops.theres been a db operation problem. msg = "+e.getMessage();
			}
			finally
			{
				JDBCHelper.close(rs);
				JDBCHelper.close(ps);
				JDBCHelper.close(con);
				
			}
			
				
		}
		else
			return result;
		
	}

	public List<RegBean> getRegisteredUsers() {

		Connection con = null; PreparedStatement ps=null;ResultSet rs = null;
		try
		{
			con = JDBCHelper.getConnection();
			if(con==null)
				throw new RuntimeException("problem connection to db");
			else
			{
				System.out.println("connection established "+con);
				ps  = con.prepareStatement("select * from customer");
				ps.execute();
				rs = ps.getResultSet();
				List<RegBean> li = new ArrayList<RegBean>();
				RegBean rb = null;
				while(rs.next())
				{
					rb = new RegBean();
					rb.setEmail(rs.getString("email"));
					rb.setUname(rs.getString("name"));
					li.add(rb);
				}
				return li;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			 throw new RuntimeException("oops.theres been a db operation problem. msg = "+e.getMessage());
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps);
			JDBCHelper.close(con);
			
		}		
	}

}























