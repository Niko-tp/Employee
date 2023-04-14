package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Repository
public class EmployeeDao implements BaseDao<Employee> {
	@Autowired
	EmployeeRepository repository;
	
	@Override
	public List<Employee> findAll() {
		return repository.findAll();
	}

	@Override
	public Employee findById(Integer id) throws DataNotFoundException {
		return repository.findById(id).orElseThrow(() -> new DataNotFoundException());
	}

	@Override
	public void save(Employee employee) {
		repository.save(employee);
	}

	@Override
	public void deleteById(Integer id) {
		try {
			Employee employee = this.findById(id);
			this.repository.deleteById(employee.getId());
		} catch (DataNotFoundException e) {
			System.out.println("no data");
		}
	}
}