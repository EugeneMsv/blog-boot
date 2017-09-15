package ru.text.nastya.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.text.nastya.domain.entities.Tag;
import ru.text.nastya.dto.TagDto;
import ru.text.nastya.dto.mapper.collection.AbstractCollectionIdentityMapper;
import ru.text.nastya.dto.mapper.collection.ListWrapper;

import java.util.List;

@Component
public class TagCollectionMapper extends AbstractCollectionIdentityMapper<Tag, TagDto> {

    @Autowired
    private TagMapper tagMapper;

    @Override
    protected EntityMapper<Tag, TagDto> getEntityMapper() {
        return tagMapper;
    }

    /**
     * Override cause selma didn't compile
     */
    @Override
    public List<Tag> updateCollectionWithDto(ListWrapper<TagDto> dtos, List<Tag> entities) {
        return super.updateCollectionWithDto(dtos, entities);
    }

    /**
     * Override cause selma didn't compile
     */
    @Override
    public List<Tag> mapDtosToEntities(ListWrapper<TagDto> dtosWrapper) {
        return super.mapDtosToEntities(dtosWrapper);
    }

    /**
     * Override cause selma didn't compile
     */
    @Override
    public ListWrapper<TagDto> mapEntitiesToDtos(List<Tag> entities) {
        return super.mapEntitiesToDtos(entities);
    }
}
