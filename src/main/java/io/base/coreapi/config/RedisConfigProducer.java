package io.base.coreapi.config;


import io.base.coreapi.model.Ocupacion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
@Slf4j
public class RedisConfigProducer {

    @Bean
    JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        // todo: move to app properties
        factory.getPoolConfig().setMaxIdle(30);
        factory.getPoolConfig().setMinIdle(30);
        return factory;
    }

    @Bean
    public RedisTemplate<String, Ocupacion> redisTemplateEmails() {
        RedisTemplate<String, Ocupacion> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<Ocupacion>(Ocupacion.class));
        return template;
    }


//    @Bean
//    public RedisTemplate<String, String> redisTemplate() {
//        RedisTemplate<String, String> template = new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory());
//        template.setValueSerializer(new GenericToStringSerializer<String>(String.class));
//        return template;
//    };


//    @Bean
//    RedisMessageListenerContainer redisContainer() {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory());
//
//        container.addMessageListener(new MessageListenerAdapter(new RedisReciever()), topic());
//        container.setTaskExecutor(Executors.newFixedThreadPool(10));
//        return container;
//    }


}
