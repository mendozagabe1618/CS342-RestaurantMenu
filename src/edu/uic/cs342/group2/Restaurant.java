package edu.uic.cs342.group2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.uic.cs342.group2.MenuSubsection.MenuIterator;
import edu.uic.cs342.group2.Consumables.Appetizer;
import edu.uic.cs342.group2.Consumables.Consumable;
import edu.uic.cs342.group2.Consumables.ConsumableType;
import edu.uic.cs342.group2.Consumables.Dessert;
import edu.uic.cs342.group2.Consumables.LongDrink;
import edu.uic.cs342.group2.Consumables.MainDish;
import edu.uic.cs342.group2.Consumables.Soda;

/**
 * The Gigabyte Cafe User Interface
 * 
 * @author Dawid J. Zawislak
 *
 */
public class Restaurant {

	public static final String DIRECTORY = "Consumables/";
	
	private static Scanner           in      = new Scanner( System.in ); 
	private static ConsumableFactory factory = new ConsumableFactory( DIRECTORY ); 
	private static Menu              menu    = Menu.getInstance();
	
	private static ArrayList<Consumable> favorites = new ArrayList<Consumable>();
	
	/** 
	 * Add all the files inside of DIRECTORY, non-recursive.
	 * 
	 */
	private static void add_files() {
		File dir = new File( DIRECTORY );
		if ( !dir.exists() ) {
			return;
		}
		
		for ( File f: dir.listFiles() ) {
			if ( f.isFile() ) {
				String extension = null;
				
				int last = f.getName().lastIndexOf('.');
				
				if ( last > 0 ) {
					/* If last == 0, it is a hidden folder on
					 * a Linux/Darwin environment.
					 */
					extension = f.getName().substring(last + 1);
				}
				
				/* If a file is a consumable, it will end in .consumable
				 */
				if ( extension != null && extension.equals("consumable") ) {
					Consumable cons = factory.createConsumableFromFile( f );
					if ( cons == null ) {
						System.err.println("warning: '" + f.getName() + "' is corrupted.");
						continue;
					}
					
					String cons_str = cons.getClass().getName();
					
					if ( cons_str.endsWith("Appetizer") ) {
						menu.addAppetizer((Appetizer)cons);
					}
					else if ( cons_str.endsWith("MainDish") ) {
						menu.addMainDish((MainDish)cons);
					}
					else if ( cons_str.endsWith("Dessert") ) {
						menu.addDessert((Dessert)cons);
					}
					else if ( cons_str.endsWith("Soda") ) {
						menu.addSoda((Soda)cons);
					}
					else if ( cons_str.endsWith("LongDrink") ) {
						menu.addLongDrink((LongDrink)cons);
					}
					else {
						System.err.println("Something went wrong. Failed to add '" + cons.getName() + "'");
					}
				}
			}
		}	
	}
	
