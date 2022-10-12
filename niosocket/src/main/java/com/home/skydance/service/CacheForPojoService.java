package com.home.skydance.service;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * POJO方式配置Ehcache
 */
@Service
public class CacheForPojoService {
    private CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
    private Cache<Integer, Object> squareNumberCache ;

    public CacheForPojoService() {
        this.cacheManager.init();
        this.squareNumberCache = cacheManager
                .createCache("squaredNumber", CacheConfigurationBuilder
                        .newCacheConfigurationBuilder(Integer.class, Object.class, ResourcePoolsBuilder.heap(10)));
    }

    /**
     * 添加数据
     * @param list
     */
    public void addMenuListToCache(List<String> list)
    {
        this.cacheManager.getCache("squaredNumber", Integer.class, Object.class).put(1, list);
    }

    /**
     * 获取数据
     * @return
     */
    public List<String> getMenuListToCache()
    {
        return (List<String>)this.cacheManager.getCache("squaredNumber", Integer.class, Object.class).get(1);
    }

    /**
     * 删除数据
     */
    public void delMenuListToCache()
    {
        this.cacheManager.getCache("squaredNumber", Integer.class, Object.class).remove(1);
    }

}
