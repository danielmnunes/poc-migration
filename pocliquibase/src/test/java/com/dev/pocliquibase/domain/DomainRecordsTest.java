package com.dev.pocliquibase.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DomainRecordsTest {

  @Test
  void departmentRecordContract() {
    Department department = new Department(1L, "Engineering");
    Department same = new Department(1L, "Engineering");
    Department different = new Department(2L, "People");

    assertThat(department.id()).isEqualTo(1L);
    assertThat(department.name()).isEqualTo("Engineering");
    assertThat(department).isEqualTo(same).isNotEqualTo(different).isNotEqualTo(null);
    assertThat(department.hashCode()).isEqualTo(same.hashCode());
    assertThat(department.toString()).contains("Engineering");
  }

  @Test
  void employeeRecordContract() {
    Employee employee = new Employee(1L, "Ada Lovelace", 1L);
    Employee same = new Employee(1L, "Ada Lovelace", 1L);
    Employee different = new Employee(2L, "Grace Hopper", 1L);

    assertThat(employee.id()).isEqualTo(1L);
    assertThat(employee.name()).isEqualTo("Ada Lovelace");
    assertThat(employee.departmentId()).isEqualTo(1L);
    assertThat(employee).isEqualTo(same).isNotEqualTo(different).isNotEqualTo(null);
    assertThat(employee.hashCode()).isEqualTo(same.hashCode());
    assertThat(employee.toString()).contains("Ada Lovelace");
  }

  @Test
  void employeeDepartmentRowRecordContract() {
    EmployeeDepartmentRow row = new EmployeeDepartmentRow(1L, "Ada Lovelace", 1L, "Engineering");
    EmployeeDepartmentRow same = new EmployeeDepartmentRow(1L, "Ada Lovelace", 1L, "Engineering");
    EmployeeDepartmentRow different =
        new EmployeeDepartmentRow(2L, "Grace Hopper", 1L, "Engineering");

    assertThat(row.employeeId()).isEqualTo(1L);
    assertThat(row.employeeName()).isEqualTo("Ada Lovelace");
    assertThat(row.departmentId()).isEqualTo(1L);
    assertThat(row.departmentName()).isEqualTo("Engineering");
    assertThat(row).isEqualTo(same).isNotEqualTo(different).isNotEqualTo(null);
    assertThat(row.hashCode()).isEqualTo(same.hashCode());
    assertThat(row.toString()).contains("Engineering");
  }
}
