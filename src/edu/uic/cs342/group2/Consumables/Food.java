package edu.uic.cs342.group2.Consumables;

/**
 * An abstract class for anything that a person can eat.
 * 
 * @author Gabe Mendoza
 *
 */
public abstract class Food implements Consumable {

	protected double price;
	protected int calories;
	protected String name;

	/**
	 * Initialize a food item with the provided arguments.
	 * 
	 * @param _price the price of the item
	 * @param _calories the calories count of the item
	 * @param _name the name of the item
	 */
	public Food(double _price, int _calories, String _name) {
		this.price    = _price;
		this.calories = _calories;
		this.name     = _name;
	}

	/**
	 * Initialize a food item with the provided arguments and a calorie
	 * count of 0.
	 * 
	 * @param _price the price of the item
	 * @param _name the name of the item
	 */
	public Food(double _price, String _name) {
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
		else {
			System.out.println("Price cannot be less than $0.00.");
		}
	}

	/**
	 * Stringify the contents of this food item
	 */
	public void printContents() {
		System.out.println("Name: " + this.getName() );
		//System.out.println("Price: " + this.getPrice() );
		System.out.printf("Price: $%.2f\n", this.getPrice());
		System.out.println("Calories: " + this.getCalories() );
	}

}