package com.chat.bot.pao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.bot.pao.model.Libro;
import com.chat.bot.pao.repository.LibroRepository;

@Service
public class HerreroService {
	private static final long TIEMPO_CREACION_ESPADA = 2000;

	@Autowired 
	private LibroRepository libroRepository;
	
	public void crearEspada() throws InterruptedException {
		Thread.sleep(TIEMPO_CREACION_ESPADA);
	}

	public List<Libro> obtenerListaLibrosBynombre(String nombreLibro) {
		return libroRepository.findByNombreLibro("%" + nombreLibro + "%");
	}
}