	/**
	 *  User interface to add or delete from the menu 
	 */
	private static void admin_tools() {
		System.out.println("---");
		System.out.println("Please provide owner password (Its 'password')");
		System.out.print("> ");
		
		String pwd = in.nextLine();
		
		if ( pwd.compareTo("password") == 0 ) {
			while ( true ) {
				System.out.print("[C]reate Item, [E]dit Menu, [W]ipe Menu, [B]ack>> ");
				String input = in.nextLine();
				if ( input.length() <= 0 ) {
					continue;
				}
				
				Consumable cons_created  = null;
				ConsumableType cons_type = ConsumableType.ERROR;
				
				char c_in = Character.toLowerCase( input.charAt(0) );
				/* Gives the user choices of what consumable to create */
				if ( c_in == 'c' ) {
					boolean asking_input = true;
					while ( asking_input ) {
						System.out.print("[A]ppetizer, [M]ain Dish, [D]essert, [S]oda, [L]ong Drink, or [B]ack?>> ");
						input = in.nextLine();
						if ( input.length() >= 1 ) {
							c_in = Character.toLowerCase( input.charAt(0) );
							if ( c_in == 'a' ) {
								cons_type = ConsumableType.APPETIZER;
							}
							else if ( c_in == 'm' ) {
								cons_type = ConsumableType.MAIN_DISH;
							}
							else if ( c_in == 'd' ) {
								cons_type = ConsumableType.DESSERT;
							}
							else if ( c_in == 's' ) {
								cons_type = ConsumableType.SODA;
							}
							else if ( c_in == 'l' ) {
								cons_type = ConsumableType.LONG_DRINK;
							}
							else if ( c_in == 'b' ) {
								break;
							}
							else {
								System.out.println("Invalid Input. Try again or 'b' to go back.");
								continue;
							}
							
							/* Creates the consumable requested by the user */
							cons_created = factory.getConsumable(cons_type);
							switch ( cons_type ) {
								case APPETIZER:
									menu.addAppetizer((Appetizer) cons_created);
									break;
								case MAIN_DISH:
									menu.addMainDish((MainDish) cons_created);
									break;
								case DESSERT:
									menu.addDessert((Dessert) cons_created);
									break;
								case SODA:
									menu.addSoda((Soda) cons_created);
									break;
								case LONG_DRINK:
									menu.addLongDrink((LongDrink) cons_created);
									break;
								default:
									System.err.println("Something went wrong here. Cons_type should not be 'ERROR'");
							}
						}
					}
				}
				else if ( c_in == 'e' ) {
					browse_menu( true );
				}
				else if ( c_in == 'b' ) {
					break;
				}
				else if ( c_in == 'w' ) {
					System.out.println("Notice: This does NOT delete the consumable files and this lasts for the duration of this run.");
					menu.resetMenu();
				}
				else {
					System.out.println("Invalid Input. Try again or 'b' to go back.");
				}
			}
		}
		else {
			System.out.println("Invalid Password.");
		}

		System.out.println("---\n");
	}
	
	/**
	 * Prints the users favorite items along with total calorie count
	 * and price
	 * 
	 */
	private static void favorites() {
		double price    = 0;
		double calories = 0;
		
		System.out.println("--Favorites--");
		if ( favorites.size() == 0 ) {
			System.out.println("-------------");
		}
		else {
			for ( Consumable c: favorites ) {
				c.printContents();
				price    += c.getPrice();
				calories += c.getCalories();
				System.out.println("---");
			}
		}
		System.out.println("--Total Calories: " + calories + " calories" );
		System.out.printf( "--Total Price:    $%.2f\n", price );
		
	}
	
