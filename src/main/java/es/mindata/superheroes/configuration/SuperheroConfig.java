package es.mindata.superheroes.configuration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class SuperheroConfig {

    private static final Logger logger = LoggerFactory.getLogger(SuperheroConfig.class);


    @Autowired
    CacheManager cacheManager;

    public void evictAllCaches() {
        logger.info("[CLEAN CACHE] - Se limpia el cache");

        cacheManager.getCacheNames().stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

    @Scheduled(fixedRate = 6000)
    public void evictAllcachesAtIntervals() {
        evictAllCaches();
    }
}
