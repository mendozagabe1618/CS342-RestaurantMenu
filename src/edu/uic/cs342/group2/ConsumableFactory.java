package edu.uic.cs342.group2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.uic.cs342.group2.Consumables.*;

/**
 * Used to design Consumable files based on file or user input.
 * 
 * NOTE: This is an implementation of the Factory Design Pattern
 * 
 * @author Dawid J. Zawislak 
 *
 */
public class ConsumableFactory {

	/**
	 * Constant that represents which directories the files will
	 * be created in. THIS MUST END IN A '/'.
	 */
	private String directory;
	
	private Scanner in = new Scanner(System.in);
	private File   dir = null;
	
	/**
	 * Create a ConsumableFactory that deals with the given directory.
	 * 
	 * @param _dir the directory where files should be stored.
	 */
	public ConsumableFactory( String _dir ) {
		directory = _dir;
		dir       = new File( _dir );
		
		if ( !dir.exists() ) {
			dir.mkdirs();
		}
	}
	
	/**
	 * Default the factory to work with a directory called "Consumables/"
	 * 
	 */
	public ConsumableFactory() {
		this( "Consumables/" );
	}
	
	/**
	 * Ask the user a series of questions regarding the type
	 * of Consumable they would like to  create.  Take  this 
	 * input, and process it accordingly:
	 * 		1) Add it to the Menu
	 * 		2) Create a <Food Name>.consumable file
	 *  
	 * @author Dawid J. Zawislak
	 * 
	 * @param _type_of_consumable the type of consumable to be created
	 *      
	 * @return the requested Consumable
	 */
	public Consumable getConsumable(ConsumableType _type_of_consumable) {
		Consumable ret = null;
		
		char c_in = 0;
		String name, s_price, s_kcal, s_consumableType;
	    double price;
	    int    kcal;
	    
	    /* Obtain the name of the Consumable. Keep trying until input
	     * is legal.
	     */
	    while ( true ) { 
	    	System.out.print( "[Name]> " );
	    	name = in.nextLine();
	    	if ( name.length() > 1 ) {
	    		break;
	    	}
	    	else {
	    		System.out.println("Invalid Input-- Try again.");
	    	}
	    }
	    
	    /* Obtain the price of the Consumable. Keep trying until input
	     * is legal.
	     */
	    while ( true ) {
		    System.out.print( "[Price]> $" );
		    s_price = in.nextLine();
		    try {
		    	price = Double.parseDouble( s_price );
		    } catch ( NumberFormatException e ) {
		    	System.out.println("Invalid Input-- Try again.");
		    	continue;
		    }
		    if ( price < 0  ) {
		    	System.out.println("Invalid Input-- Try again."); 
		    	continue;
		    }
		    break;
	    }
	    
	    /* Obtain the calories of the Consumable. Keep trying until input
	     * is legal.
	     */
	    while ( true ) {
		    System.out.print( "[Calories]> " );
		    s_kcal = in.nextLine();
		    try {
		    	kcal = Integer.parseInt( s_kcal );
		    } catch ( NumberFormatException e ) {
		    	System.out.println("Invalid Input-- Try again.");
		    	continue;
		    }
		    if ( kcal < 0  ) {
		    	System.out.println("Invalid Input-- Try again."); 
		    	continue;
		    }
		    break;
	    }
		
	    /* Create the file that will store data about this Consumable */
	    File filer = new File( directory + name + ".consumable");
	    PrintWriter filerw = null;
	    if ( filer.exists() ) {
	    	System.out.println("warning: '" + directory +name + ".consumable' already exists.");
	    	filerw = null;
	    }
	    else {
		    try {
		        filerw = new PrintWriter(directory + name + ".consumable", "UTF-8");
		    } catch (FileNotFoundException | UnsupportedEncodingException e) {
		        e.printStackTrace();
		    }
	    }
	    
	    /* Unique information per type */
	    //filerw.println(c_in);
	    
	    if ( filerw != null ) {
		    filerw.println(String.valueOf(_type_of_consumable));		
		    filerw.println(name);
		    filerw.println("" + price);
		    filerw.println("" + kcal );
	    }
	    String str_ingredients;
	    
	    
	    
	    /* Unique information per type */
    	if ( _type_of_consumable == ConsumableType.SODA ) {
    		/* A Soda contains no other information */
    		
    		ret = (Consumable) new Soda( price, kcal, name );
    		//System.out.println("Here\n");
    	}
    	else if ( _type_of_consumable == ConsumableType.LONG_DRINK ) {
    		/* A LongDrink has Ingredients */
    		
        	System.out.println("\t--Seperate ingredients with commas");
        	/* Wait for proper input */
        	while( true ) {
	            System.out.print("[Ingredients]> ");
	            str_ingredients = in.nextLine();
	            if ( str_ingredients.length() > 1 ) {
	            	break;
	            }
	            System.out.println("Invalid Input-- Try again.");
        	}
        	
        	/* Print ingredients to file */
    	    if ( filerw != null ) filerw.println(str_ingredients);
            
            /* Generate the Ingredients ArrayList, which will be the String separated by its commas */
            ArrayList<String> ingredients = new ArrayList<String>(Arrays.asList(str_ingredients.split(", ")));
           
            ret = (Consumable) new LongDrink( price, kcal, ingredients, name );
        }
    	else if ( _type_of_consumable == ConsumableType.APPETIZER ) {
    		/* An Appetizer has Ingredients */
    		
        	System.out.println("\t--Seperate ingredients with commas");
        	/* Wait for proper input */
        	while( true ) {
	            System.out.print("[Ingredients]> ");
	            str_ingredients = in.nextLine();
	            if ( str_ingredients.length() > 1 ) {
	            	break;
	            }
	            System.out.println("Invalid Input-- Try again.");
        	}
        	
        	/* Print ingredients to file */
        	if ( filerw != null ) filerw.println(str_ingredients);
            
            /* Generate the Ingredients ArrayList, which will be the String separated by its commas */
            ArrayList<String> ingredients = new ArrayList<String>(Arrays.asList(str_ingredients.split(",")));
            
            ret = new Appetizer( price, kcal, name, ingredients );
    	}
    	else if ( _type_of_consumable == ConsumableType.MAIN_DISH ) {	
    		/* A MainDish has Ingredients and a Side Dish */
    		
        	System.out.println("\t--Seperate ingredients with commas");
        	/* Wait for proper input */
        	while( true ) {
	            System.out.print("[Ingredients]> ");
	            str_ingredients = in.nextLine();
	            if ( str_ingredients.length() > 1 ) {
	            	break;
	            }
	            System.out.println("Invalid Input-- Try again.");
        	}
        	
        	/* Print ingredients to file */
        	if ( filerw != null ) filerw.println(str_ingredients);
            
            /* Generate the Ingredients ArrayList, which will be the String separated by its commas */
            ArrayList<String> ingredients = new ArrayList<String>(Arrays.asList(str_ingredients.split(",")));
            
            System.out.print("[Side Dish]> ");
            String side = in.nextLine();
            
            /* Print side dish to file */
            if ( filerw != null ) filerw.println(side);
            
            ret = new MainDish( price, kcal, name, ingredients, side );
    	}
    	else if ( _type_of_consumable == ConsumableType.DESSERT ) {
    		/* A Dessert has a description */
    		
            System.out.print("[Description]> ");
            String desc = in.nextLine();
            
            /* Print description to file */
            if ( filerw != null ) filerw.println(desc);
            
            ret = new Dessert( price, kcal, name, desc );
    	}
    	else{
    		System.out.println("No type match ");
    	}
    	
    	/* Close the file when you're done with it */
    	if ( filerw != null ) filerw.close();
		
	    return ret;
	}

