package com.dev.pocliquibase.domain;

public record EmployeeDepartmentRow(
    Long employeeId, String employeeName, Long departmentId, String departmentName) {}
