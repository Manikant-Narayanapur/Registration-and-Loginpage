package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.UserDao;
import com.DB.DBconnect;
import com.entities.user;

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String name=request.getParameter("uname");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		user us=new user();
		us.setName(name);
		us.setEmail(email);
		us.setPassword(password);
		
		UserDao dao=new UserDao(DBconnect.getConnection());
		boolean f=dao.userRegister(us);
		if(true)
		{
		 HttpSession session=request.getSession();
		 session.setAttribute("reg-msg","Registration Sucessfully");
		 response.sendRedirect("register.jsp");
		}
		else
		{
			HttpSession session=request.getSession();
			 session.setAttribute("error-msg", "Something went wrong Server");
			 response.sendRedirect("register.jsp");
		}

	}

}
