package com.electricitycalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class WindElectricityCalculatorAndGraph extends JPanel {

    public List<Double> electricityGeneratedList = new ArrayList<>();
    public List<String> monthsList = new ArrayList<>();
    public double maxVal;
    
    int windTotal;
    double rho; // Density of the air in kg/m^3
    double R; // Length of the blades (in meters)
    InputStream csvFile; // Path to the CSV file containing solar hours data

    public WindElectricityCalculatorAndGraph(int total_turbines, double airDensity, double bladeLength, InputStream csv_File) {
    	this.windTotal = total_turbines;
    	this.rho = airDensity;
    	this.R = bladeLength;
    	this.csvFile = csv_File;
        readDataFromFile(csvFile);
    }

    private void readDataFromFile(InputStream csv_file) {
        String line;
        String cvsSplitBy = ","; // CSV delimiter

        try (BufferedReader br = new BufferedReader(new InputStreamReader(csv_file))) {
            // Skip the first line (header) as it contains column names
            br.readLine();

            // Read each line of the CSV file and extract the Wind Electricity data
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                String month = data[0]; // Assuming month is in column 1
                double speed = Double.parseDouble(data[1]); // Assuming wind speeds are in column 2
                double electricityGenerated = (0.5 * windTotal * rho * 3.14159 * Math.pow(R, 2) * Math.pow(speed, 3)) / 1000; 

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