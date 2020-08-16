package com.chat.boot.pao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.chat.boot.pao.model.Libro;

@Repository
public interface LibroRepository extends PagingAndSortingRepository<Libro, Integer> {

}
