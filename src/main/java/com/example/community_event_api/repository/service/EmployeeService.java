package com.example.community_event_api.repository.service;

import com.example.community_event_api.entity.Employee;
import com.example.community_event_api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable; // ✅ Correct import
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Method to save employee
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Method to get all employees
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
    
    // In EmployeeService
    public List<Employee> findByDesignation(String designation) {
    return ((EmployeeService) employeeRepository).findByDesignation(designation);  // Add query method to repository
    }

    public Page<Employee> getEmployees(int page, int size) {/////////////////////////////////////////////////////////////
    Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending()); // ✅ Correct way to create Pageable
    return employeeRepository.findAll(pageable);
    }



    // Method to get employee by ID
    public Optional<Employee> getEmployeeById(Long id) {  // Updated to Long
        return employeeRepository.findById(id);
    }

    // Method to update employee
    public Employee updateEmployee(Long id, Employee updatedEmployee) {  // Updated to Long
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(updatedEmployee.getName());
                    employee.setPosition(updatedEmployee.getPosition());
                    employee.setDepartment(updatedEmployee.getDepartment());
                    employee.setSalary(updatedEmployee.getSalary());
                    return employeeRepository.save(employee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // Method to delete employee
    public void deleteEmployee(Long id) {  // Updated to Long
        employeeRepository.deleteById(id);
    }

    public Object getAllEmployees(PageRequest pageable) {
       
        throw new UnsupportedOperationException("Unimplemented method 'getAllEmployees'");
    }
}
