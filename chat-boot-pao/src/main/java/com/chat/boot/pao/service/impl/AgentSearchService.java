package com.chat.boot.pao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.chat.boot.pao.model.Libro;
import com.chat.boot.pao.repository.LibroRepository;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgentSearchService extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8536854713085271552L;
	@Autowired 
	private LibroRepository libroRepository;
	
	@Override
	protected void setup() {
		
		addBehaviour(new CyclicBehaviour() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -2819159408594980380L;

			@Override
			public void action() {
				
				ACLMessage message = receive();
				System.out.println(message.getContent());
				List<Libro> lstResult = libroRepository.findByNombreLibro(message.getContent());
				lstResult.forEach(result -> System.out.println(result));
				
			}
		}); 
	}

	
	
}
