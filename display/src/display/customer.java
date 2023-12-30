/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package display;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author Digikey
 */
class customer extends JFrame implements ActionListener
{
    private static final String DBURL = "jdbc:mysql://localhost:3306/shop";
    private static final String USER = "prog";
    private static final String PASS = "hello";
    
    private JLabel hello,want;
    private JButton Display;
    private DefaultListModel<String> listModel;
    private JList<String> listCustomers;


    public customer()
    {
        setTitle("Shop Gui");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,250);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());


        hello = new JLabel("This panel Displays items in the Customer table");
        want = new JLabel("Do you want to see?");
        add(hello);
        add(want);

        Display = new JButton("Display");
        add(Display);

        Display.addActionListener(this);

        listModel = new DefaultListModel<>();
        listCustomers = new JList<>(listModel);
        add(new JScrollPane(listCustomers));


        setVisible(true);

    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == Display)
        {
           displaycustomer();
        }
    }

    private void displaycustomer()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection(DBURL, USER, PASS);
            String SelectQuery = "SELECT * FROM customers";
            PreparedStatement stmt = con.prepareStatement(SelectQuery);
            ResultSet rs = stmt.executeQuery();

            listModel.clear();

            // Retrieve and display records
            while (rs.next()) {
                int customerId = rs.getInt("CustomerID");
                String name = rs.getString("CustomerName");
                String address = rs.getString("Address");
                String city = rs.getString("City");
                listModel.addElement("Customer ID: " + customerId + ", Name: " + name + ", Address: " + address + ", City: "
                        + city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Connection not Established" );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

