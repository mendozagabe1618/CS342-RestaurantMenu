package edu.uic.cs342.group2.Consumables;

import java.util.ArrayList;

/**
 * A MainDish for the Menu
 * 
 * @author Gabe Mendoza 
 *
 */
public class MainDish extends Food implements HasIngredients, Consumable {

	protected ArrayList<String> ingredients;
	protected String sideDish;

	/**
	 * Create a main dish
	 * 
	 * @param _price the price of the Main Dish
	 * @param _calories the calories of the Main Dish
	 * @param _ingredients array list of ingredients
	 * @param _name	name of the dish as a string
	 * @param _side the side dish 
	 */
	public MainDish(double _price, int _calories, String _name, ArrayList<String> _ingredients, String _side) {
		super( _price, _calories, _name );
		ingredients = _ingredients;
		sideDish    = _side;
	}

	/**
	 * Add an ingredient to the item. 
	 * 
	 * @param _ingredient add an ingredient to the array list of ingredients
	 * 
	 * @return the return value of adding it to the array list
	 */
	public boolean addIngredient(String _ingredient) {
		return this.ingredients.add(_ingredient);
	}

	/**
	 * Remove an ingredient from the dish. 
	 * 
	 * @param _ingredient remove an ingredient from the array list
	 * 
	 * @return true if deleted, false if not found
	 */
	public boolean removeIngredient(String _ingredient) {
		int i = 0;
		for( String s: this.ingredients)
		{
			if(s == _ingredient) {
				this.ingredients.remove(i);
				return true;
			}
			i++;
		}
		
		return false;
	}

	/**
	 *  Returns an array list of the ingredients in the main dish
	 *  
	 *  @return the array list
	 */
	public ArrayList<String> listIngredients() {
		return this.ingredients;
	}
	
	/** 
	 * Returns the side dish
	 * 
	 * @return the side dish
	 */
	public String getSide() {
		return this.getSide();
	}

	/**
	 * Sets the side dish 
	 * 
	 * @param _side the name of the side dish as a string
	 */
	public void setSide(String _side) {
		this.sideDish = _side;
	}
	
	/**
	 *  List the ingredient list. implemented as an array list iterated
	 *  with a for each loop
	 */
	public void printContents() {
		super.printContents();
		System.out.println("Ingredients: " + ingredients.toString());
		/*
		for (String s in this.ingredients) 
		{
			System.out.println(s);
		}
		*/
	}

}