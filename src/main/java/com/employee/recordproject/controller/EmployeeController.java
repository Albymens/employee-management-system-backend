package com.employee.recordproject.controller;

import com.employee.recordproject.exceptions.ResourceNotFoundException;
import com.employee.recordproject.repository.EmployeeRepository;
import com.employee.recordproject.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> fetchAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        Employee employee1 = employeeRepository.save(employee);
        return ResponseEntity.ok(employee1);
    }

    @GetMapping("employees/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable("id") Integer Id){
        Employee employee = employeeRepository.findById(Id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found"));
        return ResponseEntity.ok(employee);
    }

    @PutMapping("employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Integer Id, @RequestBody Employee updateEmployee){
        Employee employee = employeeRepository
                .findById(Id)
                .orElseThrow(()-> new ResourceNotFoundException("employee Not Found"));

        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setEmail(updateEmployee.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable("id") Integer Id){
        Employee employee = employeeRepository.findById(Id).orElseThrow(()-> new ResourceNotFoundException("Employee not found"));
        employeeRepository.delete(employee);
        Map<String, Boolean> deleteEmployee = new HashMap<>();
        deleteEmployee.put("Employee Succesfully Deleted", Boolean.TRUE);

        return ResponseEntity.ok(deleteEmployee);
    }
}
