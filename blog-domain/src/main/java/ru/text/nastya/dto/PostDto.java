package ru.text.nastya.dto;

import org.hibernate.validator.constraints.NotEmpty;
import ru.text.nastya.domain.entities.PostState;
import ru.text.nastya.dto.base.IdentityDto;
import ru.text.nastya.dto.validation.groups.Create;
import ru.text.nastya.dto.validation.groups.Update;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class PostDto extends IdentityDto {

    private static final long serialVersionUID = 842318791283303562L;

    private String code;

    @NotEmpty(groups = {Create.class, Update.class})
    private String title;

    @NotEmpty(groups = {Create.class, Update.class})
    private String text;

    @Valid
    private List<TagDto> tags;

    @NotEmpty(groups = {Create.class, Update.class})
    private String author;

    private PostState state;

    @NotNull(groups = {Create.class, Update.class})
    @Valid
    private PostRegisterDto postRegister;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<TagDto> getTags() {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public PostState getState() {
        return state;
    }

    public void setState(PostState state) {
        this.state = state;
    }

    public PostRegisterDto getPostRegister() {
        return postRegister;
    }

    public void setPostRegister(PostRegisterDto postRegister) {
        this.postRegister = postRegister;
    }
}
