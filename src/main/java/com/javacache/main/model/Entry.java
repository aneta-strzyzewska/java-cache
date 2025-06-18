package com.javacache.main.model;

import java.time.ZonedDateTime;

public record Entry(String value, int ttl, ZonedDateTime timestamp) {}

