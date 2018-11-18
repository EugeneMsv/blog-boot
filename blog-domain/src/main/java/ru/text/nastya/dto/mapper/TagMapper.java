package ru.text.nastya.dto.mapper;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ru.text.nastya.domain.entities.Tag;
import ru.text.nastya.dto.TagDto;

@Mapper(withIoC = IoC.SPRING,
        withIoCServiceName = "tagMapper",
        withIgnoreMissing = IgnoreMissing.ALL,
        withIgnoreNullValue = false)
public interface TagMapper extends EntityMapper<Tag, TagDto> {

    @Override
    Tag mapToEntity(TagDto dto);

    @Override
    Tag updateEntityWithDto(TagDto dto, Tag entity);

    @Override
    TagDto mapToDto(Tag entity);
}
