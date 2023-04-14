package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.dao.BaseDao;
import com.example.demo.entity.Department;

@Service
public class DepartmentService implements BaseService<Department> {
	@Autowired
	private BaseDao<Department> dao;
	
	@Override
	public List<Department> findAll() {
		return dao.findAll();
	}
	
	@Override
	public Department findById(Integer deptId) throws DataNotFoundException {
		return dao.findById(deptId);
	}
	
	@Override
	public void save(Department department) {
		dao.save(department);
	}
	
	@Override
	public void deleteById(Integer deptId) {
		dao.deleteById(deptId);
	}
}