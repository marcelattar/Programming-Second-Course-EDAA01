package testqueue;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Iterator;

import queue.FifoQueue;

import org.junit.Test;

public class TestAppendFifoQueue {
private FifoQueue<Integer> q1;
private FifoQueue<Integer> q2;

	@Before
	public void setUp() throws Exception {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
		
	}

	@After
	public void tearDown() throws Exception {
		q1 = null;
		q2 = null;
	}
	
	@Test
	public void testTwoNonEmptyQueues() {
		int[] order = {1,2,3,10,9,8};
		q1.offer(1);
		q1.offer(2);
		q1.offer(3);
		q2.offer(10);
		q2.offer(9);
		q2.offer(8);
		q1.append(q2);
		q1.iterator();
		Iterator<Integer> itr = q1.iterator();
		int i = 0;
		while (itr.hasNext()) {
			int temp = itr.next();
			assertEquals("returns incorrect element", temp , order[i]);
			i++;
		}
	}
	
	@Test 
	public void testNonEmptyQueuTooEmptyQueue() {
		q1.offer(1);
		q1.offer(2);
		q1.append(q2);
		assertTrue("Tests if the size is the same after adding a empty list.",q1.size() == 2);
		assertTrue(q1.peek() == 1);
	}
	@Test
	public void testEmptyQueueToNonEmpty() {
		q1.offer(1);
		q1.offer(2);
		q2.append(q1);
		assertTrue(q2.size() == 2);
	}
	@Test
	public void testAppendToItself() {
		q1.offer(1);
		q1.offer(2);
		
		try {
			q1.append(q1);
				fail("It should throw IllegalArgumentException().");
		} catch (IllegalArgumentException e) {
			
		}
	}
	
	@Test
	public void testBothEmpty() {
		q1.append(q2);
		assertTrue("Size should be zero",q1.size() == 0);
		assertTrue("The queue should be null",q1.peek() == null);
	}
	
}

