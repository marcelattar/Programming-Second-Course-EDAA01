package holgersson;

public class WordCount {
private int WordCount;
private String word;
	
	/** Skapar ett objekt som håller reda på förekomster av ordet word.
	Inledningsvis har ordet 0 (noll) förekomster. */
	public WordCount(String word) {
		WordCount = 0;
		this.word = word;
	}
	
	/** Tar reda på vilket ord som räknas. */
	public String getWord() {
		return word;
	}
	
	
	/** Ökar antalet förekomster med 1. */
	public void incCount() {
		WordCount++;
	}
	
	
	/** Tar reda på antalet förekomster. */
	public int getCount() {
		return WordCount;
	}
	
	
	/** Returnerar en sträng som beskriver ordet och dess förekomster,
	på formen "xyzzy: 57" (om ordet "xyzzy" förekommit 57 gånger) */
	public String toString() {
		return word + ": " + WordCount;
	}
	
}
