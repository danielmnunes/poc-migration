package com.dev.pocliquibase;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
class EmployeeControllerIT {

  @Autowired private MockMvc mockMvc;

  @Test
  void findAllReturnsViewRows() throws Exception {
    mockMvc
        .perform(get("/employees"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(3))
        .andExpect(jsonPath("$[0].employeeName").value("Ada Lovelace"))
        .andExpect(jsonPath("$[0].departmentName").value("Engineering"))
        .andExpect(jsonPath("$[2].employeeName").value("Alan Turing"))
        .andExpect(jsonPath("$[2].departmentName").value("People"));
  }
}
