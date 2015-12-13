package com.wolfninja.keystore.memory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.wolfninja.keystore.api.KeyValueStoreAdapter;
import com.wolfninja.keystore.api.Keyspace;

/**
 * @since 0.1
 */
public class MemoryAdapter implements KeyValueStoreAdapter {

	private final Map<String, MemoryKeyspace> keyspaces = new HashMap<>();

	@Override
	public Keyspace getKeyspace(final String keyspaceName) {
		Objects.requireNonNull(keyspaceName, "KeyspaceName must not be null");
		MemoryKeyspace keyspace = keyspaces.get(keyspaceName);
		if (keyspace == null) {
			keyspace = new MemoryKeyspace();
			keyspaces.put(keyspaceName, keyspace);
		}
		return keyspace;
	}

}
