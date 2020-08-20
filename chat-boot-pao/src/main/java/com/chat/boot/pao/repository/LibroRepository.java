package com.chat.boot.pao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.chat.boot.pao.model.Libro;

@Repository
public interface LibroRepository extends PagingAndSortingRepository<Libro, Integer> {

	
	@Query(value = "Select * from Libros where nombre_libro like %?1%", nativeQuery = true)
	List<Libro> findByNombreLibro(String nombreLibro);
	
}
