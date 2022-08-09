package com.simplilearn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.util.DBConnection;

@WebServlet("/ListProduct")
public class ListProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ListProductServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>Product Id</th>");
		out.println("<th>Product Name</th>");
		out.println("<th>Product Date Added</th>");
		out.println("</tr>");
		
		try {
			DBConnection dbConnection = new DBConnection();
			Connection con = dbConnection.getConnection();
			if(con != null) {
				out.println("<h1 style='color:green'>Sucess</h1>");
			}
			Statement stm = con.createStatement();
			
			ResultSet set = stm.executeQuery("select * from product_list");
			
			while(set.next()) {
				out.println("<tr>");
				Integer ID = set.getInt(1);
				String name = set.getString(2);
				Date date = set.getDate(3);			
			
				out.println("<td>"+ID +"</td>");
				out.println("<td>"+name+"</td>");
				out.println("<td>"+date+"</td>");
				out.println("</tr>");
			}
			
			set.close();
			dbConnection.releaseConnection();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("<h1 style='color:red'>Failed</h1>");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
