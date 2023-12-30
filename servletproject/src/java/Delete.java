
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


@WebServlet("/Delete")

public class Delete extends HttpServlet{
    
    Connection con;
     PreparedStatement pst;
     ResultSet rs;
     int row;
    
     @Override
     public void doGet(HttpServletRequest req, HttpServletResponse rsp ) throws IOException, ServletException
     {
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();
        
        String empid = req.getParameter("id");
       
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servletemployee","prog","hello");
             
             pst = con.prepareStatement("DELETE FROM employee WHERE id = ?");
             pst.setString(1, empid);
              row = pst.executeUpdate();
             
                out.println("<font color= 'green'> Record Deleted!! </font>");

             
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(employee.class.getName()).log(Level.SEVERE, null, ex);
         }catch (SQLException ex) {
             out.println("<font color= 'red'> Record Failed </font>");
         }
    
     }    
}
