package just.education.convo_messaging_app.config;

import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import just.education.convo_messaging_app.cache.SessionDataCache;

import java.time.Duration;


@Configuration
public class RedisConfig {

    @Bean
    public JedisPoolConfig jedisPoolConfig(final RedisProperties redisProperties) {

        final RedisProperties.Jedis jedis = redisProperties.getJedis();
        final RedisProperties.Pool jedisPool = jedis.getPool();

        final JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxWait(jedisPool.getMaxWait());
        jedisPoolConfig.setMaxIdle(jedisPool.getMaxIdle());
        jedisPoolConfig.setMinIdle(jedisPool.getMinIdle());
        jedisPoolConfig.setMaxTotal(jedisPool.getMaxActive());
        jedisPoolConfig.setTimeBetweenEvictionRuns(jedisPool.getTimeBetweenEvictionRuns());
        jedisPoolConfig.setMinEvictableIdleDuration(Duration.ofSeconds(30));
        jedisPoolConfig.setNumTestsPerEvictionRun(5);

        return jedisPoolConfig;
    }

    @Bean
    public JedisClientConfiguration clientConfiguration(final JedisPoolConfig jedisPoolConfig) {

        final JedisClientConfiguration.JedisClientConfigurationBuilder builder = JedisClientConfiguration.builder();
        return builder.usePooling()
                .poolConfig(jedisPoolConfig)
                .build();
    }

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration(final RedisProperties redisProperties) {

        final RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
        redisStandaloneConfiguration.setDatabase(redisProperties.getDatabase());

        return redisStandaloneConfiguration;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(
            final JedisClientConfiguration clientConfiguration,
            final RedisStandaloneConfiguration redisStandaloneConfiguration
    ) {

        return new JedisConnectionFactory(redisStandaloneConfiguration, clientConfiguration);
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(final RedisConnectionFactory redisConnectionFactory) {

        final RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setEnableTransactionSupport(true);

        return template;
    }

    @Bean
    public SessionDataCache sessionCache(final RedisTemplate<Object, Object> redisTemplate) {

        return new SessionDataCache(redisTemplate);
    }
}