CREATE VIEW v_employee_department AS
SELECT
    e.id AS employee_id,
    e.name AS employee_name,
    d.id AS department_id,
    d.name AS department_name
FROM employee e
JOIN department d ON d.id = e.department_id;
