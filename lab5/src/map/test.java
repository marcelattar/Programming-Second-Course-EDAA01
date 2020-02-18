package map;

public class test {

	public static void main(String[] args) {
		SimpleHashMap<String, Integer> map1 = new SimpleHashMap<String,Integer>();
		SimpleHashMap<Integer, Integer> map2 = new SimpleHashMap<Integer,Integer>(10);
		
//		map1.put(2, 1);
//		map1.put(1, 2);
//		map1.put(5, 3);
//		map1.put(17, 4);
//		map1.put(11, 3);
		
		map1.put("a", 1);
		map1.put("b", 2);
		map1.put("c", 3);
		map1.put("a", 4);
		
		System.out.println(map1.show());
		map1.get(new String("a"));
		

	}

}
