package src;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class SortedDoubleLinkedListTestGregorianObjects {
	GregorianCalendarComparator comparatorG;
	GregorianCalendar g1,g2,g3,g4, g5;
	SortedDoubleLinkedList<GregorianCalendar> sortedLinkedDate;

	@Before
	public void setUp() throws Exception {
		g1 = new GregorianCalendar(2015, 0,1); // January 1, 2015
		g2 = new GregorianCalendar(2015, 3, 15); // April 15, 2015
		g3 = new GregorianCalendar(2015,5,6); //June 6, 2015
		g4 = new GregorianCalendar(2015,7,13); // August 13, 2015
		g5 = new GregorianCalendar(2015, 8,23); // September 23, 2015
		comparatorG = new GregorianCalendarComparator();
		sortedLinkedDate = new SortedDoubleLinkedList<GregorianCalendar>(comparatorG);
	}

	@After
	public void tearDown() throws Exception {
		comparatorG = null;
		sortedLinkedDate = null;
	}

	@Test
	public void testAddToEnd() {
		try {
			sortedLinkedDate.addToEnd(g1);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddToFront() {
		try {
			sortedLinkedDate.addToFront(g1);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}
	
	@Test
	public void testIteratorSuccessfulCalendar() {
		sortedLinkedDate.add(g2);
		sortedLinkedDate.add(g3);
		sortedLinkedDate.add(g1);
		sortedLinkedDate.add(g4);
		ListIterator<GregorianCalendar> iterator = sortedLinkedDate.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(g1, iterator.next());
		assertEquals(g2, iterator.next());
		assertEquals(g3, iterator.next());
		assertEquals(true, iterator.hasNext());
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionDateNext() {
		sortedLinkedDate.add(g2);
		sortedLinkedDate.add(g3);
		sortedLinkedDate.add(g1);
		sortedLinkedDate.add(g4);
		ListIterator<GregorianCalendar> iterator = sortedLinkedDate.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(g1, iterator.next());
		assertEquals(g2, iterator.next());
		assertEquals(g3, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(g4, iterator.next());
		try{
			//no more elements in list
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionDatePrevious() {
		sortedLinkedDate.add(g2);
		sortedLinkedDate.add(g3);
		sortedLinkedDate.add(g1);
		sortedLinkedDate.add(g4);
		ListIterator<GregorianCalendar> iterator = sortedLinkedDate.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(g1, iterator.next());
		assertEquals(g2, iterator.next());
		assertEquals(g3, iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(g4, iterator.previous());
		assertEquals(g3, iterator.previous());
		assertEquals(g2, iterator.previous());
		assertEquals(g1, iterator.previous());
		try{
			//no more elements in list
			iterator.previous();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}
	
	@Test
	public void testIteratorUnsupportedOperationExceptionString() {
		sortedLinkedDate.add(g2);
		sortedLinkedDate.add(g3);
		sortedLinkedDate.add(g1);
		sortedLinkedDate.add(g4);
		ListIterator<GregorianCalendar> iterator = sortedLinkedDate.iterator();
		try{
			//remove is not supported for the iterator
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}
	
	@Test
	public void testAddDate() {
		sortedLinkedDate.add(g2);
		sortedLinkedDate.add(g5);
		sortedLinkedDate.add(g1);
		assertEquals(g1, sortedLinkedDate.getFirst());
		assertEquals(g5, sortedLinkedDate.getLast());
		sortedLinkedDate.add(g3);
		sortedLinkedDate.add(g4);
		assertEquals(g1, sortedLinkedDate.getFirst());
		assertEquals(g5, sortedLinkedDate.getLast());
		//deletes 5 from linked list
		assertEquals(g5,sortedLinkedDate.retrieveLastElement());
		assertEquals(g4, sortedLinkedDate.getLast());
	}
	
	@Test
	public void testRemoveFirstDate() {
		sortedLinkedDate.add(g2);
		sortedLinkedDate.add(g4);
		assertEquals(g2, sortedLinkedDate.getFirst());
		assertEquals(g4, sortedLinkedDate.getLast());
		sortedLinkedDate.add(g1);
		assertEquals(g1, sortedLinkedDate.getFirst());
		// remove the first
		sortedLinkedDate.remove(g1, comparatorG);
		assertEquals(g2, sortedLinkedDate.getFirst());
	}
	
	@Test
	public void testRemoveEndDate() {
		sortedLinkedDate.add(g2);
		sortedLinkedDate.add(g4);
		sortedLinkedDate.add(g1);
		assertEquals(g1, sortedLinkedDate.getFirst());
		assertEquals(g4, sortedLinkedDate.getLast());
		//remove from the end of the list
		sortedLinkedDate.remove(g4, comparatorG);
		assertEquals(g2, sortedLinkedDate.getLast());
	}
	
	@Test
	public void testRemoveMiddleDate() {
		sortedLinkedDate.add(g2);
		sortedLinkedDate.add(g4);
		sortedLinkedDate.add(g1);
		assertEquals(g1, sortedLinkedDate.getFirst());
		assertEquals(g4, sortedLinkedDate.getLast());
		assertEquals(3, sortedLinkedDate.getSize());
		//remove from middle of list
		sortedLinkedDate.remove(g2, comparatorG);
		assertEquals(g1, sortedLinkedDate.getFirst());
		assertEquals(g4, sortedLinkedDate.getLast());
		assertEquals(2, sortedLinkedDate.getSize());
	}
	
	private class GregorianCalendarComparator implements Comparator<GregorianCalendar>
	{

		@Override
		public int compare(GregorianCalendar arg0, GregorianCalendar arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
}
