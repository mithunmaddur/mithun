package com.uttara.mvc;

public class RegBean {

	private String uname,email,pass,rpass;

	public String validate()
	{
		String msg = "";
		if (uname==null || uname.trim().equals(""))
			msg = "Enter maadi your name please";
		if (email==null || email.trim().equals(""))
			msg = msg + " <br/> " + " Email is mandatory. Enter maadi.";
		if(pass!=null && rpass!=null && !pass.equals(rpass))
		{
			msg = msg + "<br/>" + " Boss your pass is not equal to repeat pass. Correct it now!";
		}
		if (msg.equals(""))
			return Constants.SUCCESS;
		else
			return msg;
	}
	
	public RegBean() {
		System.out.println("inside RegBean constr");
	}
	
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
		System.out.println("inside setUname "+uname);
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

	public String getRpass() {
		return rpass;
	}

	public void setRpass(String rpass) {
		this.rpass = rpass;
	}
	
	
	
}
