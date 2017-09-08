package ru.text.nastya.dto;

import ru.text.nastya.dto.base.IdentityDto;

public class PostDto extends IdentityDto {

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
