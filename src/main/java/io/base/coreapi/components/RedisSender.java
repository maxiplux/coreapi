package io.base.coreapi.components;


import io.base.coreapi.model.Ocupacion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

//@Service
@Slf4j
public class RedisSender {


    @Autowired
    private RedisTemplate<String, Ocupacion> redisTemplateEmail;

    @Autowired
    private ChannelTopic topic;


    public void sendDataToRedisQueue(Ocupacion input) {
        redisTemplateEmail.convertAndSend(topic.getTopic(), input);
        log.info("Data - " + input + " sent through Redis Topic - " + topic.getTopic());
    }
}
