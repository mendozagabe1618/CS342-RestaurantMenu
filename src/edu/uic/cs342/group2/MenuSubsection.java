package edu.uic.cs342.group2;

import java.util.ArrayList;

/**
 * Is a subsection of the Menu that holds food or beverage items for a 
 * specific category of the menu.
 * 
 * NOTE: This is the implementation of the Iterator Design Pattern.
 * 
 * @author Dawid J. Zawislak
 *
 * @param <E> E is the type of objects being held in this subsection.
 */
public class MenuSubsection<E> implements Container<E> {

	protected ArrayList<E>    list;
	protected MenuIterator<E> iterator;

	/**
	 * Initialize the MenuIterator to a given ArrayList.
	 * 
	 * @param _list the list that will be used to initialize
	 *              this class
	 */
	public MenuSubsection(ArrayList<E> _list) {
		list     = _list;
		iterator = new MenuIterator<E>( list );
	}

	/**
	 * Initialize the MenuIterator to an empty ArrayList.
	 * 
	 */
	public MenuSubsection() {
		list     = new ArrayList<E>();
		iterator = new MenuIterator<E>( list );
	}

	/**
	 * The MenuIterator is what will be used to navigate through the given
	 * subsection of the menu.
	 * 
	 * @author Dawid J. Zawislak
	 *
	 * @param <E> E is the type of objects being held in this subsection.
	 */
	public static class MenuIterator<E> implements Iterator<E> {

		protected ArrayList<E> list_iterator;
 		protected int          index;

 		/**
 		 * Initialize the MenuIterator to the ArrayList that is inside of 
 		 * MenuSubsection. The index shall start at index 0.
 		 * 
 		 * @param _l the ArrayList being held by MenuSubsection.
 		 */
		protected MenuIterator( ArrayList<E> _l ) { 
			index = 0;
			list_iterator = _l;
		}

		/**
		 * Return the current element.
		 * 
		 * @return the current element
		 */
		public E current() {
			if ( list_iterator.size() == 0 ) {
				return null;
			}
			
			try {
				return (E) list_iterator.get(index);
			} 
			catch ( IndexOutOfBoundsException e ) {
				if ( index < 0 ) {
					index = 1; 
					/* To make up for the fact that - 1 will be 
					 * done in the line below
					 */
				}
				index --;
				return (E) list_iterator.get( index );
			}
		}
		
		/**
		 * Return the next element available in the ArrayList. This will return 
		 * null if no such element exists.
		 * 
		 * @return the next element in the ArrayList, or null
		 */
		public E next() {
			if ( hasNext() == false ) {
				return null;
			}
			
			index ++;
			E element = (E) list_iterator.get(index);
			
			return element;
		}

		/**
		 * Checks if there is another element available after the current position.
		 * 
		 * @return true if there is; false if there is not
		 */
		public boolean hasNext() {
			int size = list_iterator.size();

			return ( size == 0 || size - 1 <= index ) ? false : true;

		}
		
		/**
		 * Return the previous element in the ArrayList. This will return 
		 * null if no such element exists.
		 * 
		 * @return the previous element in the ArrayList, or null
		 */
		public E previous() {
			if ( hasPrev() == false ) {
				return null;
			}
			
			index --;
			E element = (E) list_iterator.get(index);
			
			return element;
		}
		
		/**
		 * Checks if there is an element before the current position.
		 * 
		 * @return true if there is; false if there is not
		 */
		public boolean hasPrev() {
			return ( index > 0 ) ? true : false;
		}
		
		/**
		 * Returns true if there is a valid value at current.
		 * 
		 * @return true if pointing to valid value, otherwise false
		 */
		public boolean hasCurrent() {
			int size = list_iterator.size();
			
			if ( size == 1 && index == 0 ) {
				return true;
			}
			else if ( index >= 0 && index < size ) {
				return true;
			}
			
			return false;
		}
		
		/**
		 * Delete the current element. This will reset the
		 * index to 0.
		 * 
		 */
		public void deleteCurrent() {
			E curr = current();
			if ( curr != null ) {
				list_iterator.remove( curr );
				index = 0;
			}
		}
		
		/** 
		 * Reset the index of the MenuIterator back to 0.
		 */
		public void reset() {
			index = 0;
		}

	}
	
	/**
	 * Obtain the iterator that will be used to navigate this MenuSubsection
	 * 
	 * @return the MenuIterator for this MenuSubsection
	 */
	public MenuIterator<E> getIterator() {
		return iterator;
	}

	/**
	 * Add the specified element to the MenuSubsection
	 * 
	 * @param _item the item to add
	 */
	public void add(E _item) {
		list.add(_item);
	}

	/**
	 * Remove the specified element from the MenuSubsection. 
	 * 
	 * @param _item the item to remove
	 * 
	 * @return true on a successful deletion; false on a failure
	 */
	public boolean remove(E _item) {
		return list.remove(_item);
	}
	
	/**
	 * Checks if the specified element is inside of the MenuSubsection.
	 * 
	 * @param _item the element to search for
	 * 
	 * @return the index of the found element, or -1 if not found
	 */
	public int contains(E _item) {
		if ( list.contains(_item) ) {
			return list.indexOf(_item);
		}
		else {
			return -1;
		}
	}

}