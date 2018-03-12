package com.barclaycard.theatersolve;

import java.util.Comparator;

/**
 * Sort for filling orders, sort by size and row, ascending order
 * @author Albert Stein
 * @version 1.0
 */
class SectionSortCompCapacity implements Comparator<Section> {
	public int compare(Section b1, Section b2) {
		if (b1.getCurrentCapacity() > b2.getCurrentCapacity()) {
			return 1;
		} else if (b1.getCurrentCapacity() < b2.getCurrentCapacity()) {
			return -1;
		} else if (b1.getRowPos() > b2.getRowPos()) {
			return 1;
		} else if (b1.getRowPos() < b2.getRowPos()) {
			return -1;
		}
		return 0; // we do not care about the order of the column in this sort
	}
};

