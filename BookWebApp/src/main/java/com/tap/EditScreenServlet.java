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

@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet{
	
	String url ="jdbc:mysql://localhost:3306/books";
	String username ="root";
	String password ="root";
	public static final String SELECT_QUERY ="Select BookName,BookEdition,BookPrice from bookdata Where id =?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out =resp.getWriter();
		resp.setContentType("text/html");
		
		int id =Integer.parseInt(req.getParameter("id"));
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			Connection connection =DriverManager.getConnection(url,username,password);
			
			PreparedStatement pmst =connection.prepareStatement(SELECT_QUERY);
			pmst.setInt(1, id);
			ResultSet result =pmst.executeQuery();
			
			result.next();
			out.print("<form action='editurl?id=" + id + "' method='post'>");
			out.print("<table align='center'>");
			out.print("<tr>");
			out.print("<td>Book Name</td>");
			out.print("<td> <input type='text' name='bookname' value='"+result.getString(1)+"'></td>");
			out.print("</tr");
			out.print("<br>");
			
			out.print("<tr>");
			out.print("<td>Book Edition</td>");
			out.print("<td> <input type='text' name='bookedition' value ='"+result.getString(2)+"'></td>");
			out.print("</tr>");
			
			out.print("<tr>");
			out.print("<td>Book Price</td>");
			out.print("<td> <input type ='text' name='bookprice' value='"+result.getFloat(3)+"'></td>");
			out.print("</tr>");
			
			out.print("<tr>");
			out.print("<td><input type='submit' value='Edit' style='background-color: #28a745; color: white; border: none; padding: 5px 10px; border-radius: 5px; cursor: pointer;'></td>");
			out.print("<td><input type='reset' value='Cancel' style='background-color: #dc3545; color: white; border: none; padding: 5px 10px; border-radius: 5px; cursor: pointer;'></td>");


			out.print("</tr>");

			
			out.print("</table>");
			out.print("</form>");
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print( "<h3><a href='index.html'>Home</a></h3>");

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
