package com.chat.bot.pao.model.dto;

import java.util.List;

import com.chat.bot.pao.model.Libro;

public class LibroDTO {

	private String nombreLibro;
	
	private List<Libro> listLibros;

	public String getNombreLibro() {
		return nombreLibro;
	}

	public void setNombreLibro(String nombreLibro) {
		this.nombreLibro = nombreLibro;
	}

	public List<Libro> getListLibros() {
		return listLibros;
	}

	public void setListLibros(List<Libro> listLibros) {
		this.listLibros = listLibros;
	}
	
	
	
}
