package com.hrishi.api.util;


import com.hrishi.api.dao.EmployeeRepository;
import com.hrishi.api.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Employee("Hrishi Kesav", "CEO")));
            log.info("Preloading " + repository.save(new Employee("Some One", "Admin")));
        };
    }
}
