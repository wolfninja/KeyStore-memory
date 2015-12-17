package com.wolfninja.keystore.memory;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wolfninja.keystore.api.Keyspace;

@SuppressWarnings("javadoc")
public class MemoryAdapterTest {

	@Test
	public void createTest() {
		Assert.assertEquals(MemoryAdapter.create().getClass(), MemoryAdapter.class);
	}

	@Test
	public void getKeyspaceReturnsSameKeyspaceTest() {
		final MemoryAdapter adapter = MemoryAdapter.create();
		final Keyspace keyspace = adapter.getKeyspace("abcd");
		Assert.assertNotNull(keyspace);

		final Keyspace keyspace2 = adapter.getKeyspace("abcd");
		Assert.assertTrue(keyspace == keyspace2);
	}

	@Test
	public void clearKeyspaceTest() {
		final MemoryAdapter adapter = MemoryAdapter.create();
		Assert.assertFalse(adapter.clearKeyspace("abcd"));
		final Keyspace keyspace = adapter.getKeyspace("abcd");
		keyspace.add("myKey", "someValue");
		Assert.assertTrue(adapter.clearKeyspace("abcd"));

	}
}
