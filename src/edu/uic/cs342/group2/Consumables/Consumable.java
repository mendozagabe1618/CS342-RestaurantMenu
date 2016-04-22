package edu.uic.cs342.group2.Consumables;

/**
 * An interface for anything that can be eaten or drank.
 * 
 * @author Group II
 *
 */
public interface Consumable {

	/**
	 * Get the price of the item. 
	 * 
	 * @return the price
	 */
	double getPrice();

	/**
	 * Get the calories of the item.
	 * 
	 * @return the calories
	 */
	int getCalories();

	/**
	 * Get the name of the item.
	 * 
	 * @return the name
	 */
	String getName();

	/**
	 * Set the price of the item to something new.
	 * 
	 * @param _price the new price
	 */
	void setPrice(double _price);

	/**
	 * Print a description of the given item to System.out.
	 * 
	 */
	void printContents();

}