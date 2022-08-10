package com.simplilearn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.util.DBConnection;


@WebServlet("/addProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Integer id = Integer.valueOf(request.getParameter("ID"));//from html
		String name = request.getParameter("name"); //from html
		
		try {
			//get connection
			DBConnection dbConnection = new DBConnection();
			Connection con = dbConnection.getConnection();
			
	        //prepare statement
			 PreparedStatement preparedStmt = 
					 con.prepareStatement("insert into product_list(id, name, date_added) values(?, ?, now()) ");
			 
			 preparedStmt.setInt(1, id);
			 preparedStmt.setString(2, name);
			 
			 preparedStmt.executeUpdate();
			 
			 preparedStmt.close();
			 dbConnection.releaseConnection();
			 
			 request.getRequestDispatcher("index.html").include(request, response);
			 out.println("<h1 style='color:green'>update Successful</h1>");
			 
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
