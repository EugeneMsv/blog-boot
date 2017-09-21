package ru.text.nastya.utils;

import ru.text.nastya.domain.entities.Tag;
import ru.text.nastya.dto.TagDto;

import java.time.LocalDateTime;

import static ru.text.nastya.utils.DomainEntityBuilder.buildRandomString;
import static ru.text.nastya.utils.DomainEntityBuilder.rand;

public class TagBuilder {

    private Tag tag;

    private TagDto tagDto;

    public TagDto randomDto() {
        return dto(buildRandomString(20),buildRandomString())
                .buildDto();
    }

    public Tag random() {
        return entity(buildRandomString(20),buildRandomString())
                .buildEntity();
    }

    public TagBuilder entity(String code, String description) {
        this.tag = new Tag(code, description);
        return this;
    }

    public TagBuilder dto(String code, String description) {
        this.tagDto = new TagDto(code, description);
        return this;
    }


    public Tag buildEntity() {
        return tag;
    }

    public TagDto buildDto() {
        return tagDto;
    }

}
