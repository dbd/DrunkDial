package com.example.derek.drunkdial;

import java.sql.*;

/**
 * Created by derek on 2/13/16.
 */
public class DrunkDatabase {


    private static final String url = "jdbc:mysql://drunkdialwwh.cui02yblkhqg.us-west-2.rds.amazonaws.com:3306/DrunkDialDB";
    private static final String user = "drunk";
    private static final String pass = "drunkdial4";

    Connection conn = null;

    public DrunkDatabase() {


        try {

            //drunkdialwwh.cui02yblkhqg.us-west-2.rds.amazonaws.com:3306
            Connection con = DriverManager.getConnection(url, user, pass);
            /* System.out.println("Databaseection success"); */

            String result = "Database connection success\n";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from phones where active = 1");
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println(rsmd.toString());

            //conn = DriverManager.getConnection("blah");

            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

}
 
