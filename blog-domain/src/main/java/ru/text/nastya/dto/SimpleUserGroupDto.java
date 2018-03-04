package ru.text.nastya.dto;

import ru.text.nastya.dto.base.DictionaryDto;

public class SimpleUserGroupDto extends DictionaryDto {

    private static final long serialVersionUID = 1L;

    public SimpleUserGroupDto() {
    }

    public SimpleUserGroupDto(String code, String name) {
        super(code, name);
    }
}
