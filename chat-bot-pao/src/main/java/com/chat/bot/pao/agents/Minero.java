package com.chat.bot.pao.agents;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.chat.bot.pao.service.impl.MineroService;

import akka.actor.UntypedActor;

@Named("minero")
@Scope("prototype")
public class Minero extends UntypedActor {
 
    public enum Mensaje {
        OBTENER_MATERIALES
    }
 
    private static final Logger log = LoggerFactory.getLogger(Minero.class);
    private final MineroService mineroService;
 
    @Inject
    public Minero(MineroService service) {
        this.mineroService = service;
    }
 
 
    @Override
    public void onReceive(Object o) throws InterruptedException {
        log.info("[Minero] ha recibido el mensaje: \"{}\".", o);
 
        if (o == Mensaje.OBTENER_MATERIALES) {
            log.info("[Minero] está obteniendo materiales...");
            mineroService.obtenerMinerales();
            log.info("[Minero] ha obtenido materiales.");
            getSender().tell(Herrero.Mensaje.MATERIALES, getSelf());
        } else {
            unhandled(o);
        }
    }
}
