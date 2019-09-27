//package com.phenom.pub_sub;
//
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.stereotype.Service;
//
//@Service
//@EnableBinding(ViewsProcessorBinder.class)
//public class LogJobBoards {
//    private static final Logger logger = LogManager.getLogger(LogJobBoards.class);
//
//    @StreamListener(ViewsProcessorBinder.OP_JOB_BOARDS)
//    public void handle(String board) {
//        logger.info("Received board: " + board);
////        throw new RuntimeException("BOOM!");
//    }
//
//    public static class JobBoard {
//        private String boardName;
//
//        public String getBoardName() {
//            return boardName;
//        }
//        public void setBoardName(String name) {
//            this.boardName = boardName;
//        }
//
//        @Override
//        public String toString() {
//            return this.boardName;
//        }
//    }
//}