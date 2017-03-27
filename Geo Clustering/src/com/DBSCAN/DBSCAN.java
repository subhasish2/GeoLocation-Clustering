package com.DBSCAN;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.io.CsvParser;

public class DBSCAN {
	LinkedList<Location> SetofPoints;
	LinkedList<Cluster> clusters;
	int Eps, MinPts;
	Map<Location, PointStatus> visited;

	public DBSCAN(LinkedList<Location> points, int eps, int minpts) {
		Eps = eps;
		MinPts = minpts;
		SetofPoints = new LinkedList<>(points);
		clusters = new LinkedList<>();
		visited = new HashMap<Location, PointStatus>();
	}
	public DBSCAN(String filename, int eps, int minpts) {
		CsvParser parser = new CsvParser(filename);
		Eps = eps;
		MinPts = minpts;
		SetofPoints = new LinkedList<>(parser.readData());
		clusters = new LinkedList<>();
		visited = new HashMap<Location, PointStatus>();
	}

	public void DBSCAN_Clustering() {
		for (Location loc : SetofPoints) {
			if (visited.get(loc) != null)
				continue;
			LinkedList<Location> neighbours = new LinkedList<>(getNeighbours(loc));
			if (neighbours.size() >= MinPts) {
				Cluster newCluster = new Cluster(clusters.size());
				clusters.add(expandCluster(loc, newCluster, neighbours));
			} else {
				visited.put(loc, PointStatus.NOISE);
			}
		}
		System.out.println(clusters.size());
		for(Cluster c:clusters) {
			c.showCluster();
		}
	}

	private Cluster expandCluster(Location loc, Cluster newCluster, LinkedList<Location> neighbours) {
		newCluster.addLocation(loc);
		visited.put(loc, PointStatus.PART_OF_CLUSTER);
		LinkedList<Location> seeds = new LinkedList<Location>(neighbours);
		int index = 0;
		while (index < seeds.size()) {
			Location current = seeds.get(index);
			PointStatus pStatus = visited.get(current);
			if (pStatus == null) {
				LinkedList<Location> currentNeighbours = getNeighbours(current);
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

	private LinkedList<Location> getNeighbours(Location l) {
		LinkedList<Location> neighbours = new LinkedList<>();
		for (Location neighbour : SetofPoints) {
			if (l != neighbour && neighbour.distanceTo(l) <= Eps) {
				neighbours.add(neighbour);
			}
		}
		return neighbours;
	}

	private LinkedList<Location> merge(LinkedList<Location> one, LinkedList<Location> two) {
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
