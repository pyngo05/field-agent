package learn.field_agent.data;

import learn.field_agent.models.Location;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SecurityClearanceJdbcTemplateRepositoryTest {

    final static int NEXT_SECURITY_CLEARANCE_ID = 4;

    @Autowired
    SecurityClearanceJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<SecurityClearance> securityClearances = repository.findAll();
        assertNotNull(securityClearances);
        assertTrue(securityClearances.size() > 0);
    }

    @Test
    void shouldFindById() {
        SecurityClearance secret = new SecurityClearance(1, "Secret");
        SecurityClearance topSecret = new SecurityClearance(2, "Top Secret");

        SecurityClearance actual = repository.findById(1);
        assertEquals(secret, actual);

        actual = repository.findById(10);
        assertEquals(null, actual);
    }

    @Test
    void shouldAddValid() {
        SecurityClearance securityClearance = makeSecurityClearance();
        SecurityClearance actual = repository.add(securityClearance);
        assertNotNull(actual);
        assertEquals(NEXT_SECURITY_CLEARANCE_ID, actual.getSecurityClearanceId());
    }

    @Test
    void shouldUpdateExisting() {
        SecurityClearance securityClearance = makeSecurityClearance();
        securityClearance.setSecurityClearanceId(3);
        assertTrue(repository.update(securityClearance));

    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(5000));
    }

    SecurityClearance makeSecurityClearance() {
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setName("Test Security Clearance");
        return securityClearance;
    }

}