package com.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Post_Servlet
 */
@WebServlet("/Post_Servlet")
public class Post_Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Post_Servlet() {
        super();
        System.out.println("[Post_Servlet Class] Constructor() is called");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[Post_Servlet Class] doPost() is Called");
		
		response.setContentType("text/url");
		
		String id = request.getParameter("id");
		String temp = request.getParameter("temp");
		
		System.out.println("[Post_Servlet Class] doPost() data recieved: id= "+id+"  temp= "+temp);
		
		PrintWriter pw = response.getWriter();
		/*pw.println(id);
		pw.println(temp);*/
		//pw.println("id: "+id + "  temp: "+temp);
		
		Byte x = Byte.valueOf(id);
		Byte y = Byte.valueOf(temp);
		System.out.println("[Post_Servlet Class] doPost() converted string to byte: x= "+ id +"  y= "+y);

		byte a = x;
		byte b = y;
		System.out.println("[Post_Servlet Class] doPost() converted Byte to byte: x= "+ id +"  y= "+y);
		
		Serial_Example.serial_Example_Obj.send_Data_Serial_Port(a, b);
		pw.println("id: "+id + "  temp: "+temp);
		
	}

}
