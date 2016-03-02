package src;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BasicDoubleLinkedListTestDogObjects {
	BasicDoubleLinkedList<Dog> linkedDog;
	DogComparator dogComparator;
	Dog dog1, dog2, dog3;
	@Before
	public void setUp() throws Exception {
		dog1 = new Dog("Carl","Sport",6);
		dog2 = new Dog("Betsy","Annabel",2);
		dog3 = new Dog("Adam","Fifi", 5);
		
		linkedDog = new BasicDoubleLinkedList<Dog>();
		linkedDog.addToEnd(new Dog("Adam","Fifi", 5));
		linkedDog.addToEnd(new Dog("Zach", "Brute",4));
		dogComparator = new DogComparator();
	}

	@After
	public void tearDown() throws Exception {
		linkedDog = null;
		dogComparator = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(2,linkedDog.getSize());
		linkedDog.addToEnd(new Dog("Betsy","Annabel",2));
		assertEquals(3,linkedDog.getSize());
	}
	
	@Test
	public void testAddToEnd() {
		assertEquals("Zach", linkedDog.getLast().getOwnerName());
		linkedDog.addToEnd(new Dog("Betsy","Annabel",2));
		assertEquals("Betsy", linkedDog.getLast().getOwnerName());
	}

	@Test
	public void testAddToFront() {
		assertEquals("Adam", linkedDog.getFirst().getOwnerName());
		linkedDog.addToFront(new Dog("Carl","Sport",6));
		assertEquals("Carl", linkedDog.getFirst().getOwnerName());
	}

	@Test
	public void testGetFirst() {
		assertEquals("Adam", linkedDog.getFirst().getOwnerName());
		linkedDog.addToFront(new Dog("Carl","Sport",6));
		assertEquals("Carl", linkedDog.getFirst().getOwnerName());
	}
	
	@Test
	public void testGetLast() {
		assertEquals("Zach", linkedDog.getLast().getOwnerName());
		linkedDog.addToEnd(new Dog("Betsy","Annabel",2));
		assertEquals("Betsy", linkedDog.getLast().getOwnerName());
	}

	@Test
	public void testToArrayList()
	{
		ArrayList<Dog> list;
		linkedDog.addToFront(new Dog("Carl","Sport",6));
		linkedDog.addToEnd(new Dog("Betsy","Annabel",2));
		list = linkedDog.toArrayList();
		assertEquals("Carl", list.get(0).getOwnerName());
		assertEquals("Adam", list.get(1).getOwnerName());
		assertEquals("Zach", list.get(2).getOwnerName());
		assertEquals("Betsy", list.get(3).getOwnerName());
	}
	
	@Test
	public void testIteratorSuccessfulNext() {
		linkedDog.addToFront(new Dog("Carl","Sport",6));
		linkedDog.addToEnd(new Dog("Betsy","Annabel",2));
		ListIterator<Dog> iterator = linkedDog.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Carl", iterator.next().getOwnerName());
		assertEquals("Adam", iterator.next().getOwnerName());
		assertEquals("Zach", iterator.next().getOwnerName());
		assertEquals(true, iterator.hasNext());
		assertEquals("Betsy", iterator.next().getOwnerName());
	}
	
	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedDog.addToFront(new Dog("Carl","Sport",6));
		linkedDog.addToEnd(new Dog("Betsy","Annabel",2));
		ListIterator<Dog> iterator = linkedDog.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Carl", iterator.next().getOwnerName());
		assertEquals("Adam", iterator.next().getOwnerName());
		assertEquals(true, iterator.hasPrevious());
		assertEquals("Zach", iterator.previous().getOwnerName());
		assertEquals("Adam", iterator.previous().getOwnerName());
		assertEquals("Carl", iterator.previous().getOwnerName());
		assertEquals(false, iterator.hasPrevious());
	}
	
	@Test
	public void testIteratorNoSuchElementException() {
		linkedDog.addToFront(new Dog("Carl","Sport",6));
		linkedDog.addToEnd(new Dog("Betsy","Annabel",2));
		ListIterator<Dog> iterator = linkedDog.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Carl", iterator.next().getOwnerName());
		assertEquals("Adam", iterator.next().getOwnerName());
		assertEquals("Zach", iterator.next().getOwnerName());
		assertEquals(true, iterator.hasNext());
		assertEquals("Betsy", iterator.next().getOwnerName());
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
	public void testIteratorUnsupportedOperationException() {
		linkedDog.addToFront(new Dog("Carl","Sport",6));
		linkedDog.addToEnd(new Dog("Betsy","Annabel",2));
		ListIterator<Dog> iterator = linkedDog.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Carl", iterator.next().getOwnerName());
		assertEquals("Adam", iterator.next().getOwnerName());
		assertEquals("Zach", iterator.next().getOwnerName());
		assertEquals(true, iterator.hasNext());
		assertEquals("Betsy", iterator.next().getOwnerName());
		
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
	public void testRemove() {
		// remove the first
		assertEquals("Adam", linkedDog.getFirst().getOwnerName());
		assertEquals("Zach", linkedDog.getLast().getOwnerName());
		linkedDog.addToFront(new Dog("Carl","Sport",6));
		assertEquals("Carl", linkedDog.getFirst().getOwnerName());
		System.out.println(linkedDog.toArrayList());
		linkedDog.remove(dog1, dogComparator);
		System.out.println(linkedDog.toArrayList());
		assertEquals("Adam", linkedDog.getFirst().getOwnerName());
		
		//remove from the end of the list
		linkedDog.addToEnd(new Dog("Betsy","Annabel",2));
		assertEquals("Betsy", linkedDog.getLast().getOwnerName());
		linkedDog.remove(dog2, dogComparator);
		assertEquals("Zach", linkedDog.getLast().getOwnerName());
		//remove from middle of list
		linkedDog.addToFront(new Dog("Carl","Sport",6));
		assertEquals("Carl", linkedDog.getFirst().getOwnerName());
		assertEquals("Zach", linkedDog.getLast().getOwnerName());
		linkedDog.remove(dog3, dogComparator);
		assertEquals("Carl", linkedDog.getFirst().getOwnerName());
		assertEquals("Zach", linkedDog.getLast().getOwnerName());
		
	}
	
	@Test
	public void testRetrieveFirstElement() {
		assertEquals("Adam", linkedDog.getFirst().getOwnerName());
		linkedDog.addToFront(new Dog("Carl","Sport",6));
		assertEquals("Carl", linkedDog.getFirst().getOwnerName());
		assertEquals("Carl", linkedDog.retrieveFirstElement().getOwnerName());
		assertEquals("Adam",linkedDog.getFirst().getOwnerName());
		assertEquals("Adam", linkedDog.retrieveFirstElement().getOwnerName());
		assertEquals("Zach",linkedDog.getFirst().getOwnerName());
		
	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals("Zach", linkedDog.getLast().getOwnerName());
		linkedDog.addToEnd(new Dog("Betsy","Annabel",2));
		assertEquals("Betsy", linkedDog.getLast().getOwnerName());
		assertEquals("Betsy", linkedDog.retrieveLastElement().getOwnerName());
		assertEquals("Zach",linkedDog.getLast().getOwnerName());
	}

	private class Dog{
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Dog [" + (owner != null ? "owner=" + owner : "") + "]";
		}

		String owner;
		public Dog(String o, String dn, int a){
			owner = o;
		}
		
		public String getOwnerName(){
			return owner;
		}
	}
	
	private class DogComparator implements Comparator<Dog>
	{

		@Override
		public int compare(Dog arg0, Dog arg1) {
			return (arg0.owner).compareTo(arg1.owner);
		}
		
	}
}
