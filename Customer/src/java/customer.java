/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/customer")
public class customer extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Database connection parameters
        String jdbcUrl = "jdbc:mysql://localhost:3306/shop";
        String dbUsername = "prog";
        String dbPassword = "hello";

        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            
            // Execute a SQL query to retrieve data
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");

            // Process and display the data in an HTML table
            out.println("<html><body>");
            out.println("<table border='1'>");
            out.println("<tr><th>Customer ID</th><th>Customer Name</th><th>Address</th><th>City</th></tr>");

            while (resultSet.next()) {
                int ID = resultSet.getInt("CustomerID");
                String Name = resultSet.getString("CustomerName");
                String Address = resultSet.getString("Address");
                String City = resultSet.getString("City");
                out.println("<tr><td>" + ID + "</td><td>" + Name + "</td><td>" + Address + "</td><td>" + City + "</td></tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

            // Close database resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
