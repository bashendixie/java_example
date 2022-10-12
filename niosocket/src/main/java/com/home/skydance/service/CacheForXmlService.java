package com.home.skydance.service;


import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.xml.XmlConfiguration;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;

/**
 * xml方式配置Ehcache
 */
@Service
public class CacheForXmlService {
    private CacheManager cacheManager;

    public CacheForXmlService() {
        URL myUrl = getClass().getResource("/my-config.xml");
        Configuration xmlConfig = new XmlConfiguration(myUrl);
        cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
        cacheManager.init();
    }

    /**
     * 添加数据
     * @param list
     */
    public void addMenuListToCache(String list)
    {
        this.cacheManager.getCache("foo", Integer.class, String.class).put(1, list);
    }

    /**
     * 获取数据
     * @return
     */
    public String getMenuListToCache()
    {
        return this.cacheManager.getCache("foo", Integer.class, String.class).get(1);
    }
}
