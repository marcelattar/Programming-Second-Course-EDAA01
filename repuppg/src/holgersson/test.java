package holgersson;

public class test {

	public static void main(String[] args) {
		WordCount w = new WordCount("Södermanland");
		w.incCount();
		w.incCount();
		System.out.println(w.toString());

	}

}
