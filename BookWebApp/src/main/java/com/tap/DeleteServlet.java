package com.tap;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet{
	
	String url ="jdbc:mysql://localhost:3306/books";
	String username ="root";
	String password ="root";
	public static final String DELETE_QUERY ="Delete from bookData where id =? ";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out =resp.getWriter();
		resp.setContentType("text/html");
		
		int id =Integer.parseInt(req.getParameter("id"));
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			Connection connection =DriverManager.getConnection(url,username,password);
			
			PreparedStatement pmst =connection.prepareStatement(DELETE_QUERY);
			
			pmst.setInt(1,id);
			
			int count =pmst.executeUpdate();
			if(count==1) {
				out.print("<h3 style='color:green;'>Record is Deleted Successfully</h3>");
			}
			else {
				out.print("<h1 style='color:Red;'>Record is Not Deleted </h1>");
			}
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
