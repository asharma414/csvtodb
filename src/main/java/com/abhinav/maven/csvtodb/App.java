package com.abhinav.maven.csvtodb;

import java.io.IOException;
import java.io.Reader;
import java.lang.Iterable;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws URISyntaxException, SQLException
    {
    	try {
    	Reader reader = Files.newBufferedReader(Paths.get(App.class.getResource("/ms3interview.csv").toURI()));

        // read csv file
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
        ArrayList<Record> data = new ArrayList<Record>();
        for (CSVRecord record : records) {
        	Record rec = new Record();
        	rec.setA(record.get(0));
        	rec.setB(record.get(1));
        	rec.setC(record.get(2));
        	rec.setD(record.get(3));
        	rec.setE(record.get(4));
        	rec.setF(record.get(5));
        	rec.setG(record.get(6));
        	rec.setH(record.get(7));
        	rec.setI(record.get(8));
        	rec.setJ(record.get(9));
        	data.add(rec);
        }
        // close the reader
        reader.close();
        PreparedStatement statement = null;
        Connection con = connect();
        String sql = "INSERT INTO records(A, B, C, D, E, F, G, H, I, J) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        statement = con.prepareStatement(sql);
        for (Record rec : data) {
        	if (rec.isValid()) {
        		statement.setString(1, rec.getA());
        		statement.setString(2, rec.getB());
        		statement.setString(3, rec.getC());
        		statement.setString(4, rec.getD());
        		statement.setString(5, rec.getE());
        		statement.setString(6, rec.getF());
        		statement.setString(7, rec.getG());
        		statement.setInt(8, rec.getH());
        		statement.setInt(9, rec.getI());
        		statement.setString(10, rec.getJ());
        		statement.addBatch();
        	}
        }
        statement.executeBatch();
        con.commit();
        con.close();


	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
    }
    
    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:csvdata.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return conn;
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
    }
}
