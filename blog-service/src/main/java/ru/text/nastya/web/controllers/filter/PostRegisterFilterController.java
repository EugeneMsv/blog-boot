package ru.text.nastya.web.controllers.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.text.nastya.domain.entities.PostRegister;
import ru.text.nastya.domain.services.filter.FilteredEntityService;
import ru.text.nastya.domain.services.filter.PostRegisterFilterService;
import ru.text.nastya.dto.PostRegisterDto;
import ru.text.nastya.dto.mapper.EntityMapper;
import ru.text.nastya.dto.mapper.PostRegisterMapper;
import ru.text.nastya.filters.PostRegisterFilter;
import ru.text.nastya.web.controllers.base.AbstractFilterController;

import static ru.text.nastya.web.controllers.filter.PostRegisterFilterController.MODEL_PARAMETER;

@Controller
@RequestMapping(MODEL_PARAMETER + "/filter")
public class PostRegisterFilterController extends AbstractFilterController<PostRegister, PostRegisterDto, PostRegisterFilter> {

    public static final String MODEL_PARAMETER = "postRegister";

    private final PostRegisterFilterService postRegisterFilterService;

    private final PostRegisterMapper postRegisterMapper;

    @Autowired
    public PostRegisterFilterController(PostRegisterFilterService postRegisterFilterService, PostRegisterMapper postRegisterMapper) {
        this.postRegisterFilterService = postRegisterFilterService;
        this.postRegisterMapper = postRegisterMapper;
    }

    @Override
    protected FilteredEntityService<PostRegister, PostRegisterFilter> getFilteredEntityService() {
        return postRegisterFilterService;
    }

    @Override
    protected EntityMapper<PostRegister, PostRegisterDto> getEntityMapper() {
        return postRegisterMapper;
    }

    @Override
    protected String getModelParameter() {
        return MODEL_PARAMETER;
    }
}
