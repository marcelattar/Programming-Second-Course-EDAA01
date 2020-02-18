package holgersson;
import java.util.ArrayList;
import java.util.Scanner;

public class LocationTable {
private ArrayList<WordCount> LocationTable;


	/** Skapar en tabell av platser och deras antal. Parametern locations anger
	vilka ord (platser) som ska räknas. Orden i locations förutsätts vara unika. */
	public LocationTable(String[] locations) {
		LocationTable = new ArrayList<WordCount>();
		for (String w: locations) {
			LocationTable.add(new WordCount(w));
		}
	}
	
	
	
	/** Undersöker ordet w. Om ordet förekommer i tabellen ökas ordets antal med 1. */
	public void inspectWord(String w) {
		for (WordCount e: LocationTable) {
			if (w.compareTo(e.getWord()) == 0) {
				e.incCount();
				break;
			}
		}
	}
	

	/** Läser text från en Scanner, ord för ord. Om ett läst ord
	förekommer i tabellen ökas dess antal med 1. */
	public void readText(Scanner textScanner) {
		while(textScanner.hasNext()) {
			String word = textScanner.next();
			
			inspectWord(word);
		}
	}
	
	/** Skriver ut de 5 vanligast förekommande platserna, angivna i fallande
	ordning efter förekomst. För varje plats anges antalet förekomster.
	Om tabellen innehåller färre än 5 platser så skrivs alla ut. */
	public void printTop5() {
		ArrayList<WordCount> sorted = new ArrayList<WordCount>();
		for (WordCount w: LocationTable) {
			int i = 0;
			while (i < sorted.size() && w.getCount() < sorted.get(i).getCount()) {
				i++;
			} sorted.add(i,w);
		}
		for (int i = 0; i < 5 && i < sorted.size(); i++)
			System.out.println(sorted.get(i).toString());
	}
	
}
