package edu.uic.cs342.group2;

/**
 * An interface for a class that is supposed to behave like
 * a container that gets navigated through by an iterator.
 * 
 * @author Group II
 *
 * @param <E> the kind of objects being held by the container
 */
public interface Container<E> {

	/**
	 * Return the iterator that will be used to navigate
	 * through the container.
	 * 
	 * @return the Iterator
	 */
	public Iterator<E> getIterator();

}