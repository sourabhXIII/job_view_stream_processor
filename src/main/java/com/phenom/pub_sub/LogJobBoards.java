package com.phenom.pub_sub;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@EnableBinding(ViewsStreams.class)
public class LogJobBoards {
    private static final Logger logger = LogManager.getLogger(LogJobBoards.class);

    @StreamListener(ViewsStreams.OP_JOB_BOARDS)
    public void handle(String board) {
        logger.info("Received board: " + board);
//        Gson gson = new GsonBuilder().create();
//        Map<String, String> input_map = gson.fromJson(gson.toJson(board), Map.class);
//        String board_name = input_map.get("jb");
//        logger.info("Received board name: " + board_name);
    }

    public static class JobBoard {
        private String boardName;

        public String getBoardName() {
            return boardName;
        }
        public void setBoardName(String name) {
            this.boardName = boardName;
        }

        @Override
        public String toString() {
            return this.boardName;
        }
    }
}