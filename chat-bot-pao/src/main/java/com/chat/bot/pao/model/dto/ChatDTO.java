package com.chat.bot.pao.model.dto;

import java.io.Serializable;


public class ChatDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1063925307925886118L;

	private String solicitud;
	
	private String respuesta;
	
	public String getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(String solicitud) {
		this.solicitud = solicitud;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
}
