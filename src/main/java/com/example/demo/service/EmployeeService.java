package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.dao.BaseDao;
import com.example.demo.entity.Employee;

@Service
public class EmployeeService implements BaseService<Employee> {
	@Autowired
	private BaseDao<Employee> dao;
	
	@Override
	public List<Employee> findAll() {
		return dao.findAll();
	}
	
	@Override
	public Employee findById(Integer id) throws DataNotFoundException {
		return dao.findById(id);
	}
	
	@Override
	public void save(Employee employee) {
		dao.save(employee);
	}
	
	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
}