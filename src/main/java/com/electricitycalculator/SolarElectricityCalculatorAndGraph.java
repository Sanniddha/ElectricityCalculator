package com.electricitycalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class SolarElectricityCalculatorAndGraph extends JPanel {

    public List<Double> electricityGeneratedList = new ArrayList<>();
    public List<String> monthsList = new ArrayList<>();
    public double maxVal;
    
    int solarTotal;
    double solarArea;
    double solarEfficiency;
    double solarPerformance;
    InputStream csvFile; // Path to the CSV file containing solar hours data

    public SolarElectricityCalculatorAndGraph(int total_panel, double solar_Area, double solar_Efficiency, double solar_Performance, InputStream csv_File) {
    	this.solarTotal = total_panel;
    	this.solarArea = solar_Area;
        this.solarEfficiency = solar_Efficiency;
        this.solarPerformance = solar_Performance;
        this.csvFile = csv_File;
        readDataFromFile(csvFile);
    }

    private void readDataFromFile(InputStream csv_file) {
        String line; // String variable to store each line read from the CSV file
        String cvsSplitBy = ","; // CSV delimiter, assuming the CSV file is comma-separated

        try (BufferedReader br = new BufferedReader(new InputStreamReader(csv_file))) {
            // Skip the first line (header) as it contains column names
            br.readLine();

            // Read each line of the CSV file and extract the Solar Electricity data
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                String month = data[0]; // Assuming month is in column 1
                double solarRadiations = Double.parseDouble(data[1]); // Assuming solar radiations are in column 2
                double electricityGenerated = solarTotal * solarArea * (solarEfficiency / 100) * solarPerformance * solarRadiations; 

                // Add data to the lists
                monthsList.add(month);
                electricityGeneratedList.add(electricityGenerated);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Find max value for scaling
        maxVal = electricityGeneratedList.stream().mapToDouble(Double::doubleValue).max().orElse(0);
    }
    
    public static void main(String[] args) {
    	
    }
}