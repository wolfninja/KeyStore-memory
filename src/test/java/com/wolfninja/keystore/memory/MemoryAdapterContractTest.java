package com.wolfninja.keystore.memory;

import org.testng.annotations.Test;

import com.wolfninja.keystore.api.BaseKeyValueStoreAdapterTest;

@Test
public class MemoryAdapterContractTest extends BaseKeyValueStoreAdapterTest {

	public MemoryAdapterContractTest() {
		super(new MemoryAdapter());
	}

}
