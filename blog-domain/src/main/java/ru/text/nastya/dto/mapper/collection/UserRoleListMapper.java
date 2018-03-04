package ru.text.nastya.dto.mapper.collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.text.nastya.domain.entities.credential.UserRole;
import ru.text.nastya.dto.UserRoleDto;
import ru.text.nastya.dto.mapper.EntityMapper;
import ru.text.nastya.dto.mapper.UserRoleMapper;

import java.util.List;

@Component
public class UserRoleListMapper extends ListMapper<UserRole, UserRoleDto> {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    protected EntityMapper<UserRole, UserRoleDto> getEntityMapper() {
        return userRoleMapper;
    }

    @Override
    public List<UserRole> mapDtosToEntities(List<UserRoleDto> dtos) {
        return super.mapDtosToEntities(dtos);
    }

    @Override
    public List<UserRoleDto> mapEntitiesToDtos(List<UserRole> entities) {
        return super.mapEntitiesToDtos(entities);
    }

    @Override
    public List<UserRole> updateListWithDto(List<UserRoleDto> dtos, List<UserRole> entities) {
        return super.updateListWithDto(dtos, entities);
    }
}
