package com.chat.bot.pao.agents;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.chat.bot.pao.service.impl.LibroServiceImpl;

import akka.actor.UntypedActor;

@Named("espadachin")
@Scope("prototype")
public class Espadachin extends UntypedActor {


    public enum Mensaje {
        ESPADA_NUEVA,
        ESPADA_ROTA;
    }

    private static final Logger log = LoggerFactory.getLogger(Espadachin.class);

    @Override
    public void onReceive(Object o) {
        log.info("[Espadachin] ha recibido el mensaje: \"{}\".", o);

        if (o == Mensaje.ESPADA_ROTA) {
        	LibroServiceImpl.herrero.tell(Herrero.Mensaje.CREAR_ESPADA, getSelf());
        } else if (o == Mensaje.ESPADA_NUEVA) {
            getContext().stop(getSelf());
        } else {
            unhandled(o);
        }
    }

}
