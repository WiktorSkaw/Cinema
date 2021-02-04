package com.wiktor.Cinema;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SeansRepository extends CrudRepository<Seans, Integer> {

    public List<Seans> findSeansByFilm(Film film);
}