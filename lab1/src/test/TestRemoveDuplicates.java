package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import set.RemoveDuplicates;
import set.ArraySet; 
import set.MaxSet;

public class TestRemoveDuplicates {
private RemoveDuplicates rd;

	@Before
	public void setUp() throws Exception {
		rd = new RemoveDuplicates();
	}

	@After
	public void tearDown() throws Exception {
		rd = null;
	}
	
	@Test
	public final void testNullPointer() {
		try {
			int[] i = rd.uniqueElements(null);
			fail("uniqueElements should catch NullPointException");
		} catch (NullPointerException e) {
		}
	}
	@Test
	public final void testEmptySet() {
		int[] i = new int[10];
		int[] b = rd.uniqueElements(i);
		assertEquals("Wrong size():", 1, b.length);
	}
	
	@Test
	public final void testDuplicates() {
		int[] i = new int[3];
		i[0] = 2;
		i[1] = 2;
		i[2] = 1;
		int[] b = rd.uniqueElements(i);
		assertEquals("Check if duplicates are removed.", 2, b.length);
	}
	
	@Test
	public final void testManyInDisorder() {
		int[] i = new int[1000];
		int[] b = new int[1000];
		for (int a = 0; a<1000; a++) {
			i[999-a] =a;
			b[a] = a;
		}
		assertArrayEquals("", rd.uniqueElements(i), b);	
	}
	
	@Test
	public final void testNegatives() {
		int[] i = new int[5];
		i[0] = 1;
		i[1] = -2;
		i[2] = -5;
		i[3] = -5;
		i[4] = -5;
		assertEquals("Checks if the vector 'i' is in order.", -5 ,rd.uniqueElements(i)[0]);
		assertEquals("Checks if the vector 'i' is in order.", -2 ,rd.uniqueElements(i)[1]);	
		assertEquals("Checks if the vector 'i' is in order.", 1 ,rd.uniqueElements(i)[2]);	
		assertEquals("Tests if duplicates have been removed.", 3 ,rd.uniqueElements(i).length);	
	}
	
}
