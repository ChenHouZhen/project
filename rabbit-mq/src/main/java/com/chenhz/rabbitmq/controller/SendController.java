package com.chenhz.rabbitmq.controller;

import com.chenhz.common.entity.R;
import com.chenhz.rabbitmq.direct.RabbitDirectSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("send")
public class SendController {

    @Autowired
    RabbitDirectSender sender;

    @PostMapping("/h")
    public R sendHello(){
        sender.sendHello();
        return R.ok();
    }

    @PostMapping("/s")
    public R sendDirect(){
        sender.sendDirect();
        return R.ok();
    }
}
