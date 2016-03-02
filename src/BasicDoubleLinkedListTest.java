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

/**
 *
 */

/**
 * @author Uzoma Uwanamodo
 *
 */
public class BasicDoubleLinkedListTest {
	BasicDoubleLinkedList<String> linkedString;
	BasicDoubleLinkedList<Double> linkedDouble;
	StringComparator comparator;
	DoubleComparator comparatorD;
	Random random;

	@Before
	public void setUp() throws Exception {
		linkedString = new BasicDoubleLinkedList<String>();
		linkedString.addToEnd("Hello");
		linkedString.addToEnd("World");
		comparator = new StringComparator();

		// STUDENT: Use the linkedDouble for the STUDENT tests
		linkedDouble = new BasicDoubleLinkedList<Double>();
		linkedDouble.addToEnd(15.0);
		linkedDouble.addToEnd(100.0);
		for (int i = 0; i < 20; i++)
			if (i != 15)
				linkedDouble.addToEnd((double) i);
		random = new Random();
		comparatorD = new DoubleComparator();

	}

	@After
	public void tearDown() throws Exception {
		linkedString = null;
		comparator = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(2, linkedString.getSize());
		assertEquals(21, linkedDouble.getSize());
	}

	@Test
	public void testAddToEnd() {
		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("End");
		assertEquals("End", linkedString.getLast());
		linkedString.addToEnd("Apocalypse");
		assertEquals("Apocalypse", linkedString.getLast());
	}

	@Test
	public void testAddToEndSTUDENT() {
		for (int i = 0; i > 50; i++) {
			linkedDouble.addToEnd((double) i);
			assertEquals((double) i, linkedDouble.getLast(), .5);
		}
	}

	@Test
	public void testAddToFront() {
		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("Begin");
		assertEquals("Begin", linkedString.getFirst());
	}

	@Test
	public void testAddToFrontSTUDENT() {
		assertTrue(linkedDouble.getFirst().equals(15.0));
		linkedDouble.addToFront((269.2));
		assertTrue(linkedDouble.getFirst().equals(269.2));
	}

	@Test
	public void testGetFirst() {
		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("New");
		assertEquals("New", linkedString.getFirst());
	}

	@Test
	public void testGetFirstSTUDENT() {
		assertTrue(linkedDouble.getFirst().equals(15.0));
		linkedDouble.addToFront(50.0);
		assertTrue(linkedDouble.getFirst().equals(50.0));
	}

	@Test
	public void testGetLast() {
		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("New");
		assertEquals("New", linkedString.getLast());
	}

	@Test
	public void testGetLastSTUDENT() {
		while (linkedDouble.getSize() > 0)
			assertEquals(linkedDouble.getLast(), linkedDouble.retrieveLastElement());
	}

	@Test
	public void testToArrayList() {
		ArrayList<String> list;
		linkedString.addToFront("Begin");
		linkedString.addToEnd("End");
		list = linkedString.toArrayList();
		assertEquals("Begin", list.get(0));
		assertEquals("Hello", list.get(1));
		assertEquals("World", list.get(2));
		assertEquals("End", list.get(3));
	}

	@Test
	public void testToArraySTUDENT() {
		linkedDouble.addToFront(4.0);
		linkedDouble.addToFront(20.0);
		linkedDouble.addToEnd(4.20);
		ArrayList<Double> doubles = linkedDouble.toArrayList();
		assertTrue(doubles.get(0).equals(20.0));
		assertTrue(doubles.get(1).equals(4.0));
		assertTrue(doubles.get(2).equals(15.0));
		assertTrue(doubles.get(3).equals(100.0));
	}

