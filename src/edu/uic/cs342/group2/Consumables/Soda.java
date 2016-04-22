package edu.uic.cs342.group2.Consumables;

/**
 * A Soda for the Menu
 * 
 * @author Scott Jette
 *
 */
public class Soda extends Drink implements Consumable {

	/**
	 * Creates a Soda given a price, the amount of calories, and a name
	 * 
	 * @param _price the price
	 * @param _calories the calorie count
	 * @param _name the name
	 */
	public Soda(double _price, int _calories, String _name) {
		super(_price, _calories, _name);
	}
	
	/**
	 * Creates a Soda given a number of calories and a name. Defaults price to 0.
	 * 
	 * @param _calories the calorie count
	 * @param _name the name
	 */
	public Soda(int _calories, String _name) {
		super(0.0, _calories, _name);
	}

	/**
	 * Print a description of the given item to System.out.
	 * 
	 */
	public void printContents() {
		super.printContents();
	}

}