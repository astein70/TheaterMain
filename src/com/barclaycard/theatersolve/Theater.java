package com.barclaycard.theatersolve;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Holds the dimensions of a theater (by sections) and maintains available seat count
 */
public class Theater {
	
	private ArrayList<Section> sections = null;
	private int theaterSeatsLeft = 0;

	/**
	 * Build a theater object, which will contain a list of sections and total available capacity
	 */
	Theater() {
		sections = new ArrayList<Section>();
		theaterSeatsLeft = 0;
	}
	
	/**
	 * Adds a section 
	 * @param row 1 relative
	 * @param section 1 relative
	 * @param size capacity of the section
	 */
	public void addSection(int row, int section, int size) {
		Section newSection = new Section(row,section,size);
		sections.add(newSection);
		theaterSeatsLeft += size;
	}

	/**
	 * Accessor for sections in the theater
	 * @return the array containing the sections
	 */
	public ArrayList<Section> getSections () {
		return sections;
	}

	/**
	 * Accessor for current seating capacity of the theater
	 * @return the current seating capacity
	 */
	public int getSeatsLeft() {
		return theaterSeatsLeft;
	}

	/**
	 * Called when an order is being filled or being moved, to reduce a section's available seating.
	 * @param section the section being modified
	 * @param reduction the modification amount
	 */
	public void decreaseSeatsRemaining(Section section, int reduction) {
		section.setCapacity(section.getCurrentCapacity()-reduction);
		theaterSeatsLeft-=reduction;
	}

	/**
	 * Called when a prior-filled order is being moved closer to the stage,
	 * to increase the older section's available seating.
	 * @param section the section being modified
	 * @param increase the modification amount
	 */
	public void increaseSeatsRemaining(Section section, int increase) {
		section.setCapacity(section.getCurrentCapacity()+increase);
		theaterSeatsLeft+=increase;
	}

	/**
	 * Sort the sections by capacity and row
	 * @return the sorted sections
	 * @throws Exception for out of memory
	 */
	public ArrayList<Section> sortSectionsByCapacity() throws Exception {
		try {
			Collections.sort(sections, new SectionSortCompCapacity());
		}
		catch (OutOfMemoryError e){
			throw new Exception("Out of memory in sortSectionsByCapacity");
		}
		return sections;
	}
	
	/**
	 * Sort the sections by row and column
	 * @return the sorted sections
	 * @throws Exception for out of memory
	 */
	public ArrayList<Section> sortSectionsByPosition() throws Exception {
		try {
			Collections.sort(sections, new SectionSortCompLocation());
		}
		catch (OutOfMemoryError e){
			throw new Exception("Out of memory in sortSectionsByPosition");
		}
		return sections;
	}
}
