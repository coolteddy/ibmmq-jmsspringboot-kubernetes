package com.mqsample.mqspringboot;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MsgRcvr {

    @JmsListener(destination = "DEV.QUEUE.1", containerFactory = "myFactory")
    public void receiveMessage(Email email) {
        System.out.println("Received <" + email + ">");
    }
}