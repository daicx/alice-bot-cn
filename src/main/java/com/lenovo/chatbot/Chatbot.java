package com.lenovo.chatbot;

import com.lenovo.chatbot.aiml.AskToAIML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Chatbot {
    public static final String END = "bye";

    public static String input() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;

    }

    public static void main(String[] args) throws Exception {
        System.getProperties().load(Chatbot.class.getClassLoader().getResourceAsStream("bot.properties"));
        //System.getProperties().load(Chatbot.class.getClassLoader().getResourceAsStream("my.properties"));
	    	
	        /*AliceBotMother mother = new AliceBotMother();
	        mother.setUp();
	        AliceBot bot = mother.newInstance();
	        System.out.println(bot.respond("今天星期几"));
	        System.out.println(bot.respond("好烦呢"));
	        System.out.println(bot.respond("啦啦啦"));
	        System.err.println("Alice>" + bot.respond("welcome"));*/
        AskToAIML askToAIML = new AskToAIML();
        System.err.println("Alice>" + askToAIML.response("welcome"));
        while (true) {
            String input = input();
            if (END.equalsIgnoreCase(input)) {
                break;
            }
            System.err.println("Alice>" + askToAIML.response(input));
        }
    }
}