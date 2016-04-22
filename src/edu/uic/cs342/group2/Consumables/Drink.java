package edu.uic.cs342.group2.Consumables;

/**
 * An abstract class for anything that a person can drink.
 * 
 * @author Dawid J. Zawislak
 *
 */
public abstract class Drink implements Consumable {

	protected double price;
	protected int calories;
	protected String name;

	/**
	 * Initialize a drink with the provided arguments.
	 * 
	 * @param _price the price of the item
	 * @param _calories the calories count of the item
	 * @param _name the name of the item
	 */
	public Drink(double _price, int _calories, String _name) {
		price    = _price;
		calories = _calories;
		name     = _name;
	}

	/**
	 * Initialize a drink with the provided arguments and a calorie
	 * count of 0.
	 * 
	 * @param _price the price of the item
	 * @param _name the name of the item
	 */
	public Drink(double _price, String _name) {
		this( _price, 0, _name );
	}
	
	/**
	 * Return the price of the item.
	 * 
	 * @return the price
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * Return the amount of calories in the item.
	 * 
	 * @return the calorie count
	 */
	public int getCalories() {
		return this.calories;
	}

	/**
	 * Return the name of the item.
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the price of the item to a value at or above 0. 
	 * 
	 * @param _price the new price
	 */
	public void setPrice(double _price) {
		if ( _price >= 0 ) {
			this.price = _price;
		}
	}

	/**
	 * Stringify the contents of this drink
	 */
	public void printContents() {
		System.out.println("Name: " + getName() );
		//System.out.println("Price: " + getPrice() );
		System.out.printf("Price: $%.2f\n", this.getPrice());
		System.out.println("Calories: " + getCalories() );
	}

}