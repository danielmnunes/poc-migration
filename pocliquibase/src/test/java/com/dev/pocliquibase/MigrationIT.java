package com.dev.pocliquibase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.simple.JdbcClient;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class MigrationIT {

  @Autowired private JdbcClient jdbcClient;

  @Test
  void tablesForeignKeyAndViewExist() {
    Integer departmentTables =
        jdbcClient
            .sql(
                """
                SELECT COUNT(*) FROM information_schema.tables
                WHERE table_schema = 'public' AND table_name = 'department'
                """)
            .query(Integer.class)
            .single();
    Integer employeeTables =
        jdbcClient
            .sql(
                """
                SELECT COUNT(*) FROM information_schema.tables
                WHERE table_schema = 'public' AND table_name = 'employee'
                """)
            .query(Integer.class)
            .single();
    Integer foreignKeys =
        jdbcClient
            .sql(
                """
                SELECT COUNT(*) FROM information_schema.table_constraints
                WHERE table_schema = 'public'
                  AND table_name = 'employee'
                  AND constraint_type = 'FOREIGN KEY'
                  AND constraint_name = 'fk_employee_department'
                """)
            .query(Integer.class)
            .single();
    Integer views =
        jdbcClient
            .sql(
                """
                SELECT COUNT(*) FROM pg_views
                WHERE schemaname = 'public' AND viewname = 'v_employee_department'
                """)
            .query(Integer.class)
            .single();

    assertThat(departmentTables).isEqualTo(1);
    assertThat(employeeTables).isEqualTo(1);
    assertThat(foreignKeys).isEqualTo(1);
    assertThat(views).isEqualTo(1);
  }
}
