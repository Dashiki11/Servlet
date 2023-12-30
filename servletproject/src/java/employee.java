
import java.sql.Connection;
import java.sql.*;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet("/employee")

public class employee extends HttpServlet {
    
    Connection con;
    PreparedStatement pst;
    int row;
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse rsp ) throws IOException, ServletException
    {
       rsp.setContentType("text/html");
       PrintWriter out = rsp.getWriter();
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servletemployee","prog","hello");
            String empid = req.getParameter("empid");
            String empfname = req.getParameter("fname");
            String emplname = req.getParameter("lname");
            
            pst = con.prepareStatement("INSERT into employee(id, fname, lname) values(?,?,?) ");
            pst.setString(1, empid);
            pst.setString(2, empfname);
            pst.setString(3, emplname);
            row = pst.executeUpdate();
            
            out.println("<font color= 'green'> Record Added</font>");
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(employee.class.getName()).log(Level.SEVERE, null, ex);
        }catch (SQLException ex) {
            out.println("<font color= 'red'> Record Failed</font>");
        }
        
    }

}
