package com.ns.springboot.cache;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @author ns
 * @create 2020-07-04
 *
 * 通过异步自动加载实体到缓存中
 * 基于大小的回收策略
 * 基于时间的回收策略
 * 自动刷新
 * key自动封装虚引用
 * value自动封装弱引用或软引用
 * 实体过期或被删除的通知
 * 写入外部资源
 * 统计累计访问缓存
 *
 * 过期策略:
 * 在Caffeine中分为两种缓存，一个是有界缓存，一个是无界缓存，无界缓存不需要过期并且没有界限。在有界缓存中提供了三个过期API:
 * expireAfterWrite：代表着写了之后多久过期。（上面列子就是这种方式）
 * expireAfterAccess: 代表着最后一次访问了之后多久过期。
 * expireAfter:在expireAfter中需要自己实现Expiry接口，这个接口支持create,update,以及access了之后多久过期
 * 注意这个API和前面两个API是互斥的。这里和前面两个API不同的是，需要你告诉缓存框架，他应该在具体的某个时间过期
 * 也就是通过前面的重写create,update,以及access的方法，获取具体的过期时间。
 *
 * 更新策略: 设定多长时间后会自动刷新缓存 refreshAfterWrite() afterRead()
 * 加载策略 :手动加载，同步加载，异步加载
 *
 * 驱逐策略: 基于大小（size-based），基于时间（time-based）和基于引用（reference-based
 * 基于大小驱逐，有两种方式：一种是基于缓存大小，一种是基于权重
 * 基于时间（Time-based） 固定的到期策略 不同的到期策略
 * 基于引用（reference-based）我们可以将缓存的驱逐配置成基于垃圾回收器。为此，我们可以将key 和 value 配置为弱引用或只将值配置成软引用。
 * 注意：AsyncLoadingCache不支持弱引用和软引用。
 *
 * 移除监听器（Removal）:
 * 驱逐（eviction）：由于满足了某种驱逐策略，后台自动进行的删除操作
 * 无效（invalidation）：表示由调用方手动删除缓存
 * 移除（removal）：监听驱逐或无效操作的监听器
 *
 * 刷新（Refresh）: refreshAfterWrite(1, TimeUnit.MINUTES)
 * 刷新和驱逐是不一样的。刷新的是通过LoadingCache.refresh(key)方法来指定，并通过调用CacheLoader.reload方法来执行
 * 刷新key会异步地为这个key加载新的value，并返回旧的值（如果有的话）。驱逐会阻塞查询操作直到驱逐作完成才会进行其他操作。
 * 与expireAfterWrite不同的是，refreshAfterWrite将在查询数据的时候判断该数据是不是符合查询条件，如果符合条件该缓存就会去执行刷新操作
 * 例如，您可以在同一个缓存中同时指定refreshAfterWrite和expireAfterWrite，只有当数据具备刷新条件的时候才会去刷新数据，不会盲目去执行刷新操作。
 * 如果数据在刷新后就一直没有被再次查询，那么该数据也会过期。
 *
 * 统计（Statistics）:
 * Caffeine.recordStats()，您可以打开统计信息收集。Cache.stats() 方法返回提供统计信息的CacheStats
 * hitRate()：返回命中与请求的比率
 * hitCount(): 返回命中缓存的总数
 * evictionCount()：缓存逐出的数量
 * averageLoadPenalty()：加载新值所花费的平均时间
 * https://www.cnblogs.com/xingzc/p/9567159.html
 */

public class CaffeineCache {


	public static void main(String[] args) {
		Cache<String, String> cache = Caffeine.newBuilder()
				.expireAfterWrite(1, TimeUnit.MINUTES)
				.expireAfterAccess(1, TimeUnit.MINUTES)
				.maximumSize(10000)
				.build();
//		手动加载
		cache.put("123", "12");

		// 检索一个entry，如果没有则为null
		String value = cache.getIfPresent("123");
		// 检索一个entry，如果entry为null，则通过key创建一个entry并加入缓存
		value = cache.get("1234", CaffeineCache::createCache);
		// 插入或更新一个实体
		cache.put("123", "123");
		// 移除一个实体
		cache.invalidate("123");

		System.out.println();


//		同步加载 构造Cache时候，build方法传入一个CacheLoader实现类。实现load方法，通过key加载value 没有自动执行加载方法

		LoadingCache<String, String> cache1 = Caffeine.newBuilder()
				.maximumSize(10_000)
				.expireAfterWrite(10, TimeUnit.MINUTES)
				.build(CaffeineCache::createCache);

//		异步加载

//		AsyncLoadingCache<String, String> cache2 = Caffeine.newBuilder()
//				.maximumSize(10_000)
//				.expireAfterWrite(10, TimeUnit.MINUTES)
//				.buildAsync((key, executor) -> createCacheAsync(key, executor));
//		CompletableFuture graph = cache2.get("123");
//		CompletableFuture<Map<String, String>> graphs = cache2.getAll(List.of("123"));

	}

	private static String createCache(String key) {
		return key;
	}


	private static String createCacheAsync(String key, Executor executor) {
		return key;
	}
}
