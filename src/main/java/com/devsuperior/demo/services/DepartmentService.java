package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.DepartmentDTO;
import com.devsuperior.demo.entities.Department;
import com.devsuperior.demo.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    @Transactional(readOnly = true)
    public List<DepartmentDTO> findAll(){
        List<Department> departments = repository.findAll(Sort.by("name"));
        return departments.stream().map(department -> new DepartmentDTO(department)).collect(Collectors.toList());
    }
}
