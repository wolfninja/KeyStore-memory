package com.wolfninja.keystore.memory;

import org.testng.Assert;
import org.testng.annotations.Test;

@SuppressWarnings("javadoc")
public class MemoryAdapterTest {

	@Test
	public void create() {
		Assert.assertEquals(MemoryAdapter.create().getClass(), MemoryAdapter.class);
	}
}
