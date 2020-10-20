package com.abhinav.maven.csvtodb;

import java.io.IOException;
import java.io.Reader;
import java.lang.Iterable;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws URISyntaxException
    {
    	try {
    	Reader reader = Files.newBufferedReader(Paths.get(App.class.getResource("/ms3interview.csv").toURI()));

        // read csv file
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withNullString("null").parse(reader);
        ArrayList<Record> data = new ArrayList<Record>();
        for (CSVRecord record : records) {
//        	Record rec = new Record();
//        	if (record.isConsistent()) {
//	        	rec.setA(record.get(0));
//	        	rec.setB(record.get(1));
//	        	rec.setC(record.get(2));
//	        	rec.setD(record.get(3));
//	        	rec.setE(record.get(4));
//	        	rec.setF(record.get(5));
//	        	rec.setG(record.get(6));
//	        	rec.setH(record.get(7));
//	        	rec.setI(record.get(8));
//	        	rec.setJ(record.get(9));
//	        	data.add(rec);
//        	} else {
        		System.out.println(record.toString());
        }
        // close the reader
        reader.close();


    } catch (IOException ex) {
        ex.printStackTrace();
    }
}
}
