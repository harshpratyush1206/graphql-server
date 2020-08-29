package org.oaknorth.graphql.server.entity.audit;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

  // TODO: Will add security context holder after security implementation

  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.of("anonymous");
  }
}
