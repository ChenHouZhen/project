package com.chenhz.rabbitmq.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "direct")
public class DirectReceiver {

    @RabbitHandler
    public void process(String message){
        System.out.println("接受者 DirectReceiver,"+ message);
    }
}
