package ru.text.nastya.web.controllers.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.text.nastya.domain.entities.credential.User;
import ru.text.nastya.domain.services.crud.CrudService;
import ru.text.nastya.domain.services.crud.UserCrudService;
import ru.text.nastya.dto.UserDto;
import ru.text.nastya.dto.mapper.EntityMapper;
import ru.text.nastya.dto.mapper.UserMapper;
import ru.text.nastya.web.controllers.base.AbstractCrudController;

@RestController
@RequestMapping("/user")
public class UserCrudController extends AbstractCrudController<User, UserDto> {

    private final UserCrudService userCrudService;

    private final UserMapper userMapper;

    @Autowired
    public UserCrudController(UserCrudService userCrudService, UserMapper userMapper) {
        this.userCrudService = userCrudService;
        this.userMapper = userMapper;
    }

    @Override
    protected CrudService<User> getCrudService() {
        return userCrudService;
    }

    @Override
    protected EntityMapper<User, UserDto> getEntityMapper() {
        return userMapper;
    }
}
