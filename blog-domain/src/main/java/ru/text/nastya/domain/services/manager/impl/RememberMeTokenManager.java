package ru.text.nastya.domain.services.manager.impl;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;
import ru.text.nastya.domain.entities.credential.RememberMeToken;
import ru.text.nastya.domain.repositories.RememberMeTokenRepository;
import ru.text.nastya.exception.DataNotFoundException;
import ru.text.nastya.util.DateUtils;

import java.util.Date;

@Service
public class RememberMeTokenManager implements PersistentTokenRepository {

    private static final Logger logger = LoggerFactory.getLogger(RememberMeTokenManager.class);

    private final RememberMeTokenRepository rememberMeTokenRepository;

    @Autowired
    public RememberMeTokenManager(RememberMeTokenRepository rememberMeTokenRepository) {
        this.rememberMeTokenRepository = rememberMeTokenRepository;
    }

    private static RememberMeToken from(PersistentRememberMeToken token) {
        Validate.notNull(token, "PersistentRememberMeToken must be set");
        RememberMeToken rememberMeToken = new RememberMeToken();
        rememberMeToken.setLastUsed(DateUtils.valueOf(token.getDate()));
        rememberMeToken.setSeries(token.getSeries());
        rememberMeToken.setTokenValue(token.getTokenValue());
        rememberMeToken.setUsername(token.getUsername());
        return rememberMeToken;
    }

    private static PersistentRememberMeToken to(RememberMeToken savedToken) {
        Validate.notNull(savedToken, "RememberMeToken must be set");
        return new PersistentRememberMeToken(
                savedToken.getUsername(),
                savedToken.getSeries(),
                savedToken.getTokenValue(),
                DateUtils.valueOf(savedToken.getLastUsed()));
    }

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        rememberMeTokenRepository.save(from(token));
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        RememberMeToken savedToken = rememberMeTokenRepository.findBySeries(series)
                .orElseThrow(DataNotFoundException::new);
        savedToken.setTokenValue(tokenValue);
        savedToken.setLastUsed(DateUtils.valueOf(lastUsed));
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return rememberMeTokenRepository.findBySeries(seriesId)
                .map(RememberMeTokenManager::to)
                .orElse(null);
    }

    @Override
    public void removeUserTokens(String username) {
        rememberMeTokenRepository.findByUsername(username)
                .ifPresent(savedToken -> rememberMeTokenRepository.delete(savedToken.getId()));
    }
}
