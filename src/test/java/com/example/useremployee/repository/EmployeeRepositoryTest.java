package com.example.useremployee.repository;

import com.example.useremployee.module.Employee;
import org.hibernate.JDBCException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Test
    void testAtLeastOneMikkel() {
        List<Employee> employees = employeeRepository.findEmployeeByName("Mikkel");
        assertTrue(employees.size() > 0);
    }

    @Test
    void testDeleteEmployee(){
        List<Employee> employees = employeeRepository.findEmployeeByName("Mikkel");
        Employee emp1 = employees.get(0);
        //userRepository.delete(emp1.getUser());
        assertThrows(DataIntegrityViolationException.class, () -> userRepository.delete(emp1.getUser()));
    }
}