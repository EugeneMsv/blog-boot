package ru.text.nastya.web.controllers.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.text.nastya.domain.entities.credential.UserGroup;
import ru.text.nastya.domain.services.crud.CrudService;
import ru.text.nastya.domain.services.crud.UserGroupCrudService;
import ru.text.nastya.dto.UserGroupDto;
import ru.text.nastya.dto.mapper.EntityMapper;
import ru.text.nastya.dto.mapper.UserGroupMapper;
import ru.text.nastya.web.controllers.base.AbstractCrudController;

@RestController
@RequestMapping("/userGroup")
public class UserGroupCrudController extends AbstractCrudController<UserGroup, UserGroupDto> {

    private final UserGroupCrudService userGroupCrudService;

    private final UserGroupMapper userGroupMapper;

    @Autowired
    public UserGroupCrudController(UserGroupCrudService userGroupCrudService, UserGroupMapper userGroupMapper) {
        this.userGroupCrudService = userGroupCrudService;
        this.userGroupMapper = userGroupMapper;
    }

    @Override
    protected CrudService<UserGroup> getCrudService() {
        return userGroupCrudService;
    }

    @Override
    protected EntityMapper<UserGroup, UserGroupDto> getEntityMapper() {
        return userGroupMapper;
    }
}
