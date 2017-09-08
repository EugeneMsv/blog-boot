package ru.text.nastya.dto;

import ru.text.nastya.dto.base.IdentityDto;

public class TagDto extends IdentityDto {

    private static final long serialVersionUID = -3884735562187892353L;

    private String code;

    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
