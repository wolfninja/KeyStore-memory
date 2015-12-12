package com.wolfninja.keystore.memory;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

import com.wolfninja.keystore.api.KeyValue;
import com.wolfninja.keystore.api.Keyspace;

/**
 * @since 1.0
 */
public class MemoryKeyspace implements Keyspace {

	private final HashMap<String, String> keys = new HashMap<>();

	@Override
	public boolean add(final String key, final String value) {
		Objects.requireNonNull(key, "Key should be provided");
		Objects.requireNonNull(value, "Value should be provided");

		if (keys.containsKey(key)) {
			return false;
		}
		keys.put(key, value);
		return true;
	}

	@Override
	public boolean checkAndSet(final String key, final String value, long version) {
		Objects.requireNonNull(key, "Key should be provided");
		Objects.requireNonNull(value, "Value should be provided");

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
		Objects.requireNonNull(key, "Key should not be null");

		return keys.remove(key) != null;
	}

	@Override
	public boolean deletes(final String key, long version) {
		Objects.requireNonNull(key, "Key should be provided");

		final String existing = keys.get(key);
		if (existing != null && existing.hashCode() == version) {
			keys.remove(key);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean exists(final String key) {
		Objects.requireNonNull(key, "Key should not be null");
		return keys.containsKey(key);
	}

	@Override
	public Optional<String> get(String key) {
		Objects.requireNonNull(key, "Key should not be null");
		return Optional.ofNullable(keys.get(key));
	}

	@Override
	public Optional<KeyValue> gets(final String key) {
		Objects.requireNonNull(key, "Key should not be null");
		final String value = keys.get(key);
		if (value != null) {
			return Optional.of(KeyValue.create(key, value, value.hashCode()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public boolean replace(final String key, final String value) {
		Objects.requireNonNull(key, "Key should not be null");
		Objects.requireNonNull(value, "Value should not be null");

		if (keys.containsKey(key)) {
			if (!value.equals(keys.get(key))) {
				keys.put(key, value);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean set(final String key, final String value) {
		Objects.requireNonNull(key, "Key should not be null");
		Objects.requireNonNull(value, "Value should not be null");

		keys.put(key, value);
		return true;
	}
}
