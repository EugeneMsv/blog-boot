package ru.text.nastya.web.controllers.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.text.nastya.domain.entities.credential.UserRole;
import ru.text.nastya.domain.services.crud.CrudService;
import ru.text.nastya.domain.services.crud.UserRoleCrudService;
import ru.text.nastya.dto.UserRoleDto;
import ru.text.nastya.dto.mapper.EntityMapper;
import ru.text.nastya.dto.mapper.UserRoleMapper;
import ru.text.nastya.web.controllers.base.AbstractCrudController;

@RestController
@RequestMapping("/userRole")
public class UserRoleCrudController extends AbstractCrudController<UserRole, UserRoleDto> {

    private final UserRoleCrudService userRoleCrudService;

    private final UserRoleMapper userRoleMapper;

    @Autowired
    public UserRoleCrudController(UserRoleCrudService userRoleCrudService, UserRoleMapper userRoleMapper) {
        this.userRoleCrudService = userRoleCrudService;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    protected CrudService<UserRole> getCrudService() {
        return userRoleCrudService;
    }

    @Override
    protected EntityMapper<UserRole, UserRoleDto> getEntityMapper() {
        return userRoleMapper;
    }
}
