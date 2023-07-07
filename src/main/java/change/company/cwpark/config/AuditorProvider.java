package change.company.cwpark.config;

import java.util.Optional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class AuditorProvider implements AuditorAware {

  @Override
  public Optional getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails)authentication.getPrincipal();
    return Optional.of(userDetails.getUsername());
  }
}
