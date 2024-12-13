package com.tap;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
	String url ="jdbc:mysql://localhost:3306/books";
	String username ="root";
	String password ="root";
	String INSERT_QUERY ="Insert Into `bookdata`(`BookName`,`BookEdition`,`BookPrice`)values(?,?,?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out =resp.getWriter();
		resp.setContentType("text/html");
		String bookname =req.getParameter("bookname");
		String bookedition =req.getParameter("bookedition");
		float bookprice =Float.parseFloat(req.getParameter("bookprice"));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			Connection connection =DriverManager.getConnection(url,username,password);
			
			PreparedStatement pmst =connection.prepareStatement(INSERT_QUERY);
			
			pmst.setString(1, bookname);
			pmst.setString(2, bookedition);
			pmst.setFloat(3, bookprice);
			
			int count =pmst.executeUpdate();
			
			if(count==1) {
				out.println("<h1 style='color:green;'>Register Success</h1>");
			}
			else {
				out.println("<h1 style='color:red;'Register failure");
			}
			
			
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		out.print( "<h3><a href='index.html'>Home</a></h3>");


		out.print( "<h3><a href='bookList'>Book List</a></h3>");

	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
