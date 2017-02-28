package com.perales.todovue.controller;

import org.springframework.data.domain.Page;

public interface ControllerGeneric<T, PK> {
    
    public T buscarPorId(PK id);    
    
    public Page<T> buscarTodos(int page, int size);
    
    public T guardar(T entity);
    
    public T actualizar(T entity);
    
    public T borrar(PK id);

}
