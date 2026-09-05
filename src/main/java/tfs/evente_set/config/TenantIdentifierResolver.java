package tfs.evente_set.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;
import tfs.evente_set.security.TenantContext;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver<String> {

    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenantId = TenantContext.getCurrentTenant();
        if (tenantId != null) {
            return tenantId;
        }
        return "default_tenant";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
