package com.chat.bot.pao.repository.customized.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import com.chat.bot.pao.model.Libro;
import com.chat.bot.pao.repository.customized.CustomizedLibroRepository;

public class CustomizedLibroRepositoryImpl implements CustomizedLibroRepository {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Libro> findLibroRecomendado(Libro libro) {
		StringBuilder jpql = new StringBuilder("SELECT * FROM LIBROS WHERE 1 = 1 ");

		if (!ObjectUtils.isEmpty(libro.getAutor())) {
			jpql.append("OR autor LIKE :autor ");
		}

		if (!ObjectUtils.isEmpty(libro.getCasaEditorial())) {
			jpql.append("OR casa_editorial LIKE :casaEditorial ");
		}

		if (!ObjectUtils.isEmpty(libro.getLibreria())) {
			jpql.append("OR libreria LIKE :libreria ");
		}

		if (!ObjectUtils.isEmpty(libro.getGenero())) {
			jpql.append("OR genero LIKE :genero ");
		}

		Query query = entityManager.createNativeQuery(jpql.toString(), Libro.class);

		if (!ObjectUtils.isEmpty(libro.getAutor())) {
			query.setParameter("autor", "%" + libro.getAutor() + "%");
		}

		if (!ObjectUtils.isEmpty(libro.getCasaEditorial())) {
			query.setParameter("casaEditorial", "%" + libro.getCasaEditorial() + "%");
		}

		if (!ObjectUtils.isEmpty(libro.getLibreria())) {
			query.setParameter("libreria", "%" + libro.getLibreria() + "%");
		}

		if (!ObjectUtils.isEmpty(libro.getGenero())) {
			query.setParameter("genero", "%" + libro.getGenero() + "%");
		}

		return query.getResultList();
	}

	@Override
	public List<Libro> findLibroRecomendado(String nombreLibro) {
		Query query = entityManager.createNativeQuery("SELECT * FROM LIBROS WHERE NOMBRE_LIBRO LIKE :nombreLibro or genero LIKE :genero",
				Libro.class);
		query.setParameter("nombreLibro", "%" + nombreLibro + "%");
		query.setParameter("genero", "%" + nombreLibro + "%");
		return query.getResultList();
	}

}
