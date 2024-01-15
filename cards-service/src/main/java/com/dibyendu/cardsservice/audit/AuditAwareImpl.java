package com.dibyendu.cardsservice.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")

// because created by and updated by fields are type of string
public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {

        // for created by and updated by
        return Optional.of("DIBYENDU_MS");
    }
}
