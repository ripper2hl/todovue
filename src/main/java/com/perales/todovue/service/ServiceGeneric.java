package com.perales.todovue.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceGeneric<T, PK> {
    
    public T buscarPorId(PK id);    
    
    public Page<T> buscarTodos(Pageable pageable);
    
    public T guardar(T entity);
    
    public T actualizar(T entity);
    
    public T borrar(PK id);

}
