package com.chat.bot.pao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.chat.bot.pao.SpringExtension;
import com.chat.bot.pao.agents.Espadachin;
import com.chat.bot.pao.agents.util.AgentUtil;
import com.chat.bot.pao.model.Libro;
import com.chat.bot.pao.repository.LibroRepository;
import com.chat.bot.pao.service.LibroService;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

@Service
public class LibroServiceImpl implements LibroService {

	@Autowired 
	private LibroRepository libroRepository; 
	
	public static ActorRef espadachin;
	public static ActorRef herrero;
	public static ActorRef minero;
	
	@Autowired
    private ApplicationContext context;
	
	@Override
	public List<Libro> listarLibros() {
		return (List<Libro>) libroRepository.findAll();
	}

	@Override
	public void initAgent() {
		ActorSystem actorSystem = context.getBean(ActorSystem.class);

		espadachin = actorSystem.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).props("espadachin"),
				"espadachin");
		herrero = actorSystem.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).props("herrero"),
				"herrero");
		minero = actorSystem.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).props("minero"),
				"minero");

		espadachin.tell(Espadachin.Mensaje.ESPADA_ROTA, ActorRef.noSender());
	}

}
