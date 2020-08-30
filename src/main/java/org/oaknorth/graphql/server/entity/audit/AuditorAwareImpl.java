package org.oaknorth.graphql.server.entity.audit;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.oaknorth.graphql.server.security.JWTUserDetails;
import org.oaknorth.graphql.server.security.SecurityUtil;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String> {

  @NotNull
  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.of(SecurityUtil.getAuthorId());
  }
}
