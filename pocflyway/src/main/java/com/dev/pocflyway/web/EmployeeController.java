package com.dev.pocflyway.web;

import com.dev.pocflyway.domain.EmployeeDepartmentRow;
import com.dev.pocflyway.repository.EmployeeRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

  private final EmployeeRepository employeeRepository;

  public EmployeeController(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @GetMapping
  public List<EmployeeDepartmentRow> findAll() {
    return employeeRepository.findAll();
  }
}
