package com.javacache.main;

import java.util.concurrent.ConcurrentHashMap;

public class MemoryCache {
    private static ConcurrentHashMap<String, String> INSTANCE;

    private MemoryCache() { }

    public static ConcurrentHashMap<String, String> getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConcurrentHashMap<>();

            return INSTANCE;
        }
        return INSTANCE;
    }

}
