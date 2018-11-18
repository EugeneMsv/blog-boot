package ru.text.nastya.dto.mapper;

import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ru.text.nastya.domain.entities.credential.User;
import ru.text.nastya.dto.UserDto;
import ru.text.nastya.dto.mapper.collection.UserGroupListMapper;

@Mapper(withIoC = IoC.SPRING,
        withIoCServiceName = "userMapper",
        withIgnoreMissing = IgnoreMissing.ALL,
        withIgnoreNullValue = false,
        withCustomFields = @Field(value = "groups", withCustom = UserGroupListMapper.class))
public interface UserMapper extends EntityMapper<User, UserDto> {

    @Override
    User mapToEntity(UserDto dto);

    @Override
    User updateEntityWithDto(UserDto dto, User entity);

    @Override
    UserDto mapToDto(User entity);
}
