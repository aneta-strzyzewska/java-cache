package com.javacache.main;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

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
	@Path("random-name")
	@Produces("application/json")
	public String getRandomName() throws IOException, InterruptedException {
		String key = "randomWordJson";
		Optional<String> data = cache.get(key);

		if(data.isEmpty()) {
			String wordJson = proxy.getRandomWord();
			cache.set(key, wordJson);
			return wordJson;
		}
		return data.get();
	}
}
