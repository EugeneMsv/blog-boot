package ru.text.nastya.dto;

import ru.text.nastya.dto.base.DictionaryDto;

import java.util.ArrayList;
import java.util.List;

public class UserGroupDto extends DictionaryDto {

    private static final long serialVersionUID = 1L;

    private List<UserRoleDto> roles;

    public UserGroupDto() {
    }

    public UserGroupDto(String code, String name) {
        super(code, name);
    }

    public List<UserRoleDto> getRoles() {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        return roles;
    }

    public void setRoles(List<UserRoleDto> roles) {
        this.roles = roles;
    }
}
