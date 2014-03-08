package net.ewenicorn.clone.java.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class HashtableTest {

	private Hashtable<String, Integer> table;

	@Before
	public void setUp() {
		table = new Hashtable<String, Integer>();
	}

	@Test
	public void testHashtable() {
		assertNotNull(table);
	}

	@Test
	public void testHashtableInt() {
		table = new Hashtable<String, Integer>(10);
		assertNotNull(table);
	}

	@Test
	public void testPut() {
		table.put("Test", Integer.getInteger("12"));
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
	public void testPutPastInitialAllocation() {
		table = new Hashtable<String, Integer>(1);
		table.put("1", Integer.valueOf(1));
		table.put("2", Integer.valueOf(2));
		assertEquals(2, table.size());
	}
}
