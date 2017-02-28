package com.perales.todovue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.perales.todovue.model.Todo;
import com.perales.todovue.service.TodoService;

@RestController
@RequestMapping("v1/todo")
public class TodoController implements ControllerGeneric<Todo, Integer>{
    
    @Autowired
    private TodoService todoService;
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @Override
    public Todo buscarPorId(@PathVariable Integer id) {
        return todoService.buscarPorId(id);
    }
    
    @RequestMapping(method = RequestMethod.GET, params= {"page", "size"})    
    @Override
    public Page<Todo> buscarTodos(@RequestParam int page, @RequestParam int size) {
        PageRequest pageable = new PageRequest(page,size);
        return todoService.buscarTodos(pageable);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @Override
    public Todo guardar(@RequestBody Todo entity) {
        return todoService.guardar(entity);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    @Override
    public Todo actualizar(@RequestBody Todo entity) {
        return todoService.actualizar(entity);
    }
    
    @RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
    @Override
    public Todo borrar(@PathVariable Integer id) {
        return todoService.borrar(id);
    }
}