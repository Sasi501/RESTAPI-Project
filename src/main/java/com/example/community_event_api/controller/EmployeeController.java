package com.example.community_event_api.controller;

import com.example.community_event_api.entity.Employee;
import com.example.community_event_api.repository.service.EmployeeService;  // Updated import statement
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;  // Updated reference to EmployeeService

    // Create new employee
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    // Get all employees with Pagination and Sorting
    @GetMapping
    public ResponseEntity<Page<Employee>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return new ResponseEntity<>(employeeService.getAllEmployees((Pageable) pageable), HttpStatus.OK);
    }

    // // Get employee by ID
    // @GetMapping("/{id}")
    // public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {  // Updated to Long
    //     return employeeService.getEmployeeById(id)
    //             .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
    //             .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    // }
    // @GetMapping("/employees")
    // public ResponseEntity<Page<Employee>> getAllEmployees(Pageable pageable) {
    // return new ResponseEntity<>(employeeService.getAllEmployees(pageable), HttpStatus.OK);
    // }


    // Get employees by designation (JPQL Query)
    @GetMapping("/designation/{designation}")
    public ResponseEntity<List<Employee>> getEmployeesByDesignation(@PathVariable String designation) {
        return new ResponseEntity<>(employeeService.findByDesignation(designation), HttpStatus.OK);
    }

    // Update employee details
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {  // Updated to Long
        try {
            return new ResponseEntity<>(employeeService.updateEmployee(id, updatedEmployee), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {  // Updated to Long
        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
