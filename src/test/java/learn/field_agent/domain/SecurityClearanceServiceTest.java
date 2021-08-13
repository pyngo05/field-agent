package learn.field_agent.domain;

import learn.field_agent.models.Agency;
import learn.field_agent.models.Location;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import learn.field_agent.data.SecurityClearanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SecurityClearanceServiceTest {

    @Autowired
    SecurityClearanceService service;

    @MockBean
    SecurityClearanceRepository securityClearanceRepository;

    @Test
    void shouldAdd() {
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setName("TEST");
        SecurityClearance mockOut = new SecurityClearance(5, "TEST");

        when(securityClearanceRepository.add(securityClearance)).thenReturn(mockOut);

        Result<SecurityClearance> actual = service.add(securityClearance);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotAddWhenInvalid() {
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setSecurityClearanceId(0);
        securityClearance.setName(null);
        Result<SecurityClearance> actual = service.add(securityClearance);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldUpdate() {
        SecurityClearance securityClearance = makeSecurityClearance();
        securityClearance.setSecurityClearanceId(1);

        when(securityClearanceRepository.update(securityClearance)).thenReturn(true);

        Result<SecurityClearance> actual = service.update(securityClearance);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() {
        SecurityClearance securityClearance = makeSecurityClearance();
        Result<SecurityClearance> actual = service.update(securityClearance);
        assertEquals(ResultType.INVALID, actual.getType());

        securityClearance = makeSecurityClearance();
        securityClearance.setName(null);
        actual = service.update(securityClearance);
        assertEquals(ResultType.INVALID, actual.getType());

    }

    //TODO FIX BELOW DELETE TESTS
//    @Test
//    void shouldNotDeleteSecurityClearanceWithAgencyAgent() {
//        when(securityClearanceRepository.deleteById(10)).thenReturn(false);
//        assertFalse(service.deleteById(10));
//    }
//
//    @Test
//    void shouldDelete() {
//        when(securityClearanceRepository.deleteById(2)).thenReturn(true);
//        assertTrue(service.deleteById(2));
//    }

    SecurityClearance makeSecurityClearance() {
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setName("TESTING");
        return securityClearance;
    }
}