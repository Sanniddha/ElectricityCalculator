package com.electricitycalculator;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import com.google.gson.Gson;

import com.electricitycalculator.WindElectricityCalculatorAndGraph;


@WebServlet(name="WindElectricityServlet", urlPatterns={"/calculate-wind-energy/"})
@MultipartConfig
public class WindElectricityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user input from the HTML form
    	int windTotal = Integer.parseInt(request.getParameter("windTotal"));
        double airDensity = Double.parseDouble(request.getParameter("airDensity"));
        double bladeLength = Double.parseDouble(request.getParameter("bladeLength"));
        
        Part csv_filePart = request.getPart("windCSV");
        InputStream csv_fileContent = csv_filePart.getInputStream();

        // Execute the Java code with the provided inputs
        WindElectricityCalculatorAndGraph calculator = new WindElectricityCalculatorAndGraph(windTotal, airDensity, bladeLength, csv_fileContent);
        
        // Prepare response data
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("monthsList", calculator.monthsList);
        responseData.put("electricityGeneratedList", calculator.electricityGeneratedList);

        // Send response as JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(responseData));
    }
    
    public static void main(String[] args) {
    	
    }
}