package com.perales.todovue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perales.todovue.model.Todo;
import com.perales.todovue.repository.TodoRepository;

@Service
@Transactional
public class TodoService implements ServiceGeneric<Todo, Integer>{
    
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo buscarPorId(Integer id) {
        return todoRepository.findOne(id);
    }

    @Override
    public Page<Todo> buscarTodos(Pageable pageable) {
        Page<Todo> pages = todoRepository.findAll(pageable);
        return pages;
    }

    @Override
    public Todo guardar(Todo entity) {
        return todoRepository.save(entity);
    }

    @Override
    public Todo actualizar(Todo entity) {
        return todoRepository.saveAndFlush(entity);
    }

    @Override
    public Todo borrar(Integer id) {
        Todo todo = todoRepository.findOne(id);
        if(todo != null){
            todoRepository.delete(id);
        }
        return todo;
    }

}
