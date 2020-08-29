package com.chat.bot.pao.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.chat.bot.pao.agents.util.AgentFactory;
import com.chat.bot.pao.model.Libro;
import com.chat.bot.pao.service.LibroService;

@Controller
public class ConsultaLibroController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8948123656105059208L;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private AgentFactory agentFactory;
	
	@Autowired 
	private LibroService libroService;
	
	List<Libro> LIST_LIBROS = new ArrayList();
	
	@GetMapping("/index")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/consulta/libro/{textSearch}")
	public String consultarLibro(@PathVariable("textSearch") String textSearch, HttpServletRequest request) {
		log.info("Search: "+ textSearch);
		if(!agentFactory.existsAgent()) {
			agentFactory.initAgent();
		}
		List<Libro> lstLibros = libroService.obtenerLibrosByNombre(textSearch);
		LIST_LIBROS = lstLibros;
		return "consultar";
	}
	
	
	@GetMapping("/chatbot/index/")
	public String cargarChat(HttpServletRequest request, Model model) {
		List<Libro> lstLibros = LIST_LIBROS;
		lstLibros.forEach(libro -> log.info(!ObjectUtils.isEmpty(libro) ? libro.toString(): ""));
		
		return "consultar";
	}
	
}
