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

@WebServlet("/editurl")
public class EditServlet extends HttpServlet{
	
	String url ="jdbc:mysql://localhost:3306/books";
	String username ="root";
	String password ="root";
	public static final String UPDATE_QUERY ="UPDATE bookdata set BookName=?,BookEdition=?,BookPrice=? where id =?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out =resp.getWriter();
		resp.setContentType("text/html");
		
		int id =Integer.parseInt(req.getParameter("id"));
		
		String bookname =req.getParameter("bookname");
		String bookedition =req.getParameter("bookedition");
		Float bookprice =Float.parseFloat(req.getParameter("bookprice"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			Connection connection =DriverManager.getConnection(url,username,password);
			
			PreparedStatement pmst =connection.prepareStatement(UPDATE_QUERY);
			pmst.setString(1, bookname);
			pmst.setString(2, bookedition);
			pmst.setFloat(3, bookprice);
			pmst.setInt(4, id);
			
			int count =pmst.executeUpdate();
			if(count==1) {
				out.print("<h3 style='color:green;'>Record is Edited Successfully</h3>");
			}
			else {
				out.print("<h1 style='color:Red;'>Record is Not Edited </h1>");
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
