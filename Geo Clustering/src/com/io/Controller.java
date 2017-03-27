package com.io;

import java.util.LinkedList;

import com.DBSCAN.DBSCAN;
import com.DBSCAN.Location;

public class Controller {
	Location loc;
	LinkedList<Location> datapoints;
	CsvParser csvParser;

	public Controller(String filename) {
		csvParser = new CsvParser(filename);
		loc = csvParser.readFirstRecord();
	}

	public void start() {
		// System.out.println(loc);
		datapoints = csvParser.readData(loc.getTimestamp());
		// for(Location l:datapoints)
		// System.out.println(l);
		DBSCAN d = new DBSCAN(datapoints, 20, 2);
		d.DBSCAN_Clustering();
		
		System.out.println(csvParser.readNextRecord());
	}

}