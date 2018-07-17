package ru.text.nastya.web.controllers.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.text.nastya.domain.entities.PostRegister;
import ru.text.nastya.domain.services.crud.CrudService;
import ru.text.nastya.domain.services.crud.PostRegisterCrudService;
import ru.text.nastya.dto.PostRegisterDto;
import ru.text.nastya.dto.mapper.EntityMapper;
import ru.text.nastya.dto.mapper.PostRegisterMapper;
import ru.text.nastya.web.controllers.base.AbstractCrudController;

import static ru.text.nastya.web.controllers.crud.PostRegisterCrudController.MODEL_PARAMETER;

@Controller
@RequestMapping(MODEL_PARAMETER)
public class PostRegisterCrudController extends AbstractCrudController<PostRegister, PostRegisterDto> {


    public static final String MODEL_PARAMETER = "postRegister";

    private final PostRegisterCrudService postRegisterCrudService;

    private final PostRegisterMapper postRegisterMapper;

    @Autowired
    public PostRegisterCrudController(PostRegisterCrudService postRegisterCrudService,
                                      PostRegisterMapper postRegisterMapper) {
        this.postRegisterCrudService = postRegisterCrudService;
        this.postRegisterMapper = postRegisterMapper;
    }

    @Override
    protected CrudService<PostRegister> getCrudService() {
        return postRegisterCrudService;
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
