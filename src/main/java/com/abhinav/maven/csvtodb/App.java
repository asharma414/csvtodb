package com.abhinav.maven.csvtodb;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.Iterable;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FilenameUtils;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws URISyntaxException, SQLException
    {
    	try {
    	Path inputPath = Paths.get(args[0]);
    	String file = inputPath.getFileName().toString();
    	String fileName = FilenameUtils.removeExtension(file);
    	Logger logger = Logger.getLogger("MyLog");
    	FileHandler fh;
    	fh = new FileHandler("./" + fileName + ".log");
        logger.addHandler(fh);
    	Reader reader = Files.newBufferedReader(inputPath);
    	CSVPrinter printer = new CSVPrinter(new FileWriter("./" + fileName + "-bad.csv"), CSVFormat.EXCEL);
    	printer.printRecord("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        // read csv file
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
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
        logger.info("Records received: " + Integer.toString(data.size()));
        // close the reader
        reader.close();
        int validCount = 0;
        int invalidCount = 0;
        PreparedStatement statement = null;
        Connection con = connect(fileName);
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
        		validCount++;
        	} else {
	       		 try  {
	       		     printer.printRecord(rec.getA(), rec.getB(), rec.getC(), rec.getD(), rec.getE(), rec.getF(), rec.getG(), rec.toString(rec.getH()), rec.toString(rec.getI()), rec.getJ());
	       		     invalidCount++;
	       		 } catch (IOException ex) {
	       		     ex.printStackTrace();
	       		 }
        	}
        }
        printer.close();
        statement.executeBatch();
        con.commit();
        logger.info("Records successful: " + Integer.toString(validCount));
        logger.info("Records failed: " + Integer.toString(invalidCount));
        con.close();

	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
    }
    
    public static Connection connect(String dbName) {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:" + "./" + dbName + ".db";
            String sql = "CREATE TABLE IF NOT EXISTS records (\n"
            		+ "	A	TEXT NOT NULL,\n"
            		+ "	B	TEXT NOT NULL,\n"
            		+ "	C	TEXT NOT NULL,\n"
            		+ "	D	TEXT NOT NULL,\n"
            		+ "	E	TEXT NOT NULL,\n"
            		+ "	F	TEXT NOT NULL,\n"
            		+ "	G	TEXT NOT NULL,\n"
            		+ "	H	INTEGER NOT NULL,\n"
            		+ "	I	INTEGER NOT NULL,\n"
            		+ "	J	TEXT NOT NULL\n"
            		+ ");";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
                // create a new table
            stmt.execute(sql);
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
