package io.base.coreapi.config;


import io.base.coreapi.components.OcupacionConsumer;
import io.base.coreapi.model.Ocupacion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfigConsumer {

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("EMAILS");
    }

    @Bean
    public RedisMessageListenerContainer listenerContainer(MessageListenerAdapter listenerAdapter,
                                                           RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic(topic().getTopic()));
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(OcupacionConsumer consumer) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(consumer);
        messageListenerAdapter.setSerializer(new Jackson2JsonRedisSerializer<>(Ocupacion.class));
        return messageListenerAdapter;
    }

    @Bean
    public Jackson2JsonRedisSerializer<Ocupacion> jackson2JsonRedisSerializer() {
        return new Jackson2JsonRedisSerializer<>(Ocupacion.class);
    }
}
