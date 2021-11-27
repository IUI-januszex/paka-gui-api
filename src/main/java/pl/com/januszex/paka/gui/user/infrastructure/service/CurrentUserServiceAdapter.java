package pl.com.januszex.paka.gui.user.infrastructure.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.com.januszex.paka.gui.configuration.security.CurrentUser;
import pl.com.januszex.paka.gui.user.api.dto.BusinessClientDto;
import pl.com.januszex.paka.gui.user.api.dto.ClientDto;
import pl.com.januszex.paka.gui.user.api.dto.UserDto;
import pl.com.januszex.paka.gui.user.api.dto.WorkerDto;
import pl.com.januszex.paka.gui.user.api.service.CurrentUserServicePort;

import java.util.Objects;

@Service
public class CurrentUserServiceAdapter implements CurrentUserServicePort {

    public boolean isAnonymous() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication == null || authentication.getPrincipal().equals("anonymousUser");
    }

    public String getCurrentJwt() {
        return Objects.requireNonNull(getAuthentication()).getCredentials();
    }

    @Override
    public String getId() {
        return Objects.requireNonNull(getAuthentication()).getPrincipal();
    }

    @Override
    public String getUserName() {
        return Objects.requireNonNull(getAuthentication()).getName();
    }

    @Override
    public Class<? extends UserDto> getCurrentUserClass() {
        CurrentUser currentUser = getAuthentication();
        if (currentUser != null && currentUser.getAuthorities() != null) {
            GrantedAuthority grantedAuthority = currentUser.getAuthorities().iterator().next();
            switch (grantedAuthority.getAuthority()) {
                case "ROLE_Courier":
                case "ROLE_Logistician":
                    return WorkerDto.class;
                case "ROLE_ClientInd":
                    return ClientDto.class;
                case "ROLE_ClientBiz":
                    return BusinessClientDto.class;
                default:
                    return UserDto.class;
            }
        }
        return null;
    }

    private CurrentUser getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof CurrentUser) {
            return (CurrentUser) authentication;
        }
        return null;
    }
}
