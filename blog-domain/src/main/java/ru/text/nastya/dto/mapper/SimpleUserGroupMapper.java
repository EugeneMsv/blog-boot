package ru.text.nastya.dto.mapper;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ru.text.nastya.domain.entities.credential.UserGroup;
import ru.text.nastya.dto.SimpleUserGroupDto;

@Mapper(withIoC = IoC.SPRING,
        withIoCServiceName = "simpleUserGroupMapper",
        withIgnoreMissing = IgnoreMissing.ALL,
        withIgnoreNullValue = false)
public interface SimpleUserGroupMapper extends EntityMapper<UserGroup, SimpleUserGroupDto> {

    @Override
    UserGroup mapToEntity(SimpleUserGroupDto dto);

    @Override
    UserGroup updateEntityWithDto(SimpleUserGroupDto dto, UserGroup entity);

    @Override
    SimpleUserGroupDto mapToDto(UserGroup entity);
}
