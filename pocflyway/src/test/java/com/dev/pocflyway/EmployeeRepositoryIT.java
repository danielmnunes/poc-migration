package com.dev.pocflyway;

import static org.assertj.core.api.Assertions.assertThat;

import com.dev.pocflyway.domain.EmployeeDepartmentRow;
import com.dev.pocflyway.repository.EmployeeRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class EmployeeRepositoryIT {

  @Autowired private EmployeeRepository employeeRepository;

  @Test
  void findAllWithDepartmentJoinReturnsSeededRows() {
    List<EmployeeDepartmentRow> rows = employeeRepository.findAllWithDepartmentJoin();

    assertThat(rows).hasSize(3);
    assertThat(rows.getFirst())
        .isEqualTo(new EmployeeDepartmentRow(1L, "Ada Lovelace", 1L, "Engineering"));
    assertThat(rows.get(1))
        .isEqualTo(new EmployeeDepartmentRow(2L, "Grace Hopper", 1L, "Engineering"));
    assertThat(rows.get(2)).isEqualTo(new EmployeeDepartmentRow(3L, "Alan Turing", 2L, "People"));
  }

  @Test
  void findAllFromViewMatchesJoin() {
    List<EmployeeDepartmentRow> fromJoin = employeeRepository.findAllWithDepartmentJoin();
    List<EmployeeDepartmentRow> fromView = employeeRepository.findAll();

    assertThat(fromView).containsExactlyElementsOf(fromJoin);
  }
}
