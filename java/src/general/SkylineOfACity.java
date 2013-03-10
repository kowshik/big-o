package general;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import collections.PeekIterator;

/**
 * You are given the buildings present in the 2-D view of a city i.e. X-Y plane.
 * Each building is represented in the following format: (lx, rx, h) where:
 * 
 * lx => left x-coordinate of the building
 * rx => right x-coordinate of the building
 * h => height of the building
 * 
 * You are supposed to find the skyline of the city, represented as a sequence
 * of (x,y) points which when connected will yield the city skyline.
 * 
 * See this picture for an example: http://i.imgur.com/cTXlm.png
 */

class Building {
	public int lx = 0;
	public int rx = 0;
	public int height = 0;

	public Building(int lx, int rx, int height) {
		this.lx = lx;
		this.rx = rx;
		this.height = height;
	}
}

public class SkylineOfACity {
	public static ArrayList<Point2D.Double> findSkyline(
			ArrayList<Building> buildings) {
		return findSkyline(buildings, 0, buildings.size() - 1);
	}

	private static ArrayList<Point2D.Double> generateSkyline(Building building) {
		ArrayList<Point2D.Double> skyline = new ArrayList<Point2D.Double>();

		skyline.add(new Point2D.Double(building.lx, 0));
		skyline.add(new Point2D.Double(building.lx, building.height));
		skyline.add(new Point2D.Double(building.rx, building.height));
		skyline.add(new Point2D.Double(building.rx, 0));

		return skyline;
	}

	private static ArrayList<Point2D.Double> findSkyline(
			ArrayList<Building> buildings, int startIndex, int endIndex) {
		if (startIndex == endIndex) {
			return generateSkyline(buildings.get(startIndex));
		}

		int middle = (startIndex + endIndex) / 2;
		ArrayList<Point2D.Double> leftSkyline = findSkyline(buildings,
				startIndex, middle);
		ArrayList<Point2D.Double> rightSkyline = findSkyline(buildings,
				middle + 1, endIndex);

		return merge(leftSkyline, rightSkyline);
	}

	private static ArrayList<Point2D.Double> merge(
			ArrayList<Point2D.Double> leftSkyline,
			ArrayList<Point2D.Double.Double> rightSkyline) {
		ArrayList<Point2D.Double> merged = new ArrayList<Point2D.Double>();
		double currentHeightLeft = 0f;
		double currentHeightRight = 0f;

		PeekIterator<Point2D.Double> iterLeft = new PeekIterator<Point2D.Double>(
				leftSkyline.iterator());
		PeekIterator<Point2D.Double> iterRight = new PeekIterator<Point2D.Double>(
				rightSkyline.iterator());
		while (iterLeft.hasNext() && iterRight.hasNext()) {
			if (iterLeft.peek().x < iterRight.peek().x) {
				currentHeightLeft = iterLeft.peek().y;
				merged.add(new Point2D.Double(iterLeft.peek().x, Math.max(
						currentHeightLeft, currentHeightRight)));
				iterLeft.next();
			} else {
				currentHeightRight = iterRight.peek().y;
				merged.add(new Point2D.Double(iterRight.peek().x, Math.max(
						currentHeightLeft, currentHeightRight)));
				iterRight.next();
			}
		}

		while (iterLeft.hasNext()) {
			merged.add(new Point2D.Double(iterLeft.peek().x, iterLeft.peek().y));
			iterLeft.next();
		}

		while (iterRight.hasNext()) {
			merged.add(new Point2D.Double(iterRight.peek().x,
					iterRight.peek().y));
			iterRight.next();
		}

		return merged;
	}

	public static void main(String[] args) {
		ArrayList<Building> buildings = new ArrayList<Building>();
		buildings.add(new Building(1, 5, 10));
		buildings.add(new Building(3, 7, 5));
		buildings.add(new Building(4, 10, 11));

		System.out.println(findSkyline(buildings));
	}
}
