package com.wolfninja.keystore.memory;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.wolfninja.keystore.api.KeyValueStoreAdapter;
import com.wolfninja.keystore.api.Keyspace;

/**
 * In-Memory implementation of {@link KeyValueStoreAdapter} <br>
 * <br>
 * This implementation is thread safe.
 * 
 * @since 0.1
 */
public class MemoryAdapter implements KeyValueStoreAdapter {

	/**
	 * @return new {@link MemoryAdapter} instance
	 * @since 0.1
	 */
	public static MemoryAdapter create() {
		return new MemoryAdapter();
	}

	private final ConcurrentHashMap<String, MemoryKeyspace> keyspaces = new ConcurrentHashMap<>();

	@Override
	public Keyspace getKeyspace(final String keyspaceName) {
		Objects.requireNonNull(keyspaceName, "KeyspaceName must not be null");
		return keyspaces.computeIfAbsent(keyspaceName, k -> new MemoryKeyspace());
	}

	/**
	 * Clear keyspace for the given keyspace name, if present
	 * 
	 * @param keyspaceName
	 *            String keyspace name, not null
	 * @return boolean true if keyspace exists and cleared, false otherwise
	 */
	public boolean clearKeyspace(final String keyspaceName) {
		Objects.requireNonNull(keyspaceName, "KeyspaceName must not be null");
		return keyspaces.remove(keyspaceName) != null;
	}
}
