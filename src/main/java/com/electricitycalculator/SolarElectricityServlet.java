package com.electricitycalculator;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
//import org.json.JSONObject;
import com.google.gson.Gson;

import com.electricitycalculator.SolarElectricityCalculatorAndGraph;

@MultipartConfig
public class SolarElectricityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
	    	// Get user input from the HTML form
        	int solarTotal = Integer.parseInt(request.getParameter("solarTotal"));
	        double solarArea = Double.parseDouble(request.getParameter("solarArea"));
	        double solarEfficiency = Double.parseDouble(request.getParameter("solarEfficiency"));
	        double solarPerformance = Double.parseDouble(request.getParameter("solarPerformance"));
	        
	        Part csv_filePart = request.getPart("solarCSV");
	        InputStream csv_fileContent = csv_filePart.getInputStream();
	
	        // Execute the Java code with the provided inputs
	        SolarElectricityCalculatorAndGraph calculator = new SolarElectricityCalculatorAndGraph(solarTotal, solarArea, solarEfficiency, solarPerformance, csv_fileContent);
	        
	        // Prepare response data
	        Map<String, Object> responseData = new HashMap<>();
	        responseData.put("monthsList", calculator.monthsList);
	        responseData.put("electricityGeneratedList", calculator.electricityGeneratedList);
	
	        // Send response as JSON
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(new Gson().toJson(responseData));
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
    }
}