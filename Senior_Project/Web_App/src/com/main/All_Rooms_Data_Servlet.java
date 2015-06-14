package com.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class All_Rooms_Data_Servlet
 */
@WebServlet("/All_Rooms_Data_Servlet")
public class All_Rooms_Data_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public All_Rooms_Data_Servlet() {
        super();
        System.out.println("[All_Rooms_Data_Servlet Class] Constructor is Called");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[All_Rooms_Data_Servlet Class] doGet() is Called");
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter pw = response.getWriter();
		/*int x = 1;
		int y = 2;
		int z = 3;*/
		
		int x = (int) Database.bd.pull_db_last_value_for_room1();
		int y = (int) Database.bd.pull_db_last_value_for_room2();
		int z = (int) Database.bd.pull_db_last_value_for_room3();
		
		/*JSONObject js_obj = new JSONObject();
		js_obj.put("key1", x);
		js_obj.put("key2", y);
		*/
		
		JSONObject js_obj = new JSONObject();
		
		JSONArray js_array = new JSONArray();
		js_array.put(x);
		js_array.put(y);
		js_array.put(z);
		
		js_obj.put("array", js_array);
		
		pw.print(js_obj.toString());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
