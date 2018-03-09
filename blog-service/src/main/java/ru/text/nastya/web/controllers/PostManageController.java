package ru.text.nastya.web.controllers;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.text.nastya.domain.entities.Post;
import ru.text.nastya.domain.services.crud.PostRegisterCrudService;
import ru.text.nastya.dto.PostDto;
import ru.text.nastya.dto.mapper.PostMapper;
import ru.text.nastya.dto.validation.groups.Create;
import ru.text.nastya.dto.validation.groups.Update;

@RestController
@RequestMapping("postRegister/{registerId}/post")
public class PostManageController {

    private final PostRegisterCrudService postRegisterCrudService;

    private final PostMapper postMapper;

    @Autowired
    public PostManageController(PostRegisterCrudService postRegisterCrudService, PostMapper postMapper) {
        this.postRegisterCrudService = postRegisterCrudService;
        this.postMapper = postMapper;
    }

    private Post toEntity(PostDto postDto) {
        return postMapper.mapToEntity(postDto);
    }

    private PostDto toDto(Post post) {
        return postMapper.mapToDto(post);
    }

    private Post updateWithDto(PostDto dto, Post post) {
        return postMapper.updateEntityWithDto(dto, post);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto addPost(@PathVariable Long registerId, @Validated(Create.class) PostDto postDto) {
        Validate.notNull(registerId, "Can't add with null register id");
        Validate.notNull(postDto, "Can't add with null postDto");
        Post post = postRegisterCrudService.addPost(registerId, toEntity(postDto));
        return toDto(post);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto updatePost(@PathVariable Long registerId, @Validated(Update.class) PostDto postDto) {
        Validate.notNull(registerId, "Can't update with null register id");
        Validate.notNull(postDto, "Can't update with null postDto");
        Post alreadySavedPost = postRegisterCrudService.getPost(registerId);
        if (alreadySavedPost == null) {
            return addPost(registerId, postDto);
        } else {
            Post updatedPost = postRegisterCrudService.addPost(registerId, updateWithDto(postDto, alreadySavedPost));
            return toDto(updatedPost);
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePost(@PathVariable Long registerId) {
        Validate.notNull(registerId, "Can't remove with null register id");
        postRegisterCrudService.removePost(registerId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPost(@PathVariable Long registerId) {
        Validate.notNull(registerId, "Can't get with null register id");
        Post post = postRegisterCrudService.getPost(registerId);
        return toDto(post);
    }
}
