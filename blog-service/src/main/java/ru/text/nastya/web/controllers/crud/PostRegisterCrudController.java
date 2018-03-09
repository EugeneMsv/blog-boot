package ru.text.nastya.web.controllers.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.text.nastya.domain.entities.PostRegister;
import ru.text.nastya.domain.services.crud.CrudService;
import ru.text.nastya.domain.services.crud.PostRegisterCrudService;
import ru.text.nastya.dto.PostRegisterDto;
import ru.text.nastya.dto.mapper.EntityMapper;
import ru.text.nastya.dto.mapper.PostRegisterMapper;
import ru.text.nastya.web.controllers.base.AbstractCrudController;

@RestController
@RequestMapping("/postRegister")
public class PostRegisterCrudController extends AbstractCrudController<PostRegister, PostRegisterDto> {

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
}
