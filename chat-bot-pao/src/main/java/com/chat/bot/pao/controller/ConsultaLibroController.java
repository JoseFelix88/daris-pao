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
import com.chat.bot.pao.model.dto.ChatDTO;
import com.chat.bot.pao.model.dto.LibroDTO;
import com.chat.bot.pao.service.LibroService;
import com.chat.bot.pao.service.impl.ChatService;

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
	
	@Autowired 
	private ChatService chatService;

	List<Libro> LIST_LIBROS = new ArrayList();

	List<Libro> LIST_LIBROS_RECOMENDADOS = new ArrayList();

	@GetMapping("/index")
	public String index(Model model) {
		return "index";
	}

	@GetMapping("/consulta/libro/{textSearch}")
	public String consultarLibro(@PathVariable("textSearch") String textSearch, HttpServletRequest request) {
		log.info("Search: " + textSearch);
		if (!agentFactory.existsAgent()) {
			agentFactory.initAgent();
		}
		LibroDTO libroDTO = libroService.obtenerLibrosByNombre(textSearch);
		LIST_LIBROS = libroDTO.getListLibros();
		LIST_LIBROS_RECOMENDADOS = libroDTO.getListLibrosRecomendados();
		return "consultar";
	}

	@GetMapping("/chatbot/index/")
	public String cargarChat(HttpServletRequest request, Model model) {
		StringBuilder mensajeChat = new StringBuilder();
		if (!ObjectUtils.isEmpty(LIST_LIBROS)) {
			ChatDTO chat = new ChatDTO();
			chat.setSolicitud("hola");
			log.info(chatService.getResponse(chat));
			mensajeChat.append("Hola, espero estes bien, he encontrado el siguiente listado de libros: \n");
			LIST_LIBROS.forEach(libro -> {
				mensajeChat.append(libro.getNombreLibro());
				if (LIST_LIBROS.size() > 1) {
					mensajeChat.append(",");
				} else {
					mensajeChat.append("\t\t");
				}
			});
		} else {
			mensajeChat.append(
					"Hola, espero estes bien, lo lamento no he podido encontrar alguna conincidencia exacta con tu busqueda. ");
		}

		if (!ObjectUtils.isEmpty(LIST_LIBROS_RECOMENDADOS)) {
			mensajeChat.append("\nTe suguiero revises el listado de recomendaciones.");
		}
		log.info(mensajeChat.toString());
		model.addAttribute("lstLibros", LIST_LIBROS_RECOMENDADOS);
		model.addAttribute("mensajeChat", mensajeChat.toString());
		return "consultar";
	}

}
