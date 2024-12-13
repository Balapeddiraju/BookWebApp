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

@WebServlet("/bookList")
public class Booklist extends HttpServlet{
	
	String url ="jdbc:mysql://localhost:3306/books";
	String username ="root";
	String password ="root";
	String SELECT_QUERY ="Select Id,BookName,BookEdition,BookPrice from bookdata";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out =resp.getWriter();
		resp.setContentType("text/html");
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			Connection connection =DriverManager.getConnection(url,username,password);
			
			Statement statement =connection.createStatement();
			ResultSet result=statement.executeQuery(SELECT_QUERY);
			
			out.print("<table border='1' align='center'>");
			out.print("<tr>");
			out.print("<th>Book Id</th>");
			out.print("<th>Book Name</th>");
			out.print("<th>Book Edition</th>");
			out.print("<th>Book Price</th>");
			out.print("<th>Edit</th>");
			out.print("<th>Delete</th>");

			out.print("</tr>");
			
			while(result.next()) {
				out.print("<tr>");
				out.print("<td>"+result.getInt(1)+"</td>");
				out.print("<td>"+result.getString(2)+"</td>");
				out.print("<td>"+result.getString(3)+"</td>");
				out.print("<td>"+result.getFloat(4)+"</td>");
				out.print("<td><a href='editScreen?id="+result.getInt(1)+"'>Edit</a></td>");
				out.print("<td><a  href='deleteurl?id="+result.getInt(1)+"'>Delete</a></td>");

				out.print("</tr>");
			}
			out.print("</table>");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print( "<a href='index.html'>Home</a>");

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
