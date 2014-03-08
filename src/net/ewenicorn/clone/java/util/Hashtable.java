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
	private class Entry<M, N> {
		private M key;
		private N value;

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
				Entry<M, N> otherEntry = (Entry) other;
				if (this.getKey().equals(otherEntry.getKey())) {
					isEqual = true;
				}
			}

			return isEqual;
		}

	}

}
