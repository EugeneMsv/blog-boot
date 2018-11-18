package ru.text.nastya.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.text.nastya.domain.entities.Tag;
import ru.text.nastya.dto.TagDto;
import ru.text.nastya.dto.mapper.collection.AbstractCollectionIdentityMapper;

import java.util.List;

@Component
public class TagCollectionMapper extends AbstractCollectionIdentityMapper<Tag, TagDto> {

    @Autowired
    private TagMapper tagMapper;

    @Override
    protected EntityMapper<Tag, TagDto> getEntityMapper() {
        return tagMapper;
    }

    @Override
    public List<Tag> mapToEntities(List<TagDto> dtos) {
        return super.mapToEntities(dtos);
    }

    @Override
    public List<TagDto> mapToDtos(List<Tag> entities) {
        return super.mapToDtos(entities);
    }

    @Override
    public List<Tag> updateWithDtos(List<TagDto> dtos, List<Tag> entities) {
        return super.updateWithDtos(dtos, entities);
    }
}
