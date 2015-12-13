package com.wolfninja.keystore.memory;

import org.testng.annotations.Test;

import com.wolfninja.keystore.api.BaseKeyspaceTest;

@SuppressWarnings("javadoc")
@Test
public class MemoryKeyspaceContractTest extends BaseKeyspaceTest {

	public MemoryKeyspaceContractTest() {
		super(new MemoryAdapter().getKeyspace("a.b.c"));
	}

}
