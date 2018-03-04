package ru.text.nastya.dto.mapper;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ru.text.nastya.domain.entities.PostRegister;
import ru.text.nastya.dto.PostRegisterDto;

@Mapper(withIoC = IoC.SPRING,
        withIoCServiceName = "postRegisterMapper",
        withIgnoreMissing = IgnoreMissing.ALL,
        withIgnoreNullValue = true
)
public interface PostRegisterMapper extends EntityMapper<PostRegister, PostRegisterDto> {

    @Override
    PostRegister mapToEntity(PostRegisterDto dto);

    @Override
    PostRegister updateEntityWithDto(PostRegisterDto dto, PostRegister entity);

    @Override
    PostRegisterDto mapToDto(PostRegister entity);
}
