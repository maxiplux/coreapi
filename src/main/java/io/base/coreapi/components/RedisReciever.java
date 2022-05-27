package io.base.coreapi.components;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;


public class RedisReciever implements MessageListener {


    @Override
    public void onMessage(Message message, byte[] pattern) {
        // TODO Auto-generated method stub

        //log.info("Received data - " + message.toString() + " from Topic - " + new String(pattern));
    }
}
