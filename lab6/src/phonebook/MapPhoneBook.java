package phonebook;


import java.io.Serializable;
import java.util.*;
import java.util.AbstractSet;


public class MapPhoneBook implements PhoneBook,Serializable {
private Map<String, Set<String>> book;
	
	public MapPhoneBook() {
		book = new TreeMap<String, Set<String>>();
	}
	
	@Override
	/** 
	 * Associates the specified number with the specified 
	 * name in this phone book. 
	 * post: If the specified name is not present in this phone book,
	 *        the specified name is added and associated  with
	 *  	  the specified number. Otherwise the specified 
	 *  	  number is added to the set of number associated with name.
	 * @param name The name for which a phone number is to be added
	 * @param number The number associated with the specified name
	 * @return true if the specified name and number was inserted
	 */
	public boolean put(String name, String number) {
		if (!book.containsKey(name)) { //Om namnet inte finns sedan innan.
			Set<String> numberSet = new TreeSet<String>();
			numberSet.add(number);
			book.put(name, numberSet);
			return true;
		}
		if (book.containsKey(name) && !book.get(name).contains(number)) {
			book.get(name).add(number); // Om namnet finns, lägg till numret till Set:et.
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(String name) {
		if (book.containsKey(name)) {
			book.remove(name);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeNumber(String name, String number) {
		if (book.containsKey(name)) {
			return book.get(name).remove(number);
		}
		return false;
	}

	@Override
	/**
	 * Retrieves a set of phone numbers for the specified name. If the 
	 * specified name is not present in this phone book an empty set is 
	 * returned.
	 * @param name The name whose associated phone numbers are to be returned  
	 * @return The phone numbers associated with the specified name
	 */
	public Set<String> findNumbers(String name) {
		Set<String> returnSet = new TreeSet<String>();
		Set<String> set = book.get(name);
		if (set == null) {
			return returnSet;
		}
		returnSet.addAll(set);
		return returnSet;
	}

	@Override
	/**
	 * Retrieves a set of names associated with the specified phone number. 
	 * If the specified number is not present in this phone book an empty 
	 * set is returned.
	 * @param number The number for which the set of associated
	 * names is to be returned.
	 * @return The names associated with the specified number
	 */
	public Set<String> findNames(String number) {
		Set<String> returnSet = new TreeSet<String>();
//		if (book.isEmpty() || !book.containsValue(number)) { WTF IS GOING ON?
//			return returnSet;
//		}
		Set<String> names = names();
		Iterator itr = names.iterator();
		while(itr.hasNext()) {
			String testName = (String) itr.next();
			if (book.get(testName).contains(number)) {
				returnSet.add(testName);
			}
		}
		return returnSet;
	}
	
	public Set<String> findPerson(String letters) {
		Set<String> returnSet = new TreeSet<String>();
		
		Set<String> names = names();
		Iterator itr = names.iterator();
		while (itr.hasNext()) {
			String testName = (String) itr.next();
			if (testName.contains(letters)) {
				returnSet.add(testName);
			}
		}
		
		return returnSet;
	}

	@Override
	public Set<String> names() {
		Set<String> names = new TreeSet<String>(book.keySet());
		return names;
	}
	

	@Override
	public int size() {
		return book.size();
	}

}
