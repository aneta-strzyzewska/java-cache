package com.javacache.main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MemoryCacheTest {

	MemoryCache cache = MemoryCache.getInstance();

	@Test
	void cacheSetterAndGetterTest() {
		cache.set("key", "value");
		var value = cache.get("key");
		assertTrue(value.isPresent());
		assertEquals("value", value.get());
	}

}
