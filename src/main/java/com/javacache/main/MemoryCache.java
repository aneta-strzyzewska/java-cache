package com.javacache.main;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.concurrent.ConcurrentHashMap;

import com.javacache.main.model.Entry;

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

	public String get(String key) {
		Entry entry = this.cache.get(key);
		if (entry != null && (entry.timestamp().plus(Duration.ofMillis(entry.ttl()))).isBefore(ZonedDateTime.now())) {
			return entry.value();
		} else {
			this.invalidate(key);
			return null;
		}
	}

	public String set(String key, String value) {
		return this.set(key, value, 60_000);
	}

	public String set(String key, String value, int ttl) {
		Entry entry = this.cache.putIfAbsent(key, new Entry(value, ttl, ZonedDateTime.now()));
		return entry == null ? null : entry.value();
	}

	private void invalidate(String key) {
		this.cache.remove(key);
	}
}
