package ru.text.nastya.dto.mapper;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ru.text.nastya.domain.entities.credential.UserRole;
import ru.text.nastya.dto.UserRoleDto;

@Mapper(withIoC = IoC.SPRING,
        withIoCServiceName = "userRoleMapper",
        withIgnoreMissing = IgnoreMissing.ALL,
        withIgnoreNullValue = false)
public interface UserRoleMapper extends EntityMapper<UserRole, UserRoleDto> {

    @Override
    UserRole mapToEntity(UserRoleDto dto);

    @Override
    UserRole updateEntityWithDto(UserRoleDto dto, UserRole entity);

    @Override
    UserRoleDto mapToDto(UserRole entity);
}
