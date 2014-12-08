package com.wolfninja.keystore.memory;

import java.util.HashMap;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.wolfninja.keystore.api.KeyValue;
import com.wolfninja.keystore.api.Keyspace;

public class MemoryKeyspace implements Keyspace {

	private final HashMap<String, String> keys = Maps.newHashMap();

	@Override
	public boolean add(final String key, final String value) {
		Preconditions.checkNotNull(key, "Key should be provided");
		Preconditions.checkNotNull(value, "Value should be provided");

		if (keys.containsKey(key)) {
			return false;
		}
		keys.put(key, value);
		return true;
	}

	@Override
	public boolean checkAndSet(String key, String value, long version) {
		final String existing = keys.get(key);
		if (existing != null && existing.hashCode() == version) {
			keys.put(key, value);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(final String key) {
		Preconditions.checkNotNull(key, "Key should not be null");
		
		return keys.remove(key) != null;
	}

	@Override
	public boolean exists(final String key) {
		Preconditions.checkNotNull(key, "Key should not be null");
		return keys.containsKey(key);
	}

	@Override
	public Optional<KeyValue> get(final String key) {
		Preconditions.checkNotNull(key, "Key should not be null");
		final String value = keys.get(key);
		if (value != null) {
			return Optional.of(KeyValue.create(key, value, value.hashCode()));
		} else {
			return Optional.absent();
		}
	}

	@Override
	public boolean replace(String key, String value) {
		if (keys.containsKey(key)) {
			if (!value.equals(keys.get(key))) {
				keys.put(key, value);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean set(String key, String value) {
		keys.put(key, value);
		return true;
	}
}