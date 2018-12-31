package com.springboot.chapter7.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/30
 **/
@SpringBootApplication(scanBasePackages = "com.springboot.chapter7")
@MapperScan(basePackages = "com.springboot.chapter7", annotationClass = Repository.class)
//使用注解驱动缓存机制
@EnableCaching
public class Chapter7Application {

    @Autowired
    private RedisTemplate redisTemplate = null;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory = null;

    @Autowired
    private MessageListener messageListener = null;

    //任务池
    private ThreadPoolTaskScheduler taskScheduler = null;

    @PostConstruct
    public void initRedisTemplate(){
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }

    /**
     * 创建任务池
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler initTaskScheduler(){
        if(taskScheduler != null){
            return  taskScheduler;
        }

        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }

    /**
     * 定义redis监听容器
     * @return
     */
    @Bean
    public RedisMessageListenerContainer initRedisContainer(){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        //设置连接工厂
        container.setConnectionFactory(redisConnectionFactory);
        //设置运行任务池
        container.setTaskExecutor(taskScheduler);
        //定义Topic
        Topic topic = new ChannelTopic("topic1");
        //使用监听器监听Redis消息
        container.addMessageListener(messageListener, topic);

        return container;
    }

    /**
     * 自定义Redis缓存管理器
     * @return
     */
    @Bean("redisCacheManager")
    public RedisCacheManager initRedisCacheManager(){
        //Redis加锁的写入器
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory);

        //启动Redis缓存的默认设置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();

        //设置JDK序列化器
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer()));

        //禁用前缀
        config = config.disableKeyPrefix();
        //设置10min超时
        config = config.entryTtl(Duration.ofMinutes(10));

        //创建Redis缓存管理器
        RedisCacheManager redisCacheManager = new RedisCacheManager(writer,config);
        return redisCacheManager;

    }
    public static void main(String[] args) {
        SpringApplication.run(Chapter7Application.class, args);
    }
}
