package com.chat.boot.pao.service.impl;

import org.springframework.stereotype.Component;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

@Component
public class AgenteDispatcherService extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2785329544942289826L;

	private String parameterSearch;
	
	
	@Override
	protected void setup() {
		addBehaviour(new CyclicBehaviour() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -4386655285825378377L;

			@Override
			public void action() {
				ACLMessage message = new ACLMessage(ACLMessage.INFORM);
				message.setContent(getParameterSearch());
				message.addReceiver(new AID("agentSearch", AID.ISLOCALNAME));
			}

			
		});
		
	}
	
	public void setParameterSearch(String parameterSearch) {
		this.parameterSearch = parameterSearch;
		setup();
	}

	public String getParameterSearch() {
		return parameterSearch;
	}

	
	
	
}
