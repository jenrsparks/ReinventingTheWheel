package net.ewenicorn.clone.java.util;

/**
 * @author jmagas
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
		if (entries.length == ++position) {
			// TODO Add grow();
		}

		entries[position] = new Entry<K, V>(key, value);
	}

	/**
	 * Next we'll reproduce the get method, which will return the value
	 * corresponding to the key provided, or null if nothing is located.
	 */
	public V get(K key) {
		V foundVal = null;

		if (key != null) {
			for (int i = 0; i < entries.length; i++) {
				if (key.equals(entries[i].getKey())) {
					foundVal = entries[i].getValue();
				}
			}
		}

		return foundVal;
	}
}
