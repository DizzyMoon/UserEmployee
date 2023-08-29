package com.example.useremployee.controller;

import com.example.useremployee.module.Employee;
import com.example.useremployee.module.User;
import com.example.useremployee.repository.EmployeeRepository;
import com.example.useremployee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeRestController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }


    @GetMapping("/employee/{name}")
    public List<Employee> getEmployee(@PathVariable String name){
        return employeeRepository.findEmployeeByName(name);
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee postEmployee(@RequestBody Employee employee){
        System.out.println(employee);
        return employeeRepository.save(employee);
    }

    @PutMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Employee> putEmployee(@PathVariable int id, @RequestBody Employee employee){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()){
            employee.setId(id);
            employeeRepository.save(employee);
            return ResponseEntity.ok(employee);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteEmployee(@PathVariable int id){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()){
            employeeRepository.deleteById(id);
            return ResponseEntity.ok("Employee deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
    }

}
