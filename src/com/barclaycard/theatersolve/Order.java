package com.barclaycard.theatersolve;

/**
 * Class to store a single theater order
 * @author Albert Stein
 * @version 1.0
 */
public class Order {
	private String patronsName;
	private int seatsRequested;
	private Section orderSection;
	private ORDER_FULLFILLMENT orderStatus;

	@SuppressWarnings("unused")
	private Order() {
		throw new AssertionError();
	}

	/**
	 * Order constructor
	 * @param patronsName the patron's name
	 * @param seatsRequested seats requested
	 */
	Order(String patronsName, int seatsRequested) {
		this.patronsName = patronsName;
		this.seatsRequested = seatsRequested;
		orderSection = null;
		orderStatus = ORDER_FULLFILLMENT.UNPROCESSED;
	}

	/**
	 * Accessor for Order's patronsName
	 * @return the patron's name associated with the order
	 */
	public String getPatronsName() {
		return patronsName;
	}

	/**
	 * Accessor for Order's seat request
	 * @return the count of seats requested by the patron
	 */
	public int getSeatsRequested() {
		return seatsRequested;
	}

	/**
	 * Setter for Order's section, also sets order as filled
	 * @param orderSection a representation of the section the order is being placed into 
	 */
	public void setSection(Section orderSection) {
		this.orderSection = orderSection;
		setOrderStatus(ORDER_FULLFILLMENT.FILLED);
	}

	/**
	 * Accessor for Order's section
	 * @return the section that this order is set to currently
	 */
	public Section getSection() {
		return orderSection;
	}

	/**
	 * Setter for Order's status
	 * @param orderStatus the current status for the order, a value from the enum of ORDER_FULLFILLMENT
	 */
	public void setOrderStatus(ORDER_FULLFILLMENT orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * Accessor for Order's status
	 * @return the current status of the order, a value from the enum of ORDER_FULLFILLMENT
	 */
	public ORDER_FULLFILLMENT getOrderStatus() {
		return orderStatus;
	}
}

