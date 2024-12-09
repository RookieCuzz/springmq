package com.example.springmq.controller;


import com.example.springmq.service.RocketMQProducerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProducerController {


    @Resource
    RocketMQProducerService rocketMQProducerService;

    @GetMapping("/sendMessage")
    public Boolean sendMessage(String topic,@RequestParam("message") String message){
        System.out.println(message);
        rocketMQProducerService.sendMessage(topic,message);
        return Boolean.TRUE;
    }

}
