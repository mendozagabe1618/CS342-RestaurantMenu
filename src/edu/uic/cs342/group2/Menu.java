package edu.uic.cs342.group2;

import edu.uic.cs342.group2.Consumables.*;

/**
 * A Menu that can be browsed by customers and edited by staff.
 * 
 * NOTE: This Menu class employs the use of the Singleton Design
 *       Pattern.
 * 
 * @author Dawid J. Zawislak
 *
 */
public class Menu {

	private MenuSubsection<Consumable> appetizers;
	private MenuSubsection<Consumable> mainDishes;
	private MenuSubsection<Consumable> desserts;
	private MenuSubsection<Consumable> sodas;
	private MenuSubsection<Consumable> longDrinks;
	
	private static Menu instance = null;

	/**
	 * Initialize all the subsections. This is a private constructor
	 * and can only be used by the class itself.
	 * 
	 */
	private Menu() {
		appetizers = new MenuSubsection<Consumable>();
		mainDishes = new MenuSubsection<Consumable>();
		desserts   = new MenuSubsection<Consumable>();
		sodas      = new MenuSubsection<Consumable>();
		longDrinks = new MenuSubsection<Consumable>();
	}

	/**
	 * Obtain an instance of Menu. There is a limit of one unique
	 * instance.
	 * 
	 * @return the instance of menu
	 */
	public static synchronized Menu getInstance() {
		if ( instance == null ) {
			instance = new Menu();
		}
		return instance;
	}

	/**
	 * Add an Appetizer to the given MenuSubsection.
	 * 
	 * @param _appetizer an Appetizer
	 */
	public void addAppetizer(Appetizer _appetizer) {
		appetizers.add(_appetizer);
	}

	/**
	 * Add a MainDish to the given MenuSubsection.
	 * 
	 * @param _mainDish a MainDish
	 */
	public void addMainDish(MainDish _mainDish) {
		mainDishes.add(_mainDish);
	}

	/**
	 * Add a Dessert to the given MenuSubsection.
	 * 
	 * @param _dessert a Dessert
	 */
	public void addDessert(Dessert _dessert) {
		desserts.add(_dessert);
	}

	/**
	 * Add a Soda to the given MenuSubsection.
	 * 
	 * @param _soda a Soda
	 */
	public void addSoda(Soda _soda) {
		sodas.add((Consumable) _soda);
	}
	
	/**
	 * Add a LongDrink to the given MenuSubsection.
	 * 
	 * @param _longDrink a LongDrink
	 */
	public void addLongDrink(LongDrink _longDrink) {
		longDrinks.add((Consumable) _longDrink);
	}

	/**
	 * Remove an Appetizer from the given MenuSubsection.
	 * 
	 * @param _appetizer an Appetizer
	 */
	public void removeAppetizer(Appetizer _appetizer) {
		appetizers.remove(_appetizer);
	}

	/**
	 * Remove a MainDish from the given MenuSubsection.
	 * 
	 * @param _mainDish a MainDish
	 */
	public void removeMainDish(MainDish _mainDish) {
		mainDishes.remove(_mainDish);
	}

	/**
	 * Remove a Dessert from the given MenuSubsection.
	 * 
	 * @param _dessert a Dessert
	 */
	public void removeDessert(Dessert _dessert) {
		desserts.remove(_dessert);
	}

	/**
	 * Remove a Soda from the given MenuSubsection.
	 * 
	 * @param _soda a Soda
	 */
	public void removeSoda(Soda _soda) {
		sodas.remove((Consumable) _soda);
	}

	/**
	 * Remove a LongDrink from the given MenuSubsection.
	 * 
	 * @param _longDrink a LongDrink
	 */
	public void removeLongDrink(LongDrink _longDrink) {
		longDrinks.remove((Consumable) _longDrink);
	}

	/**
	 * Obtain the Appetizer MenuSubsection.
	 * 
	 * @return the MenuSubsection
	 */
	public MenuSubsection<Consumable> getAppetizers() {
		return this.appetizers;
	}

	/**
	 * Obtain the MainDish MenuSubsection.
	 * 
	 * @return the MenuSubsection
	 */
	public MenuSubsection<Consumable> getMainDishes() {
		return this.mainDishes;
	}

	/**
	 * Obtain the Dessert MenuSubsection.
	 * 
	 * @return the MenuSubsection
	 */
	public MenuSubsection<Consumable> getDesserts() {
		return this.desserts;
	}

	/**
	 * Obtain the Soda MenuSubsection.
	 * 
	 * @return the MenuSubsection
	 */
	public MenuSubsection<Consumable> getSodas() {
		return this.sodas;
	}

	/**
	 * Obtain the LongDrink MenuSubsection.
	 * 
	 * @return the MenuSubsection
	 */
	public MenuSubsection<Consumable> getLongDrinks() {
		return this.longDrinks;
	}
	
	/**
	 * Clear the menu of all food items. This will NOT  delete
	 * the files inside the folder, and these will be reloaded
	 * upon launch if not deleted by hand. 
	 * 
	 */
	public void resetMenu() {
		appetizers = new MenuSubsection<Consumable>();
		mainDishes = new MenuSubsection<Consumable>();
		desserts   = new MenuSubsection<Consumable>();
		sodas      = new MenuSubsection<Consumable>();
		longDrinks = new MenuSubsection<Consumable>();
	}

}