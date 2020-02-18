import java.util.ArrayList;

public class LargestPalindrome {
	ArrayList<Integer> A = new ArrayList<Integer>();
	ArrayList<Integer> numbers = new ArrayList<Integer>();

	public static void main(String[] args) {
		LargestPalindrome a = new LargestPalindrome();
		int b = a.FindLargestPalindrome();
		System.out.print(b);

	}
	public int FindLargestPalindrome() {
		boolean fin = false; 
		
		for (int i = 999; i > 100; i--) {
			for (int j = 999; j >100; j--) {
				int c = i*j;
				numbers.add(c);
			}	
		}
		
			
		while (true) {
				int c = 0;
				for (int h = 0; h<numbers.size(); h++) {
					if (c<)
				}
				int c = numbers.;
				int number = 1;
				while (c > 0) {
					number = c%10; 
					A.add(number);
					c = (c- number)/10; 
				}
				int k = 0;
				boolean b = true;
				while (b) {
					if(k>A.size()/2 +1) {
						fin = true;
						b = false;
					}
					if(A.get(k) != A.get(A.size() - k-1)) {
						b = false;
					}
					k++;
				}
				if(fin == true) {
					return i*j;
				}
				A.clear();
		
	return 0;	
	}}
	
	
//	public int FindLargestPalindrome() {
//		boolean bool = true;
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		
//		
//		for (int i = 999; i > 100; i--) {
//			for (int j = 999; j >100; j--) {
//				int c = i*j;
//				
//			}	
//		}
//		return 0;
//	}
	
	public boolean compare(int left, int right) {
		return left==right;
	}

}
