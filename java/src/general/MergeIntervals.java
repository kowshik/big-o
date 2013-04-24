package general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import common.Pair;

/**
 * You are given a series of non-intersecting intervals sorted in non-decreasing
 * order of their starting points. Write a function which when given another
 * interval, merges it into these existing intervals.
 *
 * Examples:
 *
 * [1] merge([12,13], { [1,2], [4,10], [17,22] }) => {[1,2], [4,10], [12,13],
 * [17,22]}
 *
 * [2] merge([2,5], {[1,2], [4,10]}) => {[1,10]}
 */

class Interval extends Pair<Integer, Integer> {

	public Interval(int start, int end) {
		super(start, end);
	}

	public Interval() {
	}
}

public class MergeIntervals {

	public static List<Interval> mergeIntervals(List<Interval> intervals,
			Interval newInterval) {
		if (intervals == null) {
			throw new NullPointerException(
					"The list of intervals passed is null.");
		}

		if (newInterval == null) {
			throw new NullPointerException("The new interval passed is null.");
		}

		return doMergeIntervals(intervals, newInterval);
	}

	private static List<Interval> doMergeIntervals(List<Interval> intervals,
			Interval newInterval) {
		List<Interval> merged = new ArrayList<Interval>();
		Iterator<Interval> iter = intervals.iterator();

		boolean isMerged = false;
		while (iter.hasNext()) {
			Interval current = iter.next();
			if (isIntersecting(current, newInterval)) {
				Interval last = current;
				Interval interval = null;
				while (iter.hasNext()) {
					interval = iter.next();
					if (isIntersecting(interval, newInterval)) {
						last = interval;
					} else {
						break;
					}
				}

				Interval combinedInterval = new Interval(Math.min(
						current.getFirst(), newInterval.getFirst()), Math.max(
						last.getSecond(), newInterval.getSecond()));
				merged.add(combinedInterval);
				if (interval != null && interval != last) {
					merged.add(interval);
				}

				isMerged = true;
				break;
			} else if (isAfter(current, newInterval)) {
				merged.add(current);
			} else {
				merged.add(newInterval);
				merged.add(current);
				isMerged = true;
				break;
			}
		}

		if (!isMerged) {
			merged.add(newInterval);
		}

		while (iter.hasNext()) {
			merged.add(iter.next());
		}

		return merged;
	}

	// Does interval b occur after interval a?
	private static boolean isAfter(Interval a, Interval b) {
		if (isIntersecting(a, b)) {
			return false;
		}

		if (b.getFirst() > a.getSecond()) {
			return true;
		}

		return false;
	}

	// Does interval a intersect with interval b?
	private static boolean isIntersecting(Interval a, Interval b) {
		if (a.getSecond() < b.getFirst()) {
			return false;
		}

		if (b.getSecond() < a.getFirst()) {
			return false;
		}

		return true;
	}
}
