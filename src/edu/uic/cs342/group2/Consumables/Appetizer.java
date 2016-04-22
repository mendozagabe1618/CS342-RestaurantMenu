package edu.uic.cs342.group2.Consumables;
import java.util.ArrayList;

/**
 * An Appetizer for the Menu
 * 
 * @author Gabriel Mendoza & Dawid J. Zawislak (Error Checking and Comments)
 *
 */
public class Appetizer extends Food implements HasIngredients, Consumable {

	protected ArrayList<String> ingredients = null;

	/**
	 * Create an appetizer with the specified information.
	 * 
	 * @param _price the price of the item
	 * @param _carolies the amount of calories in the item
	 * @param _ingredients the name of the item
	 * @param _name the ingredients in the item
	 */
	public Appetizer(double _price, int _calories, String _name, ArrayList<String> _ingredients) {
		super( _price, _calories, _name);
		this.ingredients = _ingredients;
	}

	/**
	 * Add an ingredient to the item. 
	 * 
	 * @param _ingredient ingredient to add
	 * 
	 * @return true if added, false if not added
	 */
	public boolean addIngredient(String _ingredient) {
		if(this.ingredients == null) {
			return false;
		}
		else {
			this.ingredients.add(_ingredient);
		}
		
		return true;
	}

	/**
	 * Remove an ingredient from the item.
	 * 
	 * @param _ingredient ingredient to remove
	 * 
	 * @return true if removed; false if not removed
	 */
	public boolean removeIngredient(String _ingredient) {
		int i = 0;
		for(String s : this.ingredients  )
		{
			if( s == _ingredient) {
				this.ingredients.remove(i);
				return true;
			}
			++i;
		}
		
		return false;
	}

	/**
	 * Return the ingredients ArrayList of this item
	 * 
	 * @return the ArrayList of this item
	 */
	public ArrayList<String> listIngredients() {
		return this.ingredients;
	}

	/**
	 * Stringify the contents of this item.
	 * 
	 */
	public void printContents() {
		super.printContents();
		System.out.println("Ingredients: " + ingredients.toString());
		/*
		System.out.println("Ingredients: - ");
		for( String s : this.ingredients )
		{
			System.out.print(s + " - " );
		}
		System.out.println(" - ");
		*/
	}

}