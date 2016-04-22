package edu.uic.cs342.group2.Consumables;

import java.util.ArrayList;

/**
 * A LongDrink for the Menu
 * 
 * @author Scott Jette
 *
 */
public class LongDrink extends Drink implements HasIngredients, Consumable {

	protected ArrayList<String> ingredients;

	/**
	 * Creates a LongDrink given a price, list of ingredients given as an ArrayList<String>,
	 * and a name.
	 * 
	 * @param _price the price of the item
	 * @param _calories the calorie count
	 * @param _ingredients the ingredients of the item
	 * @param _name the name of the item
	 */
	public LongDrink(double _price, int _calories, ArrayList<String> _ingredients, String _name) {
		super(_price, _calories, _name);
		ingredients = _ingredients;
	}

	/**
	 * Adds an ingredient to the end of the ingredient list.
	 * 
	 * @param _ingredient the new ingredient to add
	 */
	public boolean addIngredient(String _ingredient) {
		return ingredients.add(_ingredient);
	}

	/**
	 * Given the name of an ingredient, removes it from the ingredient list.
	 *
	 * @param _ingredient
	 */
	public boolean removeIngredient(String _ingredient) {
		return ingredients.remove(_ingredient);
	}

	/**
	 * Gets the list of ingredients for this LongDrink.
	 * 
	 * @return the ArrayList of Ingredients
	 */
	public ArrayList<String> listIngredients() {
		return ingredients;
	}

	/**
	 * Print a description of the given item to System.out.
	 * 
	 */
	public void printContents() {
		super.printContents();
		System.out.println("Ingredients: " + ingredients.toString());
	}

}