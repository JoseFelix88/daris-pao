package com.chat.bot.pao.agents.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.chat.bot.pao.SpringExtension;
import com.chat.bot.pao.agents.Espadachin;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

@Component
public class AgentFactory {
	
	public static ActorRef espadachin;
	public static ActorRef herrero;
	public static ActorRef minero;
	
	@Autowired
    private ApplicationContext context;

	public void initAgent() {
		ActorSystem actorSystem = context.getBean(ActorSystem.class);

		espadachin = actorSystem.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).props("espadachin"),
				"espadachin");
		herrero = actorSystem.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).props("herrero"),
				"herrero");
		minero = actorSystem.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).props("minero"),
				"minero");
	}

	public boolean existsAgent() {
		return !ObjectUtils.isEmpty(espadachin) && 
				!ObjectUtils.isEmpty(herrero) && 
				!ObjectUtils.isEmpty(minero);
	}
}
