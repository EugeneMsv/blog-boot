package ru.text.nastya.dto.mapper;

import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ru.text.nastya.domain.entities.Post;
import ru.text.nastya.dto.PostDto;

@Mapper(withIoC = IoC.SPRING,
        withIoCServiceName = "postMapper",
        withIgnoreMissing = IgnoreMissing.ALL,
        withIgnoreNullValue = true,
        withCustomFields = {
                @Field(value = "postRegister", withCustom = PostRegisterMapper.class),
                @Field(value = "tags", withCustom = TagCollectionMapper.class)
        }
)
public interface PostMapper extends EntityMapper<Post, PostDto> {

    @Override
    Post mapToEntity(PostDto dto);

    @Override
    Post updateEntityWithDto(PostDto dto, Post entity);

    @Override
    PostDto mapToDto(Post entity);
}
