package com.contactForm;

import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet(
		description = "Contact Form", 
		urlPatterns = { "/contactForm" }, 
		initParams = { 
				@WebInitParam(name = "name", value = "null"), 
				@WebInitParam(name = "email", value = "null"),
				@WebInitParam(name = "phone", value = "null"),
				@WebInitParam(name = "request", value = "null"),
				@WebInitParam(name = "ipaddress", value = "null")
		})
public class contactForm extends HttpServlet {
	static final String JDBC_DRIVER = "com.postgresql.jdbc.Driver";  
    static final String DB_URL = "jdbc:postgresql://localhost/contactdb?stringtype=unspecified";

	   //  Database credentials
	static final String USER = "contact_user";
	static final String PASS = "fc171172edc879c2de1db5fa3daebdb345649e06";
	private static final long serialVersionUID = 1L;
	   Connection conn = null;
	   PreparedStatement stmt = null;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date date = new Date();
		String name = new String();
		String email = new String();
		String phone = new String();
		String clientRequest = new String();
		String ipaddress = new String();
		name = request.getParameter("name");
		email = request.getParameter("email");
		phone = request.getParameter("phone");
		clientRequest = request.getParameter("request");
		ipaddress = request.getHeader("X-FORWARDED-FOR");
        if (ipaddress == null || ipaddress.isEmpty()) {
            ipaddress = request.getRemoteAddr();
        }
		
		PrintWriter out = new PrintWriter(response.getWriter());
		StringBuffer utilOutput = new StringBuffer();
		
		if(name == null || name.isEmpty() || email == null || email.isEmpty() || phone == null || phone.isEmpty() || clientRequest == null || clientRequest.isEmpty()) {
			utilOutput.append("<span style='color:red'>One or more fields are empty</span>");
			request.setAttribute("utilOutput", utilOutput.toString());
			request.getRequestDispatcher("/contact.jsp").forward(request, response);
			response.sendRedirect("contact.jsp");
		}
		
		if(clientRequest.length() > 500 || phone.length() > 14 || email.length() > 40 || name.length() > 40) {
			utilOutput.append("<span style='color:red'>One or more fields contain too many characters</span>");
			request.setAttribute("utilOutput", utilOutput.toString());
			request.getRequestDispatcher("/contact.jsp").forward(request, response);
			response.sendRedirect("contact.jsp");
		}
		
		   try{
			   out.println("Trying to load driver...");
			      //STEP 2: Register JDBC driver
			      Class.forName("org.postgresql.Driver");
			      //out.println("Driver loaded...");

			      //STEP 3: Open a connection
			      //out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);
			      //out.println("Connected....");

			      //STEP 4: Inserting Records
			      //out.println("conn.createStatment....");
			      stmt = conn.prepareStatement("INSERT INTO contact_info (name, phone, email, request, ip_address, date_time) values (?, ?, ?, ?, ?, ?)");
			      stmt.setString(1, name);
			      stmt.setString(2, phone);
			      stmt.setString(3, email);
			      stmt.setString(4, clientRequest);
			      stmt.setString(5, ipaddress);
			      stmt.setString(6, date.toString());
			      //out.println("Created String sql...\n" + sql);
			      //out.println("stmt.executeUpdate...");
			      stmt.executeUpdate();
			      //out.println("update success...");
			      utilOutput.append("Request successfully submitted!");
			      request.setAttribute("utilOutput", utilOutput.toString());
			      request.getRequestDispatcher("/contact.jsp").forward(request, response);
			      response.sendRedirect("contact.jsp");
		
		   }
		   catch(SQLException se){
			   //Handle errors for JDBC
			   	  out.println("SQLExecption se: " + se.getMessage());
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      out.println("SQLExeption e: (supposedly for Class.forName) " + e.getMessage());
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			    	  //out.println("SQLExeption: " + se.getMessage());
			      }//end finally try
			   }//end try
	}
}

