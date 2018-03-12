package com.barclaycard.theatersolve;

/**
 * Extend XYBucket and provide sorting (comparator) classes against this new class
 * @author Albert Stein
 * @version 1.0
 */

public class Section extends XYBucket {
	Section(int rowPos, int colPos, int size) {
		super(rowPos, colPos, size);
	}
}
