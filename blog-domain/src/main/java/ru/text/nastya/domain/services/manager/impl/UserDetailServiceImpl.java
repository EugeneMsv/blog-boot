package ru.text.nastya.domain.services.manager.impl;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.text.nastya.domain.entities.credential.User;
import ru.text.nastya.domain.entities.credential.UserStatus;
import ru.text.nastya.domain.services.crud.UserCrudService;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    private final UserCrudService userCrudService;

    @Autowired
    public UserDetailServiceImpl(UserCrudService userCrudService) {
        this.userCrudService = userCrudService;
    }

    @Override
    public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
        UserDetails userDetails = userCrudService.findBySsoId(ssoId)
                .map(this::toUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User with ssoId=" + ssoId + " not found"));
        logger.debug("Loaded user with ssoId={}, userDetails={}", ssoId, userDetails);
        return userDetails;
    }

    private UserDetails toUserDetails(User user) {
        Validate.notNull(user, "User mustn't be null");
        return new org.springframework.security.core.userdetails.User(
                user.getSsoId(),
                user.getPassword(),
                UserStatus.ACTIVE == user.getStatus(),
                true,
                true,
                UserStatus.BANNED != user.getStatus(),
                getGrantedAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(User user) {
        return user.getGroups().stream()
                .flatMap(userGroup -> userGroup.getRoles().stream())
                .map(userRole -> new SimpleGrantedAuthority("ROLE_" + userRole.getCode()))
                .collect(Collectors.toList());
    }
}
