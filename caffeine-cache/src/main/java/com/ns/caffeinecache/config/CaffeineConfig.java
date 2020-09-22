package com.ns.caffeinecache.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.ns.caffeinecache.entity.SysSetting;
import org.springframework.cache.CacheManager;
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

		caches.add(new CaffeineCache("salesorder",
				Caffeine.newBuilder().recordStats()
						.expireAfterWrite(DEFAULT_TTL, TimeUnit.SECONDS)
						.maximumSize(DEFAULT_MAXSIZE)
						.build())
		);
		cacheManager.setCaches(caches);
		return cacheManager;
	}

	@Bean
	public Cache<String, SysSetting> sysSettingCache() {
		return Caffeine.newBuilder()
				// 设置最后一次写入或访问后经过固定时间过期
//				.expireAfterWrite(60, TimeUnit.SECONDS)
				// 初始的缓存空间大小
				.initialCapacity(100)
				// 缓存的最大条数
				.maximumSize(1000)
				.build();
	}

}
