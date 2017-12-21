package ru.text.nastya.domain.repositories;

import ru.text.nastya.domain.entities.credential.RememberMeToken;

import java.util.Optional;

public interface RememberMeTokenRepository extends PersistedEntityRepository<RememberMeToken> {

    Optional<RememberMeToken> findBySeries(String series);


    Optional<RememberMeToken> findByUsername(String userName);
}
