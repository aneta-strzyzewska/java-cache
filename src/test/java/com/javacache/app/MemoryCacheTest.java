package com.javacache.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class MemoryCacheTest {

	MemoryCache cache = MemoryCache.getInstance();

	@AfterEach
	void cleanUp() {
		this.cache.clear();
	}

	@Test
	void cacheSetterAndGetterTest() {
		cache.set("key", "value");
		Optional<String> value = cache.get("key");
		assertTrue(value.isPresent());
		assertEquals("value", value.get());
	}

	@Test
	void cacheInvalidateTest() throws InterruptedException {
		cache.set("key", "value", 50);
		Thread.sleep(50);
		Optional<String> value = cache.get("key");
		assertFalse(value.isPresent());
	}

}
