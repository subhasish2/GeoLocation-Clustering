package Test;

import com.Clustering.PDBSCAN;
import com.DBSCAN.DBSCAN;
import com.io.Controller;
import com.io.CsvParser;

public class TestIo {

	public static void main(String[] args) {
		String filename = "csv/bike.csv";
		// CsvParser parser = new CsvParser(filename);
		// parser.readData();
		PDBSCAN d = new PDBSCAN(filename, 1000, 2);
		d.PDBSCAN_Clustering();
		// d.showStatus();
		// d.showClusters();
		// Controller controller=new Controller(filename);
		// controller.start();
	}

}
