package com.wolfninja.keystore.memory;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.wolfninja.keystore.api.KeyValue;
import com.wolfninja.keystore.api.Keyspace;

/**
 * In-Memory implementation of {@link Keyspace} <br>
 * <br>
 * This implementation is thread safe.
 * 
 * @since 0.1
 */
public class MemoryKeyspace implements Keyspace {

	private final ConcurrentHashMap<String, String> keys = new ConcurrentHashMap<>(16);

	@Override
	public boolean add(final String key, final String value) {
		Objects.requireNonNull(key, "Key should be provided");
		Objects.requireNonNull(value, "Value should be provided");

		return keys.putIfAbsent(key, value) == null;
	}

	@Override
	public boolean checkAndSet(final String key, final String value, long version) {
		Objects.requireNonNull(key, "Key should be provided");
		Objects.requireNonNull(value, "Value should be provided");

		final String existing = keys.get(key);
		if (existing != null && existing.hashCode() == version) {
			return keys.replace(key, existing, value);
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
			return keys.remove(key, existing);
		}
		return false;
	}

	@Override
	public boolean exists(final String key) {
		Objects.requireNonNull(key, "Key should not be null");
		return keys.containsKey(key);
	}

	@Override
	public Optional<String> get(final String key) {
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

		final String saved = keys.replace(key, value);
		return saved != null && !saved.equals(value);
	}

	@Override
	public boolean set(final String key, final String value) {
		Objects.requireNonNull(key, "Key should not be null");
		Objects.requireNonNull(value, "Value should not be null");

		keys.put(key, value);
		return true;
	}
}
