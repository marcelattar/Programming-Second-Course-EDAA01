package queue;

import java.util.Iterator;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FifoQueue<Integer> myIntQueue = new FifoQueue<Integer>();
		myIntQueue.offer(1);
		myIntQueue.offer(2);
		myIntQueue.offer(3);
		myIntQueue.offer(4);
		Iterator<Integer> itr = myIntQueue.iterator();
		int i = 0;
		int temp = 0;
		while(itr.hasNext()) {
			temp = itr.next();
			i++;
			System.out.println(i);
		}
		System.out.println(i);
		
		
	}

}
