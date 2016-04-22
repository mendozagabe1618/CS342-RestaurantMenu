package edu.uic.cs342.group2;

/**
 * The interface for a class that shall behave like
 * an iterator.
 * 
 * @author Group II
 *
 * @param <E> the type of objects to hold
 */
public interface Iterator<E> {

	/**
	 * Return true if a next element is available, return false
	 * if not.
	 * 
	 * @return true if there is another element; else false
	 */
	public boolean hasNext();

	/**
	 * Return the next element or null if there is none.
	 * 
	 * @return the next element; else null
	 */
	public E next();

}