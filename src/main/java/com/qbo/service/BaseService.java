package com.qbo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.qbo.model.Estado;

public interface BaseService<E> {
	
    public List<E> findAll();
    public Optional<Estado> findById(Long id);
    public E save(E entity);
    public E update(Long id, E entity);
    public HashMap<String, String> deleteById(Long id);
}
