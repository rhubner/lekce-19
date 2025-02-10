package cz.robotdreams.java.lekce18.databaze;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.*;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class SeznamStudentuTest {

    static Logger log = LoggerFactory.getLogger(SeznamStudentuTest.class);

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
        log.info("Postgresql nastartovan s parametry jdbsUrl: {} username: {}, password: {}", postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword() );
        createSchema();
    }

    private static void createSchema() {
        try(Connection con = DriverManager.getConnection(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
            PreparedStatement createTable = con.prepareStatement("""
                create table seznam_studentu (
                    id serial primary key not null ,
                    first_name varchar(255) not null,
                    last_name varchar(255) not null,
                    email varchar(255) not null)""");
            PreparedStatement insert = con.prepareStatement("""
                insert into seznam_studentu (first_name, last_name, email) values
                ('Jan', 'Novak', 'jnovak@example.cz'),
                ('Petr', 'Novak', 'pnovak@example.cz'),
                ('Roman', 'Novak', 'rnovak@example.cz'),
                ('Bozena', 'Novakova', 'bnovakova@example.cz')""");
        ) {
            createTable.execute();
            long insertedRows = insert.executeUpdate();

            log.info("Databaze inicializovana, vlozeno {} hodnot", insertedRows);
        } catch (SQLException e) {
            log.warn("Chyba inicializace databaze", e);
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @Test
    public void testSeznamStudentu() {
        SeznamStudentu seznamStudentu = new SeznamStudentu(
                postgres.getJdbcUrl(),
                postgres.getUsername(),
                postgres.getPassword()
        );

        List<Student> s = seznamStudentu.getStudents();

        assertThat(s.size()).isEqualTo(4);
        assertThat(s).contains(new Student("Jan", "Novak", "jnovak@example.cz"));

    }
}