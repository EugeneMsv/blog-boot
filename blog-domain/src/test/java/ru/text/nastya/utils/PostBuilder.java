package ru.text.nastya.utils;

import ru.text.nastya.domain.entities.Post;
import ru.text.nastya.domain.entities.PostRegister;
import ru.text.nastya.domain.entities.PostState;
import ru.text.nastya.domain.entities.Tag;
import ru.text.nastya.dto.PostDto;
import ru.text.nastya.dto.PostRegisterDto;
import ru.text.nastya.dto.TagDto;
import ru.text.nastya.dto.mapper.collection.ListWrapper;

import java.util.List;

public class PostBuilder {

    private Post post;

    private PostDto postDto;

    public PostBuilder entity(String code) {
        this.post = new Post();
        post.setCode(code);
        return this;
    }

    public PostBuilder dto(String code) {
        this.postDto = new PostDto();
        postDto.setCode(code);
        return this;
    }

    public PostBuilder head(String title, String author) {
        if (post != null) {
            post.setTitle(title);
            post.setAuthor(author);
        } else {
            postDto.setTitle(title);
            postDto.setAuthor(author);
        }
        return this;
    }

    public PostBuilder main(String text) {
        if (post != null) {
            post.setText(text);
        } else {
            postDto.setText(text);
        }
        return this;
    }

    public PostBuilder state(PostState state) {
        if (post != null) {
            post.setState(state);
        } else {
            postDto.setState(state);
        }
        return this;
    }

    public PostBuilder tags(List<Tag> tags) {
        post.setTags(tags);
        return this;
    }

    public PostBuilder tags(ListWrapper<TagDto> tags) {
        postDto.setTags(tags);
        return this;
    }

    public PostBuilder register(PostRegisterDto register) {
        postDto.setPostRegister(register);
        return this;
    }

    public PostBuilder register(PostRegister register) {
        post.setPostRegister(register);
        return this;
    }

    public Post buildEntity() {
        return post;
    }

    public PostDto buildDto() {
        return postDto;
    }

}
