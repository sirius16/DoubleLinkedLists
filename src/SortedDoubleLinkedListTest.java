package src;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortedDoubleLinkedListTest {
	SortedDoubleLinkedList<String> sortedLinkedString;
	StringComparator comparator;
	DoubleComparator comparatorD;
	SortedDoubleLinkedList<Double> sortedLinkedDouble;
	ListIterator<Double> iterator;
	Random random;

	@Before
	public void setUp() throws Exception {
		comparator = new StringComparator();
		sortedLinkedString = new SortedDoubleLinkedList<String>(comparator);

		// STUDENT - use the SortedDoubleLinkedList<Double> for your STUDENT
		// tests
		comparatorD = new DoubleComparator();
		sortedLinkedDouble = new SortedDoubleLinkedList<Double>(comparatorD);

		random = new Random();
		for (int i = 0; i < 20; i++)
			sortedLinkedDouble.add(random.nextDouble());
		iterator = sortedLinkedDouble.iterator();
	}

	@After
	public void tearDown() throws Exception {
		comparator = null;
		sortedLinkedString = null;
	}

	@Test
	public void testAddToEnd() {
		try {
			sortedLinkedString.addToEnd("Hello");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		} catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		} catch (Exception e) {
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddToFront() {
		try {
			sortedLinkedString.addToFront("Hello");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		} catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		} catch (Exception e) {
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testIteratorSuccessfulStringNext() {
		sortedLinkedString.add("Hello");
		sortedLinkedString.add("World");
		sortedLinkedString.add("Begin");
		sortedLinkedString.add("Zebra");
		ListIterator<String> iterator = sortedLinkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Begin", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals(true, iterator.hasNext());
	}

	@Test
	public void testIteratorSuccessfulStringPrevious() {
		sortedLinkedString.add("Hello");
		sortedLinkedString.add("World");
		sortedLinkedString.add("Begin");
		sortedLinkedString.add("Zebra");
		ListIterator<String> iterator = sortedLinkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Begin", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals("Zebra", iterator.previous());
		assertEquals("World", iterator.previous());
		assertEquals("Hello", iterator.previous());
	}

	@Test
	public void testIteratorSuccessfulDoubleNext() {
		ArrayList<Double> arrayList = sortedLinkedDouble.toArrayList();
		int i = 0;
		while (iterator.hasNext())
			assertEquals(iterator.next(), arrayList.get(i++));
		testIteratorSuccessfulDoublePrevious();
	}

	@Test
	public void testIteratorSuccessfulDoublePrevious() {
		ArrayList<Double> arrayList = sortedLinkedDouble.toArrayList();
		int i = 0;
		while (iterator.hasNext())
			assertEquals(iterator.next(), arrayList.get(i++));
		while (iterator.hasPrevious())
			assertEquals(iterator.previous(), arrayList.get(i--));
	}

	@Test
	public void testIteratorNoSuchElementExceptionString() {
		sortedLinkedString.add("Hello");
		sortedLinkedString.add("World");
		sortedLinkedString.add("Begin");
		sortedLinkedString.add("Zebra");
		ListIterator<String> iterator = sortedLinkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Begin", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals("Zebra", iterator.next());
		try {
			// no more elements in list
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException", false);
		} catch (NoSuchElementException e) {
			assertTrue("Successfully threw a NoSuchElementException", true);
		} catch (Exception e) {
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}

	@Test
	public void testIteratorNoSuchElementExceptionDouble() {
		while (iterator.hasNext())
			iterator.next();
		try {
			iterator.next();
		} catch (NoSuchElementException e) {
			assertTrue("Successfully threw a NoSuchElementException", true);
		}
	}

	@Test
	public void testIteratorUnsupportedOperationExceptionString() {
		sortedLinkedString.add("Hello");
		sortedLinkedString.add("World");
		sortedLinkedString.add("Begin");
		sortedLinkedString.add("Zebra");
		ListIterator<String> iterator = sortedLinkedString.iterator();
		try {
			// remove is not supported for the iterator
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException", false);
		} catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw a UnsupportedOperationException", true);
		} catch (Exception e) {
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testIteratorUnsupportedOperationExceptionDouble() {
		try {
			sortedLinkedDouble.addToEnd(96.5);
		} catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw a UnsupportedOperationException", true);
		}
		try {
			sortedLinkedDouble.addToFront(84.2);
		} catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw a UnsupportedOperationException", true);
		}

	}

	@Test
	public void testAddString() {
		sortedLinkedString.add("Banana");
		sortedLinkedString.add("Elephant");
		sortedLinkedString.add("Apple");
		System.out.println(sortedLinkedString.getSize());
		System.out.println(sortedLinkedString.toArrayList());
		assertEquals("Apple", sortedLinkedString.getFirst());
		assertEquals("Elephant", sortedLinkedString.getLast());
		sortedLinkedString.add("Cat");
		sortedLinkedString.add("Dog");
		System.out.println(sortedLinkedString.getSize());
		System.out.println(sortedLinkedString.toArrayList());
		assertEquals("Apple", sortedLinkedString.getFirst());
		assertEquals("Elephant", sortedLinkedString.getLast());
		// deletes Elephant from linked list
		assertEquals("Elephant", sortedLinkedString.retrieveLastElement());
		assertEquals("Dog", sortedLinkedString.getLast());
	}

	@Test
	public void testAddDouble() {
		sortedLinkedDouble.add(-5.73);
		assertEquals(-5.73, sortedLinkedDouble.getFirst(), .1);
		sortedLinkedDouble.add(32.2);
		assertEquals(32.2, sortedLinkedDouble.getLast(), .1);
		sortedLinkedDouble.add(15.2);
		assertEquals(32.2, sortedLinkedDouble.getLast(), .1);
		assertEquals(-5.73, sortedLinkedDouble.getFirst(), .1);
	}

	@Test
	public void testRemoveFirstString() {
		sortedLinkedString.add("Hello");
		sortedLinkedString.add("World");
		assertEquals("Hello", sortedLinkedString.getFirst());
		assertEquals("World", sortedLinkedString.getLast());
		sortedLinkedString.add("Begin");
		assertEquals("Begin", sortedLinkedString.getFirst());
		// remove the first
		sortedLinkedString.remove("Begin", comparator);
		assertEquals("Hello", sortedLinkedString.getFirst());
	}

	@Test
	public void testRemoveFirstDouble() {
		while (sortedLinkedDouble.getSize() > 0)
			assertEquals(sortedLinkedDouble.getFirst(), sortedLinkedDouble.retrieveFirstElement());
	}

	@Test
	public void testRemoveEndString() {
		sortedLinkedString.add("Hello");
		sortedLinkedString.add("World");
		assertEquals("Hello", sortedLinkedString.getFirst());
		assertEquals("World", sortedLinkedString.getLast());
		sortedLinkedString.add("Zebra");
		assertEquals("Zebra", sortedLinkedString.getLast());
		// remove from the end of the list
		sortedLinkedString.remove("Zebra", comparator);
		assertEquals("World", sortedLinkedString.getLast());
	}

	@Test
	public void testRemoveEndDouble() {
		while (sortedLinkedDouble.getSize() > 0)
			assertEquals(sortedLinkedDouble.getLast(), sortedLinkedDouble.retrieveLastElement());
	}

	@Test
	public void testRemoveMiddleString() {
		sortedLinkedString.add("Hello");
		sortedLinkedString.add("World");
		assertEquals("Hello", sortedLinkedString.getFirst());
		assertEquals("World", sortedLinkedString.getLast());
		sortedLinkedString.add("Begin");
		assertEquals("Begin", sortedLinkedString.getFirst());
		assertEquals("World", sortedLinkedString.getLast());
		assertEquals(3, sortedLinkedString.getSize());
		// remove from middle of list
		sortedLinkedString.remove("Hello", comparator);
		assertEquals("Begin", sortedLinkedString.getFirst());
		assertEquals("World", sortedLinkedString.getLast());
		assertEquals(2, sortedLinkedString.getSize());
	}

	@Test
	public void testRemoveMiddleDouble() {
		while (sortedLinkedDouble.getSize() > 0) {
			int nextInt = random.nextInt(sortedLinkedDouble.getSize());
			Double double1 = sortedLinkedDouble.toArrayList().get(nextInt);
			sortedLinkedDouble.remove(double1, comparatorD);
			assertFalse(sortedLinkedDouble.toArrayList().contains(double1));
		}

	}

	private class StringComparator implements Comparator<String> {

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}

	}

	private class DoubleComparator implements Comparator<Double> {

		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}

	}
}
