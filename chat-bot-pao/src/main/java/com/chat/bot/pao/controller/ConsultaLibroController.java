package com.chat.bot.pao.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.chat.bot.pao.service.LibroService;

@Controller
public class ConsultaLibroController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8948123656105059208L;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private LibroService libroService;
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("search", "");
		return "index";
	}
	
	@GetMapping("/consulta/libro/{textSearch}")
	public String consultarLibro(@PathVariable("textSearch") String textSearch) {
		log.info("Search: "+ textSearch);
		libroService.initAgent();
		return "index";
	}
	
}
