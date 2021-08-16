package learn.field_agent.data;

import learn.field_agent.models.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import learn.field_agent.models.Alias;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasJdbcTemplateRepositoryTest {

    @Autowired
    AliasJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindById() {
        List<Alias> actual = repository.findAliasesById(1);
        assertEquals(2, actual.size());
    }

    @Test
    void shouldAdd() {
        Alias alias = new Alias();
        alias.setAliasId(5);
        alias.setName("Test2");
        alias.setPersona("Happy");
        alias.setAgentId(2);
        Alias actual = repository.add(alias);
        assertNotNull(actual);
    }

    @Test
    void shouldUpdate() {
        Alias alias = new Alias();
        alias.setAliasId(42);
        alias.setName("Frankie");
        alias.setPersona("Happy");
        alias.setAgentId(1);
        assertTrue(repository.update(alias));
    }

    @Test
    void shouldDeleteById() {
        assertTrue(repository.deleteById(41));
    }

    Alias makeAlias() {
        Alias alias = new Alias();
        alias.setAliasId(41);
        alias.setName("Franco");
        alias.setPersona("Happy");
        alias.setAgentId(1);
        return alias;
    }

}