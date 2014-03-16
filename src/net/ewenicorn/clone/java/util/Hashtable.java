package net.ewenicorn.clone.java.util;

/**
 * @author jmagas <jen.r.magas@gmail.com>
 * 
 * @see http://docs.oracle.com/javase/7/docs/api/java/util/Hashtable.html
 * 
 */

public class Hashtable<K, V> {

	/**
	 * The first step is to build containers for the key-value pairs being
	 * stored.
	 */
	class Entry<M, N> {
		private M key;
		private N value;

		public Entry(M key, N value) {
			setKey(key);
			setValue(value);
		}

		public void setKey(M key) {
			this.key = key;
		}

		public M getKey() {
			return key;
		}

		public void setValue(N value) {
			this.value = value;
		}

		public N getValue() {
			return value;
		}

		/**
		 * We need to be sure to have an equals implementation so that we can
		 * easily compare in case we get multiples of the same thing.
		 */
		@Override
		public boolean equals(Object other) {
			boolean isEqual = false;

			/** Can't be equal if they aren't the same class! */
			if (other.getClass().equals(this.getClass())) {
				/**
				 * In this case, we only want to exclude matching keys from
				 * being duplicated and don't care about the value aspect. There
				 * may be a valuable use case for making this more explicit
				 * later, in which case we will need a different approach.
				 */
				@SuppressWarnings("unchecked")
				Entry<M, N> otherEntry = (Entry<M, N>) other;
				if (this.getKey().equals(otherEntry.getKey())) {
					isEqual = true;
				}
			}

			return isEqual;
		}

	}

	private final int SIZE;
	private int position = 0;
	private Entry<K, V>[] entries;

	public Hashtable() {
		/** Defaulting initial size to an arbitrary amount for simplicity. */
		this(50);
	}

	public Hashtable(int size) {
		this.SIZE = size;
		entries = new Entry[SIZE];
	}

	/**
	 * Let's start with a simple implementation to add an Entry.
	 */
	public void put(K key, V value) {
		/**
		 * We'll need to double-check that the entries array isn't full already
		 * in the allocated space, so for now we'll reference a method that
		 * hasn't been created.
		 */
		if (entries.length == position) {
			grow();
		}

		/**
		 * Lest we forget, we have to make sure the entry isn't already in the
		 * entries array! We'll need a contains(key) check added to the internal
		 * Entry object.
		 */
		if (!this.contains(key)) {
			entries[position++] = new Entry<K, V>(key, value);
		}
	}

	/**
	 * Next we'll reproduce the get method, which will return the value
	 * corresponding to the key provided, or null if nothing is located.
	 */
	public V get(K key) {
		V foundVal = null;

		if (key != null) {
			for (int i = 0; i < position; i++) {
				if (key.equals(entries[i].getKey())) {
					foundVal = entries[i].getValue();
				}
			}
		}

		return foundVal;
	}

	/**
	 * To assist with some visibility to the consuming code, we'll want to be
	 * able to provide a number of elements back. In the java.util.Hashtable,
	 * this is the size() method.
	 */
	public int size() {
		return position;
	}

	/**
	 * Next we need to tackle the reallocation of size available for the entries
	 * array. grow() and shrink() will each scale the array as necessary using a
	 * shared method called resize.
	 */
	private void resize(int newSize) {
		int newPosition = 0;
		Entry<K, V>[] newEntries = new Entry[newSize];

		for (int i = 0; i < position && i < newSize; i++) {
			Entry<K, V> entry = entries[i];
			if (entry != null) {
				newEntries[i] = entry;
				newPosition++;
			}
		}
		entries = newEntries;
		position = newPosition;
	}

	private void grow() {
		resize(entries.length + SIZE);
	}

	/**
	 * This will be used after removing entries to make sure we aren't using up
	 * excessive space, though it'll come in handy later.
	 */
	private void shrink() {
		// TODO
	}

	/**
	 * Easily implemented - we'll simply use get to see if a null value is
	 * returned; if so, that means the key isn't contained in the entries array.
	 */
	public boolean contains(K key) {
		return get(key) != null;
	}

	/**
	 * Next we need to implement the remove functionality, which will leverage
	 * the aforementioned 'shrink' method to make sure we aren't going too big
	 * with our space usage.
	 */
	public void remove(K key) {
		int foundPosition = -1;

		if (contains(key)) {
			/** Locate the position to empty and backfill from */
			for (int i = 0; i < position; i++) {
				if (entries[i].getKey().equals(key)) {
					foundPosition = i;
					break;
				}
			}

			/**
			 * Shift the values backwards to fill in the found location and move
			 * the rest into the new placements.
			 */
			for (int i = foundPosition; i < position - 1; i++) {
				entries[i] = entries[i + 1];
			}

			/** decrement the position value to reflect the new size of entries */
			position--;
		}
	}
}
