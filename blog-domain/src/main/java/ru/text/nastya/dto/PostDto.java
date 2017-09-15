package ru.text.nastya.dto;

import ru.text.nastya.domain.entities.PostRegister;
import ru.text.nastya.domain.entities.PostState;
import ru.text.nastya.domain.entities.Tag;
import ru.text.nastya.dto.base.IdentityDto;

import java.util.List;

public class PostDto extends IdentityDto {

    private String code;

    private String title;

    private String text;

    private List<Tag> tags;

    private String author;

    private PostState state;

    private PostRegister postRegister;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
