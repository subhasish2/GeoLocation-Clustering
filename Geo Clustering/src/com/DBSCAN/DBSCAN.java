package com.DBSCAN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.io.CsvParser;

public class DBSCAN {
	ArrayList<Location> SetofPoints;
	ArrayList<Cluster> clusters;
	int Eps, MinPts;
	Map<Location, PointStatus> visited;

	public DBSCAN(ArrayList<Location> points, int eps, int minpts) {
		Eps = eps;
		MinPts = minpts;
		SetofPoints = new ArrayList<>(points);
		clusters = new ArrayList<>();
		visited = new HashMap<Location, PointStatus>();
	}
	public DBSCAN(String filename, int eps, int minpts) {
		CsvParser parser = new CsvParser(filename);
		Eps = eps;
		MinPts = minpts;
		SetofPoints = new ArrayList<>(parser.readData());
		clusters = new ArrayList<>();
		visited = new HashMap<Location, PointStatus>();
	}

	public void DBSCAN_Clustering() {
		for (Location loc : SetofPoints) {
			if (visited.get(loc) != null)
				continue;
			ArrayList<Location> neighbours = new ArrayList<>(getNeighbours(loc));
			if (neighbours.size() >= MinPts) {
				Cluster newCluster = new Cluster(clusters.size());
				clusters.add(expandCluster(loc, newCluster, neighbours));
			} else {
				visited.put(loc, PointStatus.NOISE);
			}
		}
		System.out.println("No. of Cluster: "+clusters.size());
		for(Cluster c:clusters) {
			c.showCluster();
		}
	}

	private Cluster expandCluster(Location loc, Cluster newCluster, ArrayList<Location> neighbours) {
		newCluster.addLocation(loc);
		visited.put(loc, PointStatus.PART_OF_CLUSTER);
		ArrayList<Location> seeds = new ArrayList<Location>(neighbours);
		int index = 0;
		while (index < seeds.size()) {
			Location current = seeds.get(index);
			PointStatus pStatus = visited.get(current);
			if (pStatus == null) {
				ArrayList<Location> currentNeighbours = getNeighbours(current);
				if (currentNeighbours.size() >= MinPts) {
					seeds = merge(seeds, currentNeighbours);
				}
			}
			if (pStatus != PointStatus.PART_OF_CLUSTER) {
				visited.put(current, PointStatus.PART_OF_CLUSTER);
				newCluster.addLocation(current);
			}

			index++;
		}
		
		return newCluster;

	}

	private ArrayList<Location> getNeighbours(Location l) {
		ArrayList<Location> neighbours = new ArrayList<>();
		for (Location neighbour : SetofPoints) {
			if (l != neighbour && neighbour.distanceTo(l) <= Eps) {
				neighbours.add(neighbour);
			}
		}
		return neighbours;
	}

	private ArrayList<Location> merge(ArrayList<Location> one, ArrayList<Location> two) {
		final Set<Location> oneSet = new HashSet<Location>(one);
		for (Location item : two) {
			if (!oneSet.contains(item)) {
				one.add(item);
			}
		}
		return one;
	}
	public void showStatus(){
		for(Map.Entry<Location, PointStatus> i:visited.entrySet()) {
			System.out.println(i.getKey()+" "+i.getValue());
		}
	}
	public void showClusters(){
		for(Cluster c:clusters) {
			c.showCluster();
		}
	}

}
