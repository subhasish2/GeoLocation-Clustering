package Test;

import com.DBSCAN.DBSCAN;
import com.io.Controller;
import com.io.CsvParser;

public class TestIo {

	public static void main(String[] args) {
		String filename = "csv/bike.csv";
		//CsvParser parser = new CsvParser(filename);
		//parser.readData();
		//DBSCAN d = new DBSCAN(filename, 20, 1);
		//d.DBSCAN_Clustering();
		//d.showStatus();
		//d.showClusters();
		Controller controller=new Controller(filename);
		controller.start();
	}

}