	/**
	 * User interface to browse the menu.
	 * 
	 * @param _admin_status if true, allow deletion of items
	 */
	@SuppressWarnings("resource")
	public static void browse_menu( boolean _admin_status ) {
		Menu           m     = Menu.getInstance();
		String         input = null;
		ConsumableType cons  = ConsumableType.ERROR;
		
		MenuSubsection<Consumable> subsection = null;
		MenuIterator<Consumable>   iterator   = null;
		
		System.out.println("--Menu--");
		
		/* Ask for food or drink and set the ConsumableType
		 * cons accordingly.
		 * */
		while ( true ) {
			System.out.print("[F]ood, [D]rink, or [B]ack>> ");
			input = in.nextLine();	
			if ( input.length() > 0 ) {
				char c_in = Character.toLowerCase( input.charAt(0) );
				if ( c_in == 'b' ) {
					break;
				}
				/* Gives the user choices of food consumables to view in menu*/
				else if ( c_in == 'f' ) {
					while ( true ) {
						System.out.print("\t[A]ppetizer, [M]ain Dish, [D]essert, or [B]ack>> ");
						input = in.nextLine();
						if ( input.length() > 0 ) {
							c_in = Character.toLowerCase( input.charAt(0) );
							
							boolean exit = false;
									
							switch ( c_in ) {
								case('a'):
									cons = ConsumableType.APPETIZER;
									break;
								case('m'):
									cons = ConsumableType.MAIN_DISH;
									break;
								case('d'):
									cons = ConsumableType.DESSERT;
									break;
								case('b'):
									exit = true;
									break;
								default:
									System.out.println("Invalid Input. Try again or 'b' to go back.");
							}
							
							if ( exit || cons != ConsumableType.ERROR ) {
								break;
							}
						}	
					}
				}
				/* Gives the user choices of drink consumables to view in menu*/
				else if ( c_in == 'd' ) {
					while ( true ) {
						System.out.print("\t[L]ong Drink, [S]oda, or [B]ack>> ");
						input = in.nextLine();
						if ( input.length() > 0 ) {
							c_in = Character.toLowerCase( input.charAt(0) );
							
							boolean exit = false;
									
							switch ( c_in ) {
								case('l'):
									cons = ConsumableType.LONG_DRINK;
									break;
								case('s'):
									cons = ConsumableType.SODA;
									break;
								case('b'):
									exit = true;
									break;
								default:
									System.out.println("Invalid Input. Try again or 'b' to go back.");
							}
							
							if ( exit || cons != ConsumableType.ERROR ) {
								break;
							}
						}	
					}
				}
				else {
					System.out.println("Invalid Input. Try again or 'b' to go back.");
				}
				
			}
			
			System.out.println("");
			
			if ( cons != ConsumableType.ERROR ) {
				break;
			}
			
		}
		
		/* After cons was set,  determine the type of item the
		 * user wants. Use the appropriate get<..>() method to
		 * obtain this list.
		 */
		switch ( cons ) {
			case APPETIZER:
				subsection = m.getAppetizers();
				break;
			case MAIN_DISH:
				subsection = m.getMainDishes();
				break;
			case DESSERT:
				subsection = m.getDesserts();
				break;
			case SODA:
				subsection = m.getSodas();
				break;
			case LONG_DRINK:
				subsection = m.getLongDrinks();
				break;
		}
		
		/* If subsection is null, the user probably exited. */
		if ( subsection != null ) {
			iterator = subsection.getIterator();
			iterator.reset();
			System.out.println("---");
			
			boolean print_contents = true;
			
			/* Check for empty list */
			if ( iterator.hasCurrent() == false ) {
				System.out.println("This subsection is currently empty.");
			}
			else {
				Consumable next = iterator.current();
				
				/* Allow user to go back and forth */
				boolean run_loop = true;
				while ( run_loop ) {
					if ( iterator.current() == null ) {
						System.out.println("There are no available items.");
						break;
					}
					
					if ( print_contents ) iterator.current().printContents();
				
					if ( _admin_status ) {
						System.out.print("[N]ext, [P]revious, [T]oggle Favorites, [C]hange Price, [D]elete, or [B]ack>> ");
					}
					else {
						System.out.print("[N]ext, [P]revious, [T]oggle Favorites, or [B]ack>> ");
					}
					
					input = in.nextLine();
					if ( input.length() > 0 ) {
						char c_in = Character.toLowerCase( input.charAt(0) );
						
						if ( c_in == 'n' ) {
							if ( iterator.hasNext() == false ) {
								System.out.println("There are no more items available.");
								print_contents = false;
							}
							else {
								next = iterator.next();
								print_contents = true;
							}
						}
						else if ( c_in == 't' ) {
							if ( favorites.contains( iterator.current() ) ) {
								System.out.println("Removed '" + iterator.current().getName() + "' from favorites.");
								favorites.remove( iterator.current() );
							}
							else {
								System.out.println("Added '" + iterator.current().getName() + "' to favorites.");
								favorites.add( iterator.current() );
							}
							print_contents = false;
						}
						else if ( c_in == 'p' ) {
							if ( iterator.hasPrev() == false ) {
								System.out.println("There is nothing before this.");
								print_contents = false;
							}
							else {
								iterator.previous();
								next = iterator.current();
								print_contents = true;
							}
						}
						else if ( c_in == 'b' ) {
							run_loop = false;
						}
						else if ( c_in == 'c' && _admin_status ) {
							if ( iterator.current() == null ) {
								System.out.println( "Error: No item is currently selected.");
							}
							else {
								System.out.println("Item '" + iterator.current().getName() + "' currently costs $" + iterator.current().getPrice() + ".");
								double price = -1;
								
								/* Obtain new price */
								while ( price == -1 ) {
									System.out.print("New Price>> $");
									String new_line = in.nextLine();
									
									try {
								    	price = Double.parseDouble( new_line );
								    } catch ( NumberFormatException e ) {
								    	System.out.println("Invalid Input-- Try again.");
								    	continue;
								    }
								    if ( price < 0  ) {
								    	System.out.println("Invalid Input-- Try again."); 
								    	continue;
								    }
								}
								
								/* Set the price */
								iterator.current().setPrice(price);
								
								/* Delete the old consumable and create a new one with an updated
								 * price
								 */
								String del_file_str = DIRECTORY + iterator.current().getName() + ".consumable";
								String new_file_str = del_file_str + ".temp";
								File del_file = new File( del_file_str );
								
								if ( del_file.exists() ) {
								    PrintWriter new_file = null;
								    try {
								        new_file = new PrintWriter(new_file_str, "UTF-8");
								    } catch (FileNotFoundException | UnsupportedEncodingException e) {
								        e.printStackTrace();
								    }
									
									Scanner del_scanner;
									try {
										del_scanner = new Scanner( del_file );
									} catch (FileNotFoundException e) {
										break;
									}
									
									String file_cpy = null;
									while ( file_cpy == null || ( file_cpy != null && file_cpy.length() <= 2 ) ) {
										try {
											file_cpy = del_scanner.nextLine();
										}
										catch ( NoSuchElementException e ) {
											break;
										}
									}
									new_file.println(file_cpy);
									
									try {
									file_cpy = del_scanner.nextLine();
									}
									catch ( NoSuchElementException e ) {
										break;
									}
									new_file.println(file_cpy);
									
									try {
									file_cpy = del_scanner.nextLine();
									}
									catch ( NoSuchElementException e ) {
										break;
									}
									new_file.println("" + iterator.current().getPrice() );
									
									while ( file_cpy != null && file_cpy.length() >= 1 ) {
										try {
											file_cpy = del_scanner.nextLine();
											new_file.println(file_cpy);
										}
										catch ( NoSuchElementException e ) {
											break;
										}
									}
									
									del_file.delete();
									new_file.close();
									
									File new_file_f = new File( new_file_str );
									if ( new_file_f.exists() ) {
										new_file_f.renameTo(del_file);
									}
								}
								
								print_contents = true;
								
							}   
						}
						else if ( c_in == 'd' && _admin_status ) {
							if ( iterator.current() == null ) {
								System.out.println("Error: No item is currently selected.");
							}
							else {
								System.out.println("Deleting '" + iterator.current().getName() + "'." );
								
								String del_file_str = DIRECTORY + iterator.current().getName() + ".consumable";
								File del_file = new File( del_file_str );
								
								if ( del_file.exists() ) {
									del_file.delete();
								}
								
								System.out.println(del_file_str);
								iterator.deleteCurrent();
							}
							
							print_contents = true;
							
						}
						else {
							System.out.println("Invalid Input. Try Again.");
							
							print_contents = false;
						}
					}
					
				}
				
			}
		}
		
		System.out.println("---\n");
	}
	
	/**
	 * Program starts here
	 * Main use interface
	 * 
	 */
	public static void main(String[] args) {
		
		add_files(); 
		
		File directory = new File("Consumables/");
		if ( !directory.exists() ) {
			directory.mkdir();
		}
		
		String input = null;
		boolean run  = true;
		
		System.out.println("--Welcome-to-the-Gigabyte-Cafe--\n");
		
		while ( run ) {
			
			while ( true ) {
				System.out.println("[B]rowse Menu");
				System.out.println("[C]heck Favorites");
				System.out.println("[A]dministrative Tools");
				System.out.println("[E]xit");
				System.out.print("> ");
				
				input  = in.nextLine();

				if ( input.length() >= 1 ) {
					char c = Character.toLowerCase( input.charAt(0) );
					
					if ( c == 'b' ) {
						browse_menu( false );
					}
					else if ( c == 'c' ) {
						favorites();
					}
					else if ( c == 'a' ) {
						admin_tools();
					}
					else if ( c == 'e' ) {
						System.out.println("Goodbye.");
						run = false;
					}
					else {
						System.out.println("Invalid Input.\n---\n");
						continue;
					}
					
					break;
				}
			}
			
			System.out.println("");
		}					
	}
}