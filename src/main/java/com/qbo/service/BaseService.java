package com.qbo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface BaseService<E> {
	
    public List<E> findAll();
    public Optional<E> findById(Long id);
    public E save(E entity);
    public HashMap<String, String> deleteById(Long id);
}
