package com.dev.pocliquibase;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.autoconfigure.JdbcConnectionDetails;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class PocliquibaseApplicationTests {

  @Autowired private JdbcConnectionDetails connectionDetails;

  @Test
  void contextLoads() {}

  @Test
  void mainStartsApplication() {
    assertThatCode(
            () ->
                PocliquibaseApplication.main(
                    new String[] {
                      "--spring.main.web-application-type=none",
                      "--spring.docker.compose.enabled=false",
                      "--spring.datasource.url=" + connectionDetails.getJdbcUrl(),
                      "--spring.datasource.username=" + connectionDetails.getUsername(),
                      "--spring.datasource.password=" + connectionDetails.getPassword()
                    }))
        .doesNotThrowAnyException();
  }
}
