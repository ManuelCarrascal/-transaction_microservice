package emazon.transaction.ports.persistence.mysql.adapter;

import emazon.transaction.domain.spi.IAuthenticationPersistencePort;
import emazon.transaction.infrastructure.configuration.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class AuthenticationAdapter implements IAuthenticationPersistencePort {
    private final JwtService jwtService;

    @Override
    public Long getAuthenticatedUserId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Long.valueOf( userDetails.getUsername());
    }
}
