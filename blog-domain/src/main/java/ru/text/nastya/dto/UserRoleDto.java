package ru.text.nastya.dto;

import ru.text.nastya.dto.base.DictionaryDto;

public class UserRoleDto extends DictionaryDto {

    private static final long serialVersionUID = 1L;

    public UserRoleDto() {
    }

    public UserRoleDto(String code, String name) {
        super(code, name);
    }
}
