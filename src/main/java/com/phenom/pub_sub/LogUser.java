//package com.phenom.pub_sub;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.stereotype.Service;
//
//@Service
//@EnableBinding(ViewsProcessorBinder.class)
//public class LogUser {
//    private static final Logger logger = LogManager.getLogger(LogUser.class);
//
//    @StreamListener(ViewsProcessorBinder.OP_USERS)
//    public void handle(String person) {
//        logger.info("Received person: " + person);
////        Gson gson = new GsonBuilder().create();
////        Map<String, String> input_map = gson.fromJson(gson.toJson(person), Map.class);
////        String user_name = input_map.get("fname") + input_map.get(" lname");
////        logger.info("Received person name: " + user_name);
//    }
//
//    public static class User {
//        private String fname;
//        private String lname;
//
//        public String getfName() {
//            return fname;
//        }
//        public void setfName(String fname) {
//            this.fname = fname;
//        }
//
//        public String getlName() {
//            return lname;
//        }
//        public void setlName(String lname) {
//            this.lname = lname;
//        }
//
//        @Override
//        public String toString() {
//            return this.fname+this.lname;
//        }
//    }
//}