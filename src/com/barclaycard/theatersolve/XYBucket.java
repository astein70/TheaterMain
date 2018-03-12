package com.barclaycard.theatersolve;

/**
 * Class XYBucket provides a generalized mechanism to handle an XY item of N capacity
 * @author Albert Stein
 * @version 1.0
 */
abstract class XYBucket {
	private int rowPos;
	private int colPos;
	private int currentCapacity;

	/**
	 * Constructor for XYBucket
	 * @param rowPos 1 relative
	 * @param colPos 1 relative
	 * @param size initial capacity of bucket
	 */
	XYBucket(int rowPos, int colPos, int size) {
		this.rowPos = rowPos;
		this.colPos = colPos;
		this.currentCapacity = size;
	}

	/**
	 * Accessor for this XYBucket's row
	 * @return the row position of this bucket, 1 relative
	 */
	public int getRowPos() {
		return rowPos;
	}

	/**
	 * Accessor for this XYBucket's column
	 * @return the column position of this bucket, 1 relative
	 */
	public int getColPos() {
		return colPos;
	}

	/**
	 * Accessor for this XYBucket's current capacity
	 * @return the current capacity for this bucket
	 */
	public int getCurrentCapacity() {
		return currentCapacity;
	}

	/**
	 * Setter for this XYBucket's current capacity
	 * @param newCapacity the new capacity of the bucket
	 */
	public void setCapacity(int newCapacity) {
		this.currentCapacity = newCapacity;
	}
}
