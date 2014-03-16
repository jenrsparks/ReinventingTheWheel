package net.ewenicorn.clone.java.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class HashtableTest {

	private Hashtable<String, Integer> table;

	@Before
	public void setUp() {
		table = new Hashtable<String, Integer>(10);
	}

	@Test
	public void testHashtable() {
		table = new Hashtable<String, Integer>();
		assertNotNull(table);
	}

	@Test
	public void testHashtableInt() {
		assertNotNull(table);
	}

	@Test
	public void testPut() {
		table.put("Test", Integer.getInteger("12"));
		assertEquals(1, table.size());
	}

	@Test
	public void testPutPastInitialAllocation() {
		table = new Hashtable<String, Integer>(1);
		table.put("1", Integer.valueOf(1));
		table.put("2", Integer.valueOf(2));
		assertEquals(2, table.size());
	}

	@Test
	public void testPutTwice() {
		String key = "Key";
		Integer val = Integer.valueOf(10);
		table = new Hashtable<String, Integer>(3);
		table.put(key, val);
		table.put(key, val);

		assertEquals(1, table.size());
	}

	@Test
	public void testGetFound() {
		String key = "Test";
		Integer val = Integer.getInteger("12");
		table.put(key, val);
		Integer foundVal = table.get(key);
		assertEquals(val, foundVal);
	}

	@Test
	public void testGetNotFound() {
		table.put("", Integer.getInteger("5"));
		Integer foundVal = table.get("Not Found");
		assertNull(foundVal);
	}

	@Test
	public void testContains() {
		String key = "Key";
		Integer val = Integer.valueOf(5);
		table.put(key, val);
		assertTrue(table.contains(key));
	}

	@Test
	public void testContainsNotFond() {
		String key = "Key";
		assertFalse(table.contains(key));
	}

	@Test
	public void testRemoveNotFound() {
		String key = "Key";
		table.remove(key);

		assertNull(table.get(key));
	}

	@Test
	public void testRemoveFound() {
		String key = "Key";
		table.put(key, Integer.valueOf(5));
		table.remove(key);

		assertNull(table.get(key));
	}

	@Test
	public void testRemoveSecondFound() {
		table = new Hashtable<String, Integer>(2);
		String key = "Key";
		Integer val = Integer.valueOf(5);
		table.put("Arbitrary", val);
		table.put(key, val);

		table.remove(key);

		assertNull(table.get(key));
	}
}
