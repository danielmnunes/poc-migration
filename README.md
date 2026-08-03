# POC Migration: Flyway vs Liquibase

Sandbox com duas apps Spring Boot **4.1** / Java **25** espelhadas para comparar migrations:

| | `pocflyway` | `pocliquibase` |
|---|---|---|
| Formato | SQL versionado (`V1__…sql`) | YAML declarativo (changesets) |
| Tracking | `flyway_schema_history` | `databasechangelog` / `databasechangeloglock` |
| Starter Boot 4.1 | `spring-boot-starter-flyway` (+ `flyway-database-postgresql`) | `spring-boot-starter-liquibase` |
| Schema | `department` → `employee` (FK) → view `v_employee_department` + seed | Igual |

Acesso a dados: JDBC (`JdbcClient`), sem JPA.

## Pré-requisitos

- Java 25
- Docker (Compose local + Testcontainers nos testes)

## Subir o Postgres e a app

```bash
# Flyway — porta 5432
cd pocflyway
docker compose up -d
./gradlew bootRun

# Liquibase — porta 5433
cd pocliquibase
docker compose up -d
./gradlew bootRun
```

O DataSource vem do `spring-boot-docker-compose` (sem URL hardcoded no perfil default).

## Testes, Spotless e cobertura

```bash
cd pocflyway && ./gradlew spotlessApply check
cd ../pocliquibase && ./gradlew spotlessApply check
```

- Spotless (`googleJavaFormat`) roda no `check`
- JaCoCo exige **100%** line + branch
- Testes usam Testcontainers PostgreSQL + `@ServiceConnection`
