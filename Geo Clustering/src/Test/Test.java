package Test;

import java.util.LinkedList;

import com.DBSCAN.DBSCAN;
import com.DBSCAN.Location;

public class Test {

	public static void main(String[] args) {
		LinkedList<Location> l = new LinkedList<>();
		//Location loc1 = new Location("JU", 22.4967, 88.3711, " ");
		//l.add(loc1);
		//Location loc2 = new Location("BHU", 25.2677, 82.9913, " ");
		//Location loc3 = new Location("JNU", 28.5424, 77.1671, " ");
		//Location loc4 = new Location("CU", 22.5748, 88.3626, " ");
		//l.add(loc2);
		//l.add(loc3);
		//l.add(loc4);
		//DBSCAN d = new DBSCAN(l, 20, 1);
		//d.DBSCAN_Clustering();
		//d.showStatus();
		//System.out.println(loc1.distanceTo(loc4));
		String s="Cluster_id:0 Elements:36";
		String[] headpart = s.split(" ");
		String[] cl_info = headpart[1].split(":");
		int cl_size = Integer.parseInt(cl_info[1]);
		System.out.println(cl_size);
		String s1="{lat: 22.5609959, lng: 88.4145324},";
		System.out.println(s1.substring(0,s1.length()-1));
	}

}
