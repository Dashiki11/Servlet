
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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet("/EditServlet")

public class EditServlet extends HttpServlet {
    
     Connection con;
     PreparedStatement pst;
     ResultSet rs;
     int row;
    
     @Override
     public void doPost(HttpServletRequest req, HttpServletResponse rsp ) throws IOException, ServletException
     {
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();
        
        String eid = req.getParameter("id");
       
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servletemployee","prog","hello");
             
             String empid = req.getParameter("empid");
             String fname = req.getParameter("fname");
             String lname = req.getParameter("lname");
             
             pst = con.prepareStatement("UPDATE employee SET fname = ?, lname = ? WHERE id = ?");
             pst.setString(1, fname);
             pst.setString(2, lname);
             pst.setString(3, empid);
             
              row = pst.executeUpdate();
             
                out.println("<font color= 'green'> Record Updated!! </font>");

             
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(employee.class.getName()).log(Level.SEVERE, null, ex);
         }catch (SQLException ex) {
             out.println("<font color= 'red'> Record Failed </font>");
         }
          
    }
    
}
