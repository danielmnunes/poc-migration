CREATE TABLE employee (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department_id BIGINT NOT NULL,
    CONSTRAINT fk_employee_department FOREIGN KEY (department_id) REFERENCES department (id)
);
