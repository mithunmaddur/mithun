package com.uttara.mvc;

public class LoginBean {

	private String uname,email,pass,rpass;

	public String validate()
	{
		String msg = "";
		if (email==null || email.trim().equals(""))
			msg = msg + " <br/> " + " Email is mandatory. Enter maadi.";
		if(pass==null || pass.trim().equals(""))
		{
			msg = msg + "<br/>" + " Boss your pass is empty. Enter it or get lost!";
		}
		if (msg.equals(""))
			return Constants.SUCCESS;
		else
			return msg;
	}
	
	public LoginBean() {
		System.out.println("inside LoginBean constr");
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		System.out.println("inside setEmail "+email);
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	
	
	
}
