package com.perales.todovue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perales.todovue.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {}
