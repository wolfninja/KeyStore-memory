package com.wolfninja.keystore.memory;

import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.wolfninja.keystore.api.KeyValueStoreAdapter;
import com.wolfninja.keystore.api.Keyspace;

/**
 * @author nick
 * @since 1.0
 */
public class MemoryAdapter implements KeyValueStoreAdapter {

	private final Map<String, MemoryKeyspace> keyspaces = Maps.newHashMap();

	@Override
	public Keyspace getKeyspace(final String keyspaceName) {
		Preconditions.checkNotNull(keyspaceName, "KeyspaceName must not be null");
		MemoryKeyspace keyspace = keyspaces.get(keyspaceName);
		if (keyspace == null) {
			keyspace = new MemoryKeyspace();
			keyspaces.put(keyspaceName, keyspace);
		}
		return keyspace;
	}

}
