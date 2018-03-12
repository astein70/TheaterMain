package com.barclaycard.theatersolve;

import java.util.ArrayList;

/**
 * Class that maintains all theater orders (of class Order)
 * @author Albert Stein
 * @version 1.0
 */
public class Orders {

	private ArrayList<Order> orderArray = null;

	/**
	 * Orders constructor creates the order array and nothing else
	 */
	Orders () {
		orderArray = new ArrayList<Order>();
	}
	
	/**
	 * Add a new order to the Orders internal storage area
	 * @param newOrder is an Order object
	 */
	public void addOrder(Order newOrder) {
		orderArray.add(newOrder);
	}

	/**
	 * Accessor for the class' array
	 * @return Current array of orders
	 */
	public ArrayList<Order> getOrders() {
		return orderArray;
	}

	/**
	 * Outputs the result of all the orders, whether completed or not
	 */
	public void outputOrderResults() {
		for (Order o : orderArray) {
			showOrder(o);
		}
	}

	/**
	 * Outputs an individual order's information and state to the console
	 * @param o contains an Order object
	 */
	public void showOrder(Order o) {
		String s;
		if (o.getOrderStatus() == ORDER_FULLFILLMENT.FILLED) {
			s = o.getPatronsName() + " Row " + o.getSection().getRowPos() + " Section " + o.getSection().getColPos();
		} else {
			if (o.getOrderStatus() == ORDER_FULLFILLMENT.NEEDS_SPLIT) {
				s = o.getPatronsName() + " Call to split party.";
			} else {
				if (o.getOrderStatus() == ORDER_FULLFILLMENT.INSUFFICIENT_SEATS) {
					s = o.getPatronsName() + " Sorry, we can't handle your party.";
				} else {
					s = o.getPatronsName() + " ORDER UNPROCESSED !!!";
				}
			}
		}
		System.out.println(s);
	}
}
