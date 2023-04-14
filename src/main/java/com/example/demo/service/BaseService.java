package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.common.DataNotFoundException;

@Service
public interface BaseService<T> {
	public List<T> findAll();
	
	public T findById(Integer id) throws DataNotFoundException;
	
	public void save(T t);
	
	public void deleteById(Integer id);
}