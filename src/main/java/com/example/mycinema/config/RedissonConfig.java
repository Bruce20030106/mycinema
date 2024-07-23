package com.example.mycinema.config;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useClusterServers()
                .addNodeAddress("redis://8.135.238.152:6379", "redis://8.135.238.152:6380", "redis://8.135.238.152:6381");
        return Redisson.create(config);
    }

    @Bean
    public RBloomFilter<Long> movieBloomFilter(RedissonClient redissonClient) {
        RBloomFilter<Long> bloomFilter = redissonClient.getBloomFilter("movieBloomFilter");
        // 初始化布隆过滤器的大小和误判率
        bloomFilter.tryInit(1000000L, 0.03);
        return bloomFilter;
    }
}

