package ru.text.nastya.dto;

import ru.text.nastya.domain.entities.credential.UserStatus;
import ru.text.nastya.dto.base.IdentityDto;

import java.util.ArrayList;
import java.util.List;

public class UserDto extends IdentityDto {

    private static final long serialVersionUID = 1L;

    private String ssoId;

    private UserStatus status;

    private List<SimpleUserGroupDto> groups;

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public List<SimpleUserGroupDto> getGroups() {
        if (groups == null) {
            groups = new ArrayList<>();
        }
        return groups;
    }

    public void setGroups(List<SimpleUserGroupDto> groups) {
        this.groups = groups;
    }

}
