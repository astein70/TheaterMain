package com.barclaycard.theatersolve;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main entry point to console program for the 'theater' solution to "Design Problem - Set 2.txt". 
 * @author Albert Stein
 * @version 1.0
 */
public class TheaterMain {

	static Theater theater = new Theater();
	static Orders orders = new Orders();

	/**
	 * Program accepts console input of a row/column representation of a theater,
	 * followed by a blank line to represent the end of that input,
	 * followed by lines of patron orders and their seat count requests.
	 * Orders are processed to fill for the maximum completion of orders to the 
	 * extent of keeping the patrons within the same section, followed
	 * by placing patrons as close to the front of the stage.
	 * Final results are output to the console, and can include unfillable orders,
	 * and orders that can be filled if the order is split to accommodate the
	 * requirement of an order fitting within one section.   
	 * @param args not used here
	 */
	public static void main(String[] args) {
		InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader br= new BufferedReader(isr);
		try {
			BuildTheaterLayout(br);
			ReceiveTicketRequests(br);
			ProcessOrders();
			OutputOrders();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			System.exit(1);
		}
		finally {
			try {
				br.close();
			}
			catch (Exception e) {
				System.out.println("Failed to close BufferedReader on programm exit: " + e.getMessage());
				System.exit(1);
			}
		}
		System.exit(0);
	}

	/**
	 * Build a layout of the theater, given a set of inputs. The layout
	 * will be used later for processing of a batch of orders.
	 * @exception Exception
	 */
	private static void BuildTheaterLayout(BufferedReader br) throws Exception {
		String inpLine = "";
		boolean layoutComplete = false;
		Pattern numericOnly = Pattern.compile("\\d+");
		int row = 1; // 'Row' stored is 1 relative

		try {
			while (layoutComplete == false) { // only allow positive numeric values
				inpLine = br.readLine(); // grab a row of sections at a time
				if (inpLine.length() > 0) {
					StringTokenizer t = new StringTokenizer(inpLine);
					String word = "";
					int sectionCol = 1;
					Integer capacity = 0;
					while (t.hasMoreTokens()) {
						word = t.nextToken();
						Matcher m = numericOnly.matcher(word);
						if (m.matches()) {
							capacity = Integer.parseInt(word);
							if (capacity < 0) {
								throw new NumberFormatException("Numeric input failure in BuildTheaterLayout on row " + row + ", negative value: " + capacity);						
							}
							theater.addSection(row, sectionCol++, capacity);
						} else {
							throw new NumberFormatException("Numeric input failure in BuildTheaterLayout on row " + row + ": " + word);
						}
					}
					row++;
				} else {
					// layout precedes orders,
					// must stop layout creation on an empty input line
					layoutComplete = true;
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Store the orders to be processed per specification. The orders will
	 * be processed later against the theater layout.
	 * @exception Exception
	 */
	private static void ReceiveTicketRequests(BufferedReader br) throws Exception {
		String inpLine="";
		String name="";
		String tempSeatsNeeded = "";
		int seatsRequired=0;
		try {
			while ((inpLine = br.readLine()) != null) {
				if (inpLine.length() == 0) {
					break;
				}
				StringTokenizer t = new StringTokenizer(inpLine);
				name = t.nextToken();
				tempSeatsNeeded = t.nextToken();
				try {
					seatsRequired = Integer.parseInt(tempSeatsNeeded);
				} catch (Exception e) {
					throw new NumberFormatException(
							"Numeric input failure in ProcessTicketRequests for patron " + name + ": " + tempSeatsNeeded);
				}
				if (seatsRequired <= 0) {
					throw new NumberFormatException("Numeric input failure in ProcessTicketRequest for patron " + name + ", non-positive value: " + seatsRequired);
				}
				orders.addOrder(new Order(name, seatsRequired));
			}
		}
		catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Process the orders given prior theater dimensions and ticketing requests.
	 * This will fill as many patron requests as possible, with the requirement of
	 * keeping the group of tickets from an order within one section, while also
	 * providing patrons the closest seating to the stage.
	 * @throws Exception 
	 */
	static void ProcessOrders() throws Exception {
		ProcessOrdersForBestSales();
		ProcessOrdersForBestPlacement();
	}

	/**
	 * First pass of order processing. Sort the theater arrangement by section
	 * capacity ascending and fill the most orders that can be filled.
	 * @throws Exception 
	 */
	static void ProcessOrdersForBestSales() throws Exception {
		boolean orderFilled = false;

		ArrayList<Section> sectionsByCapacity = theater.sortSectionsByCapacity();

		// first fill for capacity and maximum orders
		for (Order o : orders.getOrders()) {
			if (o.getSeatsRequested() > theater.getSeatsLeft()) {
				o.setOrderStatus(ORDER_FULLFILLMENT.INSUFFICIENT_SEATS);
				continue;
			}
			orderFilled = false;
			for (Section section : sectionsByCapacity) {
				// complete orders by matching for first section that supports the order
				if (o.getSeatsRequested() <= section.getCurrentCapacity()) {
					theater.decreaseSeatsRemaining(section, o.getSeatsRequested());
					o.setSection(section); // also sets ORDER_FILLED
					orderFilled = true;
					break;
				}
			}
			if (!orderFilled) {
				o.setOrderStatus(ORDER_FULLFILLMENT.NEEDS_SPLIT);
			}
		}
	}

	/**
	 * Second and final pass of order processing. Sort the theater arrangement by
	 * section position and move filled orders closest to the front as possible.
	 * @throws Exception 
	 */
	static void ProcessOrdersForBestPlacement() throws Exception {
		ArrayList<Section> locationSectionSet = theater.sortSectionsByPosition();
		for (Order o : orders.getOrders()) {
			if (o.getOrderStatus() != ORDER_FULLFILLMENT.FILLED) { // could not fill earlier, so can't move it now
				continue;
			}
			for (Section section : locationSectionSet) {
				if (section.getRowPos() >= o.getSection().getRowPos()) { // nothing closer for this order
					break;
				}
				if (section.getCurrentCapacity() >= o.getSeatsRequested()) { // can fit here
					// adjust the sections
					theater.increaseSeatsRemaining(o.getSection(), o.getSeatsRequested());
					theater.decreaseSeatsRemaining(section, o.getSeatsRequested());
					// adjust the order to the closer section
					o.setSection(section);
					break;
				}
			}
		}
	}

	/**
	 * Output the order processing results
	 */
	static void OutputOrders () {
		orders.outputOrderResults(); // final results output to console
	}
}