	/**
	 * Create a Consumable from the given File. 
	 * 
	 * TODO: Support passing a Directory and/or just *.summary files
	 * 
	 * @author Dawid J. Zawislak and Enrique Vaca
	 * 
	 * @param _file the file to create a consumable from
	 * 
	 * @return the requested Consumable from file or null or error
	 */
	public Consumable createConsumableFromFile(File _file) {
		Consumable ret = null;
		String name = null, s_price = null, s_kcal = null, s_ingredients = null,
			   s_consumableType = null, sideDish = null, description = null;
		
	    double price = 0;
	    int    kcal = 0;
		
		Scanner scanner = null;
		
		/* Reads order details from file. If file not found, give an exception */
		try {
			scanner = new Scanner(_file);
		} 
		catch (FileNotFoundException e) {
			System.err.println("Error: file not found.");
			e.printStackTrace();
		}
		
		/* Create new directory if does not exist */
		_file = new File(directory);
		if (!_file.exists()) {
			if (_file.mkdir()) {
				//System.out.println("Directory is created!");
			} else {
				System.err.println("Failed to create directory!");
			}
		}
		
		/* Reads the consumable type */
		while ( s_consumableType == null ) {
			try {
				s_consumableType = scanner.nextLine();
			}
			catch ( NoSuchElementException e ) {
				return null;
			}
		}
		
		/* Reads the name of the consumable */
		while( name == null ) {
			try {
				name = scanner.nextLine();
			} 
			catch ( NoSuchElementException e ) {
				return null;
			}
		}
		
		/* Reads the price of the consumable and converts it to a double */
		while( s_price == null ) {
			try {
				s_price = scanner.nextLine();
			}
			catch ( NoSuchElementException e ) {
				return null;
			}
			
			try {
				price = Double.parseDouble( s_price );
			} catch ( NumberFormatException e ) {
				s_price = null;
			}
		}
		
		/* Reads the number calories of the consumable and converts it to an int */
		while( s_kcal == null ) {
			try {
				s_kcal = scanner.nextLine();
			} 
			catch ( NoSuchElementException e ) {
				s_kcal = "0";
				kcal = 0;
				break;
			}
			
			try {
				kcal = Integer.parseInt( s_kcal );
			} catch ( NumberFormatException e ) {
				s_kcal = null;
			}
		}
		
		/* If consumable type is SODA then no more reading has to be done and creates a new Soda */
		if (s_consumableType.equals(String.valueOf(ConsumableType.SODA))){
			ret = (Consumable) new Soda( price, kcal, name );
		}
		
		/* If consumable type is LONG_DRINK then read the next line for ingredients to a string and split them 
		 * with commas and create an ArrayList of ingredients
		 */
		else if (s_consumableType.equals(String.valueOf(ConsumableType.LONG_DRINK))){
			try {
				s_ingredients = scanner.nextLine();
			} 
			catch ( NoSuchElementException e ) {
				return null;
			}
			
			ArrayList<String> ingredients = new ArrayList<String>(Arrays.asList(s_ingredients.split(", ")));
			ret = (Consumable) new LongDrink( price, kcal, ingredients, name );
		}
		
		/* If consumable type is APPETIZER then read the next line for ingredients to a string and split them
		 * with commas and create an ArrayList of ingredients
		 */
		else if (s_consumableType.equals(String.valueOf(ConsumableType.APPETIZER))){
			try {
				s_ingredients = scanner.nextLine();
			} 
			catch ( NoSuchElementException e ) {
				return null;
			}
			ArrayList<String> ingredients = new ArrayList<String>(Arrays.asList(s_ingredients.split(", ")));
			ret = new Appetizer( price, kcal, name, ingredients );
		}
		
		/* If consumable type is MAIN_DISH then read the next line for ingredients to a string and split them
		 * with commas and create an ArrayList of ingredients. Read the next line for side dish
		 */
		else if(s_consumableType.equals(String.valueOf(ConsumableType.MAIN_DISH))){
			try { 
				s_ingredients = scanner.nextLine();
			} 
			catch ( NoSuchElementException e ) {
				return null;
			}
			ArrayList<String> ingredients = new ArrayList<String>(Arrays.asList(s_ingredients.split(", ")));
			
			sideDish = scanner.nextLine();
			ret = new MainDish( price, kcal, name, ingredients, sideDish );
		}
		
		/* If consumable type is DESSERT then read next line for description */
		else if(s_consumableType.equals(String.valueOf(ConsumableType.DESSERT))){
			try {
				description = scanner.nextLine();
			} 
			catch ( NoSuchElementException e ) {
				return null;
			}
			ret = new Dessert( price, kcal, name, description );
		}
		
		/* returns the proper consumable */
		return ret;
	}

}