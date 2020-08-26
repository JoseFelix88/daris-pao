package com.chat.bot.pao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.bot.pao.model.Libro;
import com.chat.bot.pao.repository.LibroRepository;
import com.chat.bot.pao.service.LibroService;

@Service
public class LibroServiceImpl implements LibroService {

	@Autowired 
	private LibroRepository libroRepository; 
	
	@Override
	public List<Libro> listarLibros() {
		return (List<Libro>) libroRepository.findAll();
	}

}
