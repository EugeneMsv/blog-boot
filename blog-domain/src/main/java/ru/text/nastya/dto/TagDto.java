package ru.text.nastya.dto;

import ru.text.nastya.dto.base.DictionaryDto;

public class TagDto extends DictionaryDto {

    private static final long serialVersionUID = -3884735562187892353L;

    public TagDto() {
    }

    public TagDto(String code, String name) {
        super(code, name);
    }
}
