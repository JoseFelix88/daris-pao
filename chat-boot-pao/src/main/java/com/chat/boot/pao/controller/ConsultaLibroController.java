package com.chat.boot.pao.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.chat.boot.pao.service.LibroService;

@Controller
public class ConsultaLibroController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8948123656105059208L;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private LibroService libroService;
	
	@PostMapping("/consulta/libro")
	public String consultarLibro(@ModelAttribute("search") String search) {
		libroService.listarLibros().forEach(libro -> log.info(libro.toString()));
		return "index";
	}
	
}
