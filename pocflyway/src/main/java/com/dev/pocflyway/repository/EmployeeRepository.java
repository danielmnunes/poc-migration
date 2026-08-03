package com.dev.pocflyway.repository;

import com.dev.pocflyway.domain.EmployeeDepartmentRow;
import java.util.List;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

  private final JdbcClient jdbcClient;

  public EmployeeRepository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  public List<EmployeeDepartmentRow> findAllWithDepartmentJoin() {
    return jdbcClient
        .sql(
            """
            SELECT e.id AS employee_id,
                   e.name AS employee_name,
                   d.id AS department_id,
                   d.name AS department_name
            FROM employee e
            JOIN department d ON d.id = e.department_id
            ORDER BY e.id
            """)
        .query(EmployeeDepartmentRow.class)
        .list();
  }

  public List<EmployeeDepartmentRow> findAll() {
    return jdbcClient
        .sql(
            """
            SELECT employee_id, employee_name, department_id, department_name
            FROM v_employee_department
            ORDER BY employee_id
            """)
        .query(EmployeeDepartmentRow.class)
        .list();
  }
}
