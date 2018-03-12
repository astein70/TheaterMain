package com.barclaycard.theatersolve;

/**
 * Enumeration of an order's state.
 * @author Albert Stein
 * @version 1.0
 */
public enum ORDER_FULLFILLMENT {
	/**
	 * An order that has yet to be processed 
	 */
	UNPROCESSED,
	/**
	 * An order that has been filled per the spec
	 */
	FILLED, 
	/**
	 * An order that cannot be filled since the seats requested does not fit within a section
	 */
	NEEDS_SPLIT, 
	/**
	 * An order that cannot be filled since there is insufficient space in the theater (even if empty)
	 */
	INSUFFICIENT_SEATS
}
