package ru.text.nastya.domain.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import ru.text.nastya.domain.entities.base.Dictionary;

import java.util.Optional;

@NoRepositoryBean
public interface DictionaryRepository<T extends Dictionary> extends PersistedEntityRepository<T> {

    Optional<T> findByCodeIgnoreCase(String code);
}
