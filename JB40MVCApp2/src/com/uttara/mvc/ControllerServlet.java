package com.uttara.mvc;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ControllerServlet
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside process");
		
		RequestDispatcher rd = null;
		try
		{
			Model model = new Model();
			String uri = request.getRequestURI();
			System.out.println("uri = "+uri);
			
			if (uri.contains("/openRegisterView"))
			{
				System.out.println("inside openRegisterView if block");
				rd = request.getRequestDispatcher("Register.jsp");
				rd.forward(request, response);
			}
			if(uri.contains("/register"))
			{
				System.out.println("inside register if block");
				// invoke models register() and pass populated bean as parameter
				// take the return value - string. check if success, forward to success view. 
				// else forward back to register view (save the error msg in request)
				
				RegBean rb = (RegBean) request.getAttribute("reg");
				System.out.println("before calling models register()");
				String result = model.register(rb);
				System.out.println("after calling register() with result = "+result);
				if(result.equals(Constants.SUCCESS))
				{
					rd = request.getRequestDispatcher("Success.jsp");
					request.setAttribute("msg", "Your registration succeeded!");
					rd.forward(request, response);
				}
				else
				{
					rd = request.getRequestDispatcher("Register.jsp");
					request.setAttribute("errorMsg", result);
					rd.forward(request, response);
				}
			}
			if(uri.contains("/openLoginView"))
			{
				System.out.println("inside openLoginView if block");

				rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);	
			}
			if(uri.contains("/login"))
			{
				System.out.println("inside login if block");
				LoginBean rb = (LoginBean) request.getAttribute("user");
				System.out.println("before calling models authenticate()");
				String result = model.authenticate(rb);
				System.out.println("after calling authenticate() with result = "+result);
				if(result.equals(Constants.SUCCESS))
				{
					rd = request.getRequestDispatcher("Success.jsp");
					request.setAttribute("msg", "Your login has succeeded!");
					
					HttpSession session = request.getSession(true);
					session.setAttribute("user", rb.getEmail());
					rd.forward(request, response);
				}
				else
				{
					rd = request.getRequestDispatcher("Login.jsp");
					request.setAttribute("errorMsg", result);
					rd.forward(request, response);
				}				
			}
			if(uri.contains("/logout"))
			{
				System.out.println("inside logout if block");
				
				HttpSession session = request.getSession(false);
				if(session!=null)
				{
					session.removeAttribute("user");
					session.invalidate();
				}
				rd = request.getRequestDispatcher("Success.jsp");
				request.setAttribute("msg", "You have successfully logged out!");				
				rd.forward(request,response);
			}
			
			if(uri.contains("/fetchUsers"))
			{
				System.out.println("inside fetchusers if block");
				
				HttpSession session = request.getSession(false);
				if(session==null || session.getAttribute("user")==null)
				{
					rd = request.getRequestDispatcher("Error.jsp");
					request.setAttribute("errorMsg", "First login maadi. Aamele users nodi!");
					rd.forward(request, response);
				}
				else
				{
				
					List<RegBean> li = model.getRegisteredUsers();
					rd = request.getRequestDispatcher("DisplayUsers.jsp");
					request.setAttribute("listOfusers", li);
					rd.forward(request, response);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			rd = request.getRequestDispatcher("Error.jsp");
			request.setAttribute("errorMsg", "Theres been a problem. Contact admin. msg = "+e.getMessage());
			rd.forward(request, response);
		}
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside doGet");
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside dopost");
		process(request,response);
	}

}
