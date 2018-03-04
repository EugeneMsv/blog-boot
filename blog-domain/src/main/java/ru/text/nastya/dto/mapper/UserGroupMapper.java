package ru.text.nastya.dto.mapper;

import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ru.text.nastya.domain.entities.credential.UserGroup;
import ru.text.nastya.dto.UserGroupDto;
import ru.text.nastya.dto.mapper.collection.UserRoleListMapper;

@Mapper(withIoC = IoC.SPRING,
        withIoCServiceName = "userGroupMapper",
        withIgnoreMissing = IgnoreMissing.ALL,
        withIgnoreNullValue = true,
        withCustomFields = @Field(value = "roles", withCustom = UserRoleListMapper.class))
public interface UserGroupMapper extends EntityMapper<UserGroup, UserGroupDto> {

    @Override
    UserGroup mapToEntity(UserGroupDto dto);

    @Override
    UserGroup updateEntityWithDto(UserGroupDto dto, UserGroup entity);

    @Override
    UserGroupDto mapToDto(UserGroup entity);
}
