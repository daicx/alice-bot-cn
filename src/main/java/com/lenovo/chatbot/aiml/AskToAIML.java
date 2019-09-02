package com.lenovo.chatbot.aiml;

import java.io.IOException;

import bitoflife.chatterbean.AliceBot;
import bitoflife.chatterbean.AliceBotMother;

public class AskToAIML implements IAskApproach {

	private static AliceBotMother mother = null;
	private static AliceBot bot = null;
	private static GossipLoad gossipLoad = null;

	public AskToAIML() {
		gossipLoad = new GossipLoad();
		try {
			gossipLoad.load();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			gossipLoad.clean();
		}
		mother = new AliceBotMother();
		mother.setUp();
		bot = mother.newInstance();

	}

	public String response(String input) {
		return bot.respond(input);
	}
}
