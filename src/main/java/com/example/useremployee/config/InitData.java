package com.example.useremployee.config;

import com.example.useremployee.module.Employee;
import com.example.useremployee.module.Gender;
import com.example.useremployee.module.User;
import com.example.useremployee.repository.EmployeeRepository;
import com.example.useremployee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = userRepository.save(new User("mikkel@gmail.com", "1234" ));
        userRepository.save(new User("børge@gmail.com", "kode" ));
        userRepository.save(new User("stimon@gmail.com", "6969" ));

        Employee emp1 = new Employee();
        emp1.setName("Mikkel");
        emp1.setGender(Gender.MALE);
        emp1.setVegetarian(false);
        emp1.setBorn(LocalDateTime.of(1999, 7, 17, 12, 0, 0));
        emp1.setUser(user1);

        employeeRepository.save(emp1);
    }
}
