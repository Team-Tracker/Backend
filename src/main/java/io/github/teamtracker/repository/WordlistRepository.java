package io.github.teamtracker.repository;

import org.springframework.data.repository.CrudRepository;

import io.github.teamtracker.model.misc.Wordlist;

public interface WordlistRepository extends CrudRepository<Wordlist, Integer> {
}