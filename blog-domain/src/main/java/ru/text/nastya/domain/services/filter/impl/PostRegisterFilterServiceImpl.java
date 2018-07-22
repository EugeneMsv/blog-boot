package ru.text.nastya.domain.services.filter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.text.nastya.domain.entities.PostRegister;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.domain.repositories.PostRegisterRepository;
import ru.text.nastya.domain.services.filter.PostRegisterFilterService;
import ru.text.nastya.filters.PostRegisterFilter;
import ru.text.nastya.profiling.Profiling;

@Profiling(showArgs = true, timeRecord = true, showOutput = true)
@Service
public class PostRegisterFilterServiceImpl extends FilteredEntityServiceImpl<PostRegister, PostRegisterFilter> implements PostRegisterFilterService {

    private final PostRegisterRepository postRegisterRepository;

    @Autowired
    public PostRegisterFilterServiceImpl(PostRegisterRepository postRegisterRepository) {
        this.postRegisterRepository = postRegisterRepository;
    }

    @Override
    protected PersistedEntityRepository<PostRegister> getRepository() {
        return postRegisterRepository;
    }
}
