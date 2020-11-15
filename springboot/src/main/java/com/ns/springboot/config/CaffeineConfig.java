package com.ns.springboot.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author ns
 * @create 2020-07-04
 */
@Configuration
@EnableCaching
public class CaffeineConfig {
	public static final int DEFAULT_MAXSIZE = 10000;
	public static final int DEFAULT_TTL = 600;

	@Bean
	@Primary
	public CacheManager caffeineCacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		ArrayList<CaffeineCache> caches = new ArrayList<CaffeineCache>();
		caches.add(new CaffeineCache("customer",
				Caffeine.newBuilder().recordStats()
						.expireAfterWrite(DEFAULT_TTL, TimeUnit.SECONDS)
						.maximumSize(DEFAULT_MAXSIZE)
						.build())
		);
		cacheManager.setCaches(caches);
		return cacheManager;
	}
}
