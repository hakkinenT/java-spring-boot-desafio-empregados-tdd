package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EmployeeDTO;
import com.devsuperior.demo.entities.Department;
import com.devsuperior.demo.entities.Employee;
import com.devsuperior.demo.repositories.DepartmentRepository;
import com.devsuperior.demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> findAll(Pageable pageable){
        Page<Employee> employees = repository.findAll(pageable);
        return employees.map(employee -> new EmployeeDTO(employee));
    }

    @Transactional
    public EmployeeDTO insert(EmployeeDTO dto){
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());

        Department department = departmentRepository.getReferenceById(dto.getDepartmentId());
        employee.setDepartment(department);

        employee = repository.save(employee);
        return new EmployeeDTO(employee);
    }
}
