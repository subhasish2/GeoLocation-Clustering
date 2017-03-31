package com.io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.DBSCAN.Cluster;
import com.DBSCAN.Location;

public class Writer {
	FileWriter w;

	public Writer(String filename) {
		try {
			w = new FileWriter(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void write(ArrayList<Cluster> clusters) {
		try {
			w.write("No. of Cluster: " + clusters.size() + "\n");
			for (Cluster c : clusters) {
				w.write("Cluster id:" + c.getId() + "\n");
				for (Location l : c.getPoints()) {
					w.write(l.toString() + "\n");
				}
			}
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Writer wr = new Writer("Output/file_" + new Date().getTime() + ".txt");
		// wr.write();

	}

}
