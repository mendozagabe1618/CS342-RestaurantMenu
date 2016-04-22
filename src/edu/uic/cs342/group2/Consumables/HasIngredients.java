package edu.uic.cs342.group2.Consumables;

import java.util.ArrayList;

/**
 * An interface that should be used by Consumables
 * that are going to have ingredients.
 * 
 * @author Group II
 *
 */
public interface HasIngredients {

	/**
	 * Add an ingredient to the list of ingredients.
	 * 
	 * TODO: Ensure that every ingredient starts with a
	 *       capital letter and ends in all lowercase.
	 *       ie) Sugar, Pepper, Vodka
	 * 
	 * @param _ingredient an ingredient to add
	 */
	public boolean addIngredient(String _ingredient);

	/**
	 * Remove an ingredient from the list  of  ingredients. 
	 * This should only remove one ingredient, so if there
	 * are two sugars, only delete one.
	 * 
	 * TODO: If the input does not start with a capital and
	 *       end with lowercase, just correct it.
	 *       ie) {sugar, SUGAR, SuGAR} -> Sugar
	 * 
	 * @param _ingredient an ingredient to remove
	 * 
	 */
	public boolean removeIngredient(String _ingredient);

	/**
	 * Return the list of ingredients.
	 * 
	 * @return the list of ingredients.
	 */
	public ArrayList<String> listIngredients();
}
