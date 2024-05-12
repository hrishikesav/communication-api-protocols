package com.hrishi.api.controller;

import com.hrishi.api.dao.EmployeeRepository;
import com.hrishi.api.model.Employee;
import com.hrishi.api.service.EmployeeNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    List<Employee> getEmployees() {
        return repository.findAll();
    }

    @GetMapping("/employees/{id}")
    EntityModel<Employee> getEmployee(@PathVariable("id") Long id) {
        Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return EntityModel.of(employee,
                linkTo(methodOn(EmployeeController.class).getEmployee(id)).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).getEmployees()).withRel("employees"));
    }

    @PostMapping("/employees")
    Employee addEmployee(@RequestBody Employee newEmp) {
        return repository.save(newEmp);
    }

    @PutMapping("/employees/{id}")
    Employee modEmployee(@RequestBody Employee replaceEmp, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(replaceEmp.getName());
                    employee.setRole(replaceEmp.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    replaceEmp.setId(id);
                    return repository.save(replaceEmp);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
