package com.javacache.main;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryCache {

	private static MemoryCache INSTANCE;
	private final ConcurrentHashMap<String, Entry> cache;

	private MemoryCache() {
		this.cache = new ConcurrentHashMap<>();
	}

	public static MemoryCache getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MemoryCache();
		}
		return INSTANCE;
	}

	public Optional<String> get(String key) {
		Entry entry = this.cache.get(key);
		if (entry != null && isValid(entry)) {
			return Optional.of(entry.value());
		} else {
			this.invalidate(key);
			return Optional.empty();
		}
	}

	public Optional<String> set(String key, String value) {
		return this.set(key, value, 60_000);
	}

	public Optional<String> set(String key, String value, int ttl) {
		Entry entry = this.cache.putIfAbsent(
				key,
				new Entry(value, ttl, System.currentTimeMillis())
			);
		return Optional.ofNullable(entry == null ? null : entry.value());
	}

	private void invalidate(String key) {
		this.cache.remove(key);
	}

	private boolean isValid(Entry entry) {
		return entry.timestamp() + entry.ttl() > System.currentTimeMillis();
	}

	private record Entry(String value, int ttl, long timestamp) {}

}
