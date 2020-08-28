package com.chat.bot.pao.agents.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.chat.bot.pao.SpringExtension;
import com.chat.bot.pao.agents.Espadachin;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class AgentUtil {
	
	public static ActorRef espadachin;
	public static ActorRef herrero;
	public static ActorRef minero;
	
	@Autowired
    private ApplicationContext context;

	
	public static void initAgent() {
	}

}
