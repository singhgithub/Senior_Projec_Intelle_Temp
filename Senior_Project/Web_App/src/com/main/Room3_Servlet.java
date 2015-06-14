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
 * Servlet implementation class Room3_Servlet
 */
@WebServlet("/Room3_Servlet")
public class Room3_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Room3_Servlet() {
        super();
        System.out.println("[Room3_Servlet Class] Constructor is Called");    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[Room2_Servlet Class] doGet() is Called");
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter pw = response.getWriter();

		double[] response_data_array = Database.bd.pull_Data_For_Room3_Graph();
		
		/*JSONObject js_obj = new JSONObject();
		js_obj.put("key1", x);
		js_obj.put("key2", y);
		*/
		
		int[] data_array= new int[response_data_array.length];
		//convert double to int, its a just do it for nw
		for (int i = 0; i < response_data_array.length; i++) {
			data_array[i] = (int) response_data_array[i];
		}
		
		JSONObject js_obj = new JSONObject();
		
		JSONArray js_array = new JSONArray();
		for (int i : data_array) {
			js_array.put(i);
		}
		
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
