package edu.uic.cs342.group2.Consumables;

/**
 * A Dessert for the Menu
 * 
 * @author Gabriel Mendoza & Dawid J. Zawislak
 *
 */
public class Dessert extends Food implements Consumable {

	protected String description;

	/**
	 * Initialize a Dessert with the following data.
	 * 
	 * @param _price the price of the dessert
	 * @param _calories the calorie count of the dessert
	 * @param _name the name of the dessert
	 * @param _description a description of the dessert
	 */
	public Dessert(double _price, int _calories, String _name, String _description) {
		super( _price, _calories, _name );
		this.description = _description;
	}

	/**
	 * Return the description of the dessert.
	 * 
	 * @return the description of the dessert
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Set the description of the dessert to something new.
	 * 
	 * @param description the new description of the dessert
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Stringify the contents of this drink
	 */
	public void printContents() {
		super.printContents();
		System.out.println("Description: " + getDescription() );
	}

}