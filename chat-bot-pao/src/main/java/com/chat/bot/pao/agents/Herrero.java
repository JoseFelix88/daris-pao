package com.chat.bot.pao.agents;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.util.ObjectUtils;

import com.chat.bot.pao.agents.util.AgentFactory;
import com.chat.bot.pao.model.Libro;
import com.chat.bot.pao.model.dto.LibroDTO;
import com.chat.bot.pao.service.impl.HerreroService;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

@Named("herrero")
@Scope("prototype")
public class Herrero extends UntypedActor {
	public enum Mensaje {
		CREAR_ESPADA, MATERIALES
	}

	private static final Logger log = LoggerFactory.getLogger(Herrero.class);
	private ArrayList<ActorRef> espadachines;
	private final HerreroService herreroService;
	
	

	@Inject
	public Herrero(HerreroService herreroService) {
		this.herreroService = herreroService;
	}

	@Override
	public void preStart() {
		espadachines = new ArrayList<>();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Object message) throws InterruptedException {
		log.info("[Herrero] ha recibido el mensaje: \"{}\".", message);
		LibroDTO libro = (LibroDTO) message;
		List<Libro> lstLibros = herreroService.obtenerListaLibrosBynombre(libro.getNombreLibro());
		libro.setListLibros(lstLibros);
		List<Libro> listLibrosRecomendados = herreroService.obtenerListaLibrosRecomendados(libro.getListLibros(), libro.getNombreLibro());
		libro.setListLibrosRecomendados(listLibrosRecomendados);
		if(!ObjectUtils.isEmpty(lstLibros)) {
			AgentFactory.espadachin.tell(libro, getSelf());
		} else {
			unhandled(message);
		}
		/*if (o == Mensaje.CREAR_ESPADA) {
			espadachines.add(getSender());
			AgentFactory.minero.tell(Minero.Mensaje.OBTENER_MATERIALES, getSelf());
		} else if (o == Mensaje.MATERIALES) {
			log.info("[Herrero] está creando espada...");
			herreroService.crearEspada();
			log.info("[Herrero] ha creado espada.");
			if (!espadachines.isEmpty()) {
				espadachines.get(0).tell(Espadachin.Mensaje.ESPADA_NUEVA, getSelf());
				espadachines.remove(0);
			}
		} else {
			unhandled(o);
		}*/
	}

}
