package com.phenom.pub_sub;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
@EnableBinding(ViewsStreams.class)
public class JobBoardsAndUsersProcessor {
    @Autowired
    private ViewsStreams streams;
    private static final Logger logger = LogManager.getLogger(JobBoardsAndUsersProcessor.class);


    @StreamListener(ViewsStreams.INPUT)
    @SendTo(ViewsStreams.OP_JOB_BOARDS)
    public String handleBoards(String s) {
        logger.info(s);
        return s;
//        streams.outboundJobBoards().send(message(s));
//        streams.outboundUsers().send(message(s));
    }

    private static final <T> Message<T> message(T val) {
        return MessageBuilder
                .withPayload(val)
                .build();
    }

    @StreamListener("errorChannel")
    public void error(Message<?> message) {
        logger.error("Handling ERROR: " + message);
    }
}