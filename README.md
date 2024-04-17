# Calculate Monthly Electricity Generated by Renewable Energy (Solar and Wind) 

## Lanugaes and Tools used:
- **IDE:**
    - Eclipse
- **Languages:**
    - Backend:
        - Java
    - Fronend::
        - HTML
        - CSS
        - Java Script
- **Framework:**
    - Java EE (Java Servlet)
- **Packages/Libraries:**
    - Backend:
        - Gson: To convert object to JSON
    - Fronend::
        - Bootstrap: To design the UI
        - AnyChart: To plot the graph on the web page
- **Server:**
    - Apache Tomcat


## CSV Files: 
Two CSV files are used here. One CSV files contains the solar radiation in each month. This CSV file has three columns. The first columns has name of the months, and the second column has the monthly avg. solar radiation (in kW/m<sup>2</sup>). <br>
Similarly, the second CSV files contains the average wind speed in each month. This CSV file has two columns. The first columns has name of the months and second column has the average wind speed of the corresponding months.


## Formulas:
**Solar Energy Calculation:** <br>
- Energy generated (in kW) by solar panels in a particular month is calculated as follows:

$$\text{Energy generated (in kW)} = \text{Area of each solar panel (in $m^2$)} $\times$ \text{No.of solar panels} $\times$ \text{Efficiency of the solar panel (in $\%$)} \times \text{Monthly avg.solar radiation (in kW/$m^2$)} \times \text{Performance ratio}$$
