package com.javacache.main;

import jakarta.ws.rs.*;

import java.io.IOException;
import java.util.Optional;

@Path("app")
public class RandomNameController {

	private final RandomWordsProxy proxy;
	private final MemoryCache cache;

	public RandomNameController() {
		this.proxy = new RandomWordsProxy();
		this.cache = MemoryCache.getInstance();
	}

	@GET
	@Path("random-name/{key}")
	@Produces("application/json")
	public String getRandomName(
			@PathParam("key") String key,
			@QueryParam("skipCache") boolean skipCache
	) throws IOException, InterruptedException {
		if(skipCache) {
			return proxy.getRandomWord();
		}
		Optional<String> data = cache.get(key);

		if(data.isEmpty()) {
			String wordJson = proxy.getRandomWord();
			cache.set(key, wordJson);
			return wordJson;
		}
		return data.get();
	}
}
