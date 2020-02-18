package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private Entry<K,V>[] table;
	private int capacity;
	private double load;
	private StringBuilder sb;
	private int VectorSize;
	private int TotalSize;
	
	
	/** Constructs an empty hashmap with the default initial capacity (16)
	and the default load factor (0.75). */
	public SimpleHashMap() {
		capacity = 16;
		load = 0.75;
		table = (Entry<K,V>[]) new Entry[capacity];
		VectorSize = 0;
		TotalSize = 0;
	}
	
	
	/** Constructs an empty hashmap with the specified initial capacity
	and the default load factor (0.75). */
	public SimpleHashMap(int capacity) {
		this.capacity = capacity;
		load = 0.75;
		table = (Entry<K,V>[]) new Entry[capacity];
		VectorSize = 0;
		TotalSize = 0;
	}
	
	public String show() {
		sb = new StringBuilder();
		int i = 0;
		for (Entry e : table) {
			sb.append(i + "   ");
			while (e != null) {
				sb.append(e.toString());
				sb.append(e.getValue() + " ");
				e = e.next;
			}
			sb.append(System.lineSeparator());
			i++;
		}
		return sb.toString();
	}
	

	@Override
	public V get(Object obj) {
		if (TotalSize == 0) {
			return null;
		}
		
		int i = index((K) obj);
		
		if (i < 0) {
			i = i + capacity;
		}
		
		Entry<K,V> e = table[i];
		if (e == null) {
			return null;
		}
		while (!e.getKey().equals(obj)) {
			e = e.next;
		}
		return e.getValue();
	}

	@Override
	public boolean isEmpty() {
		if (TotalSize == 0) {
			return true;
		}
		return false;
	}
	/** index(key) ska returnera det index som ska användas för nyckeln key. */
	private int index(K key) {
		// hasCode() översätter key till ett heltal. % table.length anpassar sedan den till vektorns längd.
		return key.hashCode() % table.length;
	}
	/** find(index, key) ska returnera det Entry-par som har nyckeln key i listan som finns
	på position index i tabellen. Om det inte finns något sådant ska metoden returnera null. */
	private Entry<K,V> find(int index, K key) {
		if (index < table.length && VectorSize>0 && table[index] != null) {
			Entry<K,V> n = table[index] ;
			while (n != null && !n.getKey().equals(key)) {
				n = n.next;
			}
			return n;
		}
		return null;
	}

	@Override
	public V put(K key1, V value1) {
		int i = index(key1);
		
		if (i < 0) {
			i = i + capacity;
		}
		
		Entry<K,V> found = find(i,key1);
		if ( found != null) { // Om den redan finns.
			V oldval = found.getValue();
			found.setValue(value1);
			return oldval;
		}
		Entry<K,V> fresh = new Entry<K,V>(key1,value1);
		if (table[i] == null) { // Om vi lägger in på en tom indexplats. 
			table[i] = fresh;
			VectorSize++;
			TotalSize++;
			int maxload = (int) (capacity*load);
			if ( maxload < VectorSize ) {
				rehash();
			}
			return null;
		}
		
		fresh.next = table[i]; // Om vi lägger till på bredden.
		table[i] = fresh;
		TotalSize++;
		
		return null;
	}

	private void rehash() {
		capacity = capacity*2;
		TotalSize = 0;
		VectorSize = 0;
		Entry<K,V>[] oldTable = table;
		table = (Entry<K,V>[]) new Entry[capacity];
		
		
		
		for (Entry<K,V> e : oldTable) {
			while (e!= null) {
				put(e.getKey(), e.getValue());
				e = e.next;
			}
		}
	}
	
	
	@Override
	public V remove(Object key) {
		int index = index((K) key);
		if (TotalSize == 0 || find(index,(K) key)==null) { // Om listan är tom eller att Key:n inte finns i listan.
			return null;
		}
		Entry<K,V> e = table[index];
		
		if (e.getKey().equals(key)) { // Om key:n är först i listan.
			V removedValue = e.getValue();
			e = e.next;
			if (e==null) {
				VectorSize--;
			}
			TotalSize--;
			return removedValue;
		} 
		while (e.next!= null && !e.next.getKey().equals(key)) { // key finns senare i listan.
			e = e.next;
		}
		V removedValue = e.next.getValue();
		e = e.next.next;
		TotalSize--;
		return removedValue;
		
	}

	@Override
	public int size() {
		return TotalSize;
	}
	
	private static class Entry<K, V> implements Map.Entry<K, V>{
		private K key;
		private V value;
		private Entry<K,V> next;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
			next = null;
		}
		@Override
		public K getKey() {
			return key;
		}
		@Override
		public String toString() {
			StringBuilder sb2 = new StringBuilder();
			sb2.append(key);
			sb2.append("=");
			return sb2.toString();
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			this.value = value;
			return value;
		}

	}

}
