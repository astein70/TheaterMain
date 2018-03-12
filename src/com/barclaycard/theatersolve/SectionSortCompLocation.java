package com.barclaycard.theatersolve;

import java.util.Comparator;

/**
 * Sort for displaying the layout, sort by row and column, ascending order
 * @author Albert Stein
 * @version 1.0
 */
class SectionSortCompLocation implements Comparator<Section> {
	public int compare(Section b1, Section b2) {
		if (b1.getRowPos() > b2.getRowPos()) {
			return 1;
		} else if (b1.getRowPos() < b2.getRowPos()) {
			return -1;
		} else if (b1.getColPos() > b2.getColPos()) {
			return 1;
		} else if (b1.getColPos() < b2.getColPos()) {
			return -1;
		}
		return 0; // we do not care about the capacity within the bucket in this sort
	}
};
