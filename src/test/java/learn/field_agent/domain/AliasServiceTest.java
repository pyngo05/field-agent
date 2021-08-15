package learn.field_agent.domain;

import learn.field_agent.models.*;
import org.junit.jupiter.api.Test;
import learn.field_agent.data.AliasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasServiceTest {

    @Autowired
    AliasService service;

    @MockBean
    AliasRepository repository;

    @Test
    void shouldAdd() {
        Alias alias = makeAlias();
        Alias mockOut = makeAlias();
        mockOut.setAliasId(1);

        when(repository.add(alias)).thenReturn(mockOut);

        Result<Alias> actual = service.add(alias);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotAddWhenInvalid() {
        Alias alias = makeAlias();
        Alias alias2 = new Alias();
        alias2.setName("TEST100");
        alias2.setAgentId(15);
        alias2.setPersona("");
        alias2.setAgent(null);
        alias2.setAliasId(2);

        Result<Alias> actual = service.add(alias);
        assertEquals(ResultType.SUCCESS, actual.getType());
        Result<Alias> actual2 = service.add(alias2);
        assertNotEquals(ResultType.SUCCESS, actual2.getType());

    }

    @Test
    void shouldUpdate() {
        Alias alias = new Alias();
        alias.setAliasId(1);
        alias.setName("Bob");
        alias.setPersona("Angry");
        alias.setAgentId(100);

        when(repository.update(alias)).thenReturn(true);
        Result<Alias> actual = service.update(alias);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() {
        Alias alias = new Alias();
        alias.setAliasId(1);
        alias.setName(null);
        alias.setPersona("Angry");
        alias.setAgentId(100);

        Result<Alias> actual = service.update(alias);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldDelete() {
        when(repository.deleteById(1)).thenReturn(true);
        assertTrue(service.deleteById(1));
    }

        Alias makeAlias () {
            Alias alias = new Alias();
            alias.setName("TEST100");
            alias.setAgentId(15);
            alias.setPersona("Test Persona");
            alias.setAgent(null);
            alias.setAliasId(1);
            return alias;
        }
    }