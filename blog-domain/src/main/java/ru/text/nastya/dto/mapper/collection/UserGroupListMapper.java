package ru.text.nastya.dto.mapper.collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.text.nastya.domain.entities.credential.UserGroup;
import ru.text.nastya.dto.SimpleUserGroupDto;
import ru.text.nastya.dto.mapper.EntityMapper;
import ru.text.nastya.dto.mapper.SimpleUserGroupMapper;

import java.util.List;

@Component
public class UserGroupListMapper extends ListMapper<UserGroup, SimpleUserGroupDto> {

    @Autowired
    private SimpleUserGroupMapper simpleUserGroupMapper;

    @Override
    protected EntityMapper<UserGroup, SimpleUserGroupDto> getEntityMapper() {
        return simpleUserGroupMapper;
    }

    @Override
    public List<UserGroup> mapDtosToEntities(List<SimpleUserGroupDto> dtos) {
        return super.mapDtosToEntities(dtos);
    }

    @Override
    public List<SimpleUserGroupDto> mapEntitiesToDtos(List<UserGroup> entities) {
        return super.mapEntitiesToDtos(entities);
    }

    @Override
    public List<UserGroup> updateListWithDto(List<SimpleUserGroupDto> dtos, List<UserGroup> entities) {
        return super.updateListWithDto(dtos, entities);
    }
}
