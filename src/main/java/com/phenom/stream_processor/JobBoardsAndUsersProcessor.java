package com.phenom.stream_processor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.phenom.bean.MessageBean;
import com.phenom.bean.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableBinding(ViewsProcessorBinder.class)
public class JobBoardsAndUsersProcessor {
    @Autowired
    private ViewsProcessorBinder streams;
    private static final Logger logger = LogManager.getLogger(JobBoardsAndUsersProcessor.class);

    @StreamListener(ViewsProcessorBinder.INPUT)
    public void handleBoards(String s) {
        Gson gson  = new GsonBuilder().setPrettyPrinting().create();

        MessageBean msg = gson.fromJson(s, MessageBean.class);
        logger.info(gson.toJson(msg));
        JsonObject msgJsonObj= gson.fromJson(gson.toJson(msg), JsonObject.class);

        JsonObject boardJsonObj = new JsonObject();
        boardJsonObj.addProperty("BoardName", msgJsonObj.get("BoardName").getAsString());
        msgJsonObj.remove("BoardName");

        Users user = gson.fromJson(gson.toJson(msgJsonObj), Users.class);

        logger.info(gson.toJson(user));
        logger.info(boardJsonObj);

        this.streams.outboundJobBoards().send(message(boardJsonObj.toString()));
        this.streams.outboundUsers().send(message(gson.toJson(user)));
    }

    private static final <T> Message<T> message(T val) {
        return MessageBuilder
                .withPayload(val)
                .build();
    }

    @ServiceActivator(inputChannel = ViewsProcessorBinder.INPUT + ".errors")
    public void error(Message<?> message) {
        logger.error("Handling ERROR: " + message);
    }

    @StreamListener("errorChannel")
    public void errorGlobal(Message<?> message) {
        logger.fatal("Handling Global ERROR: " + message);
    }
}