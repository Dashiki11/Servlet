
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet("/viewemployee")

public class viewemployee extends HttpServlet{
    
     Connection con;
     PreparedStatement pst;
     ResultSet rs;
     int row;
    
     @Override
     public void doGet(HttpServletRequest req, HttpServletResponse rsp ) throws IOException, ServletException
     {
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();
       
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servletemployee","prog","hello");
             
             String sql;
             
             sql = "SELECT * FROM employee";
             Statement stmt = con.createStatement();
             rs = stmt.executeQuery(sql);
             
             out.println("<table cellspacing = '0' width = '350px' border = '1'>");
             out.println("<tr>");
             out.println("<td> Employee ID </td>");
             out.println("<td> First Name </td>");
             out.println("<td> Last Name </td>");
             out.println("<td> Edit </td>");
             out.println("<td> Delete </td>");
             out.println("</tr>"); 
             
             while(rs.next())
             {
                 out.println("<tr>");
                 out.println("<td>" + rs.getInt("id") + "</td>");
                 out.println("<td>" + rs.getString("fname") + "</td>");
                 out.println("<td>" + rs.getString("lname") + "</td>");
                 
                 out.println("<td> <a href = 'Editreturn?id=" + rs.getString("id") + "'> Edit </a> </td>");
                 out.println("<td> <a href = 'Delete?id=" + rs.getString("id") + "'> Delete </a> </td>");
                 
                 out.println("</tr>");
             }
                        
             out.println("</table>");
             
             
             
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(employee.class.getName()).log(Level.SEVERE, null, ex);
         }catch (SQLException ex) {
             out.println("<font color= 'red'> Record Failed</font>");
         }
          
    }
    
}
