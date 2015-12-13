package com.wolfninja.keystore.memory;

import org.testng.annotations.Test;

import com.wolfninja.keystore.api.BaseKeyValueStoreAdapterTest;

@SuppressWarnings("javadoc")
@Test
public class MemoryAdapterContractTest extends BaseKeyValueStoreAdapterTest {

	public MemoryAdapterContractTest() {
		super(new MemoryAdapter());
	}

}