	@Test
	public void testIteratorSuccessfulNext() {
		linkedString.addToFront("Begin");
		linkedString.addToFront("Big Bang");
		linkedString.addToEnd("End");
		linkedString.addToEnd("Apocalypse");
		ListIterator<String> iterator = linkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Big Bang", iterator.next());
		assertEquals("Begin", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals("End", iterator.next());
	}

	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedString.addToFront("Begin");
		// linkedString.addToFront("Big Bang");
		linkedString.addToEnd("End");
		ListIterator<String> iterator = linkedString.iterator();

		assertEquals(true, iterator.hasNext());
		assertEquals("Begin", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals("End", iterator.previous());
		assertEquals("World", iterator.previous());
		assertEquals("Hello", iterator.previous());
	}

	@Test
	public void testIteratorSuccessfulSTUDENT() {
		ListIterator<Double> iterator = linkedDouble.iterator();
		ArrayList<Double> arrayList = linkedDouble.toArrayList();
		int i = 0;
		while (iterator.hasNext()) {
			Double next = iterator.next();
			assertEquals(arrayList.get(i++), next);
		}
		while (iterator.hasPrevious()) {
			Double previous = iterator.previous();
			assertEquals(arrayList.get(i--), previous);
		}
	}

	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedString.addToFront("Begin");
		linkedString.addToEnd("End");
		ListIterator<String> iterator = linkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Begin", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals("End", iterator.next());
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
	public void testIteratorNoSuchElementExceptionPrevious() {
		linkedString.addToFront("Begin");
		linkedString.addToEnd("End");
		ListIterator<String> iterator = linkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Begin", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals("End", iterator.previous());
		assertEquals("World", iterator.previous());
		assertEquals("Hello", iterator.previous());
		assertEquals("Begin", iterator.previous());
		try {
			// no more elements in list
			iterator.previous();
			assertTrue("Did not throw a NoSuchElementException", false);
		} catch (NoSuchElementException e) {
			assertTrue("Successfully threw a NoSuchElementException", true);
		} catch (Exception e) {
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}

	}

	@Test
	public void testIteratorNoSuchElementExceptionSTUDENT() {
		for (int i = 0; i < 30; i++)
			linkedDouble.addToEnd((double) i);
		ListIterator<Double> iterator = linkedDouble.iterator();
		while (iterator.hasNext())
			assertTrue(!iterator.next().equals(null));
		try {
			assertEquals(iterator.next(), null);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			assertTrue("Successfully threw a NoSuchElementException", true);
		}

	}

	@Test
	public void testIteratorUnsupportedOperationException() {
		linkedString.addToFront("Begin");
		linkedString.addToEnd("End");
		ListIterator<String> iterator = linkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Begin", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals("End", iterator.next());

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
	public void testIteratorUnsupportedOperationExceptionSTUDENT() {
		ListIterator<Double> iterator = linkedDouble.iterator();
		iterator.next();
		try {
			iterator.remove();
		} catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw a UnsupportedOperationException", true);
		}
		try {
			iterator.add(5.0);
		} catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw a UnsupportedOperationException", true);
		}
		try {
			iterator.nextIndex();
		} catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw a UnsupportedOperationException", true);
		}
		try {
			iterator.previousIndex();
		} catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw a UnsupportedOperationException", true);
		}
		try {
			iterator.set(5.8);
		} catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw a UnsupportedOperationException", true);
		}
	}

	@Test
	public void testRemove() {
		// remove the first
		assertEquals("Hello", linkedString.getFirst());
		assertEquals("World", linkedString.getLast());
		linkedString.addToFront("New");
		assertEquals("New", linkedString.getFirst());
		linkedString.remove("New", comparator);
		assertEquals("Hello", linkedString.getFirst());
		// remove from the end of the list
		linkedString.addToEnd("End");

		assertEquals("End", linkedString.getLast());
		linkedString.remove("End", comparator);
		assertEquals("World", linkedString.getLast());
		// remove from middle of list
		linkedString.addToFront("Begin");
		assertEquals("Begin", linkedString.getFirst());
		assertEquals("World", linkedString.getLast());
		linkedString.remove("Hello", comparator);
		assertEquals("Begin", linkedString.getFirst());
		assertEquals("World", linkedString.getLast());

	}

	@Test
	public void testRemoveSTUDENT() {
		while (linkedDouble.getSize() > 0) {
			Double double1 = linkedDouble.toArrayList().get(random.nextInt(linkedDouble.getSize()));
			linkedDouble.remove(double1, comparatorD);
			assertFalse(linkedDouble.toArrayList().contains(double1));
		}
		// }
	}

	@Test
	public void testRetrieveFirstElement() {
		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("New");
		assertEquals("New", linkedString.getFirst());
		assertEquals("New", linkedString.retrieveFirstElement());
		assertEquals("Hello", linkedString.getFirst());
		assertEquals("Hello", linkedString.retrieveFirstElement());
		assertEquals("World", linkedString.getFirst());

	}

	@Test
	public void testRetrieveFirstElementSTUDENT() {
		assertEquals(15.0, linkedDouble.getFirst(), .5);
		linkedDouble.retrieveFirstElement();
		assertEquals(100.0, linkedDouble.getFirst(), .5);
		linkedDouble.retrieveFirstElement();
	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("New");
		assertEquals("New", linkedString.getLast());
		assertEquals("New", linkedString.retrieveLastElement());
		assertEquals("World", linkedString.getLast());
	}

	@Test
	public void testRetrieveLastElementSTUDENT() {
		linkedDouble.addToEnd(12.63);
		assertTrue(linkedDouble.getLast().equals(12.63));

	}

	class StringComparator implements Comparator<String> {

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}

	}

	class DoubleComparator implements Comparator<Double> {

		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}

	}
}
