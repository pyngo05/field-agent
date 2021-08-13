package learn.field_agent.domain;

import learn.field_agent.data.AgencyAgentRepository;
import learn.field_agent.data.AgencyRepository;
import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.Agency;
import learn.field_agent.models.AgencyAgent;
import learn.field_agent.models.Agent;
import learn.field_agent.models.SecurityClearance;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class SecurityClearanceService {

    private final SecurityClearanceRepository securityClearanceRepository;
    private final AgencyAgentRepository agencyAgentRepository;

    public SecurityClearanceService(SecurityClearanceRepository securityClearanceRepository, AgencyAgentRepository agencyAgentRepository) {
        this.securityClearanceRepository = securityClearanceRepository;
        this.agencyAgentRepository = agencyAgentRepository;
    }

    // Find all security clearances
    public List<SecurityClearance> findAll() {
        return securityClearanceRepository.findAll();
    }

    // Find all security clearances
    public List<AgencyAgent> findAllAgencyAgent() {
        return agencyAgentRepository.findNotNullSecurityClearanceId();
    }

    // Find a security clearance by its identifier
    public SecurityClearance findById(int securityClearanceId) {
        return securityClearanceRepository.findById(securityClearanceId);
    }

    // Add a security clearance
    public Result<SecurityClearance> add(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = validate(securityClearance);
        if (!result.isSuccess()) {
            return result;
        }

        if (securityClearance.getSecurityClearanceId() != 0) {
            result.addMessage("Security Clearance ID cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        securityClearance = securityClearanceRepository.add(securityClearance);
        result.setPayload(securityClearance);
        return result;
    }

    // Update an existing security clearance
    public Result<SecurityClearance> update(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = validate(securityClearance);
        if (!result.isSuccess()) {
            return result;
        }

        if (securityClearance.getSecurityClearanceId() <= 0) {
            result.addMessage("Security Clearance ID must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!securityClearanceRepository.update(securityClearance)) {
            String msg = String.format("Security Clearance ID: %s, not found", securityClearance.getSecurityClearanceId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    //Delete a security clearance. (This requires a strategy. It's probably not
    // appropriate to delete agency_agent records that depend on a security clearance.
    // Only allow deletion if a security clearance key isn't referenced.)
    public boolean deleteById(int securityClearanceId) {
        List<AgencyAgent> allNotNull = findAllAgencyAgent();
        for (AgencyAgent agencyAgent : allNotNull) {
            if (securityClearanceId == agencyAgent.getSecurityClearance().getSecurityClearanceId()) {
                continue;
            }
        }
        return securityClearanceRepository.deleteById(securityClearanceId);
    }

    private Result<SecurityClearance> validate(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = new Result<>();
        if (securityClearance == null) {
            result.addMessage("Security Clearance cannot be null", ResultType.INVALID);
            return result;
        }

        // Security clearance name is required
        if (Validations.isNullOrBlank(securityClearance.getName())) {
            result.addMessage("Security Clearance Name is required", ResultType.INVALID);
        }

        // Name cannot be duplicated
        List<SecurityClearance> allSecurityClearances = findAll();
        IntStream.range(0, allSecurityClearances.toArray().length).filter(i ->
                securityClearance.getName().equals(allSecurityClearances.get(i).getName())).forEach(i ->
                result.addMessage("Security Clearance Name cannot be duplicated", ResultType.INVALID));
        return result;
    }
}