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
import ru.text.nastya.exception.DataNotFoundException;

@RestController
@RequestMapping("postRegister/{registerUuid}/post")
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
    public PostDto addPost(@PathVariable String registerUuid, @Validated(Create.class) PostDto postDto) {
        Validate.notNull(registerUuid, "Can't add with null register uuid");
        Validate.notNull(postDto, "Can't add with null postDto");
        Post post = postRegisterCrudService.addPost(registerUuid, toEntity(postDto));
        return toDto(post);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto updatePost(@PathVariable String registerUuid, @Validated(Update.class) PostDto postDto) {
        Validate.notNull(registerUuid, "Can't update with null register uuid");
        Validate.notNull(postDto, "Can't update with null postDto");
        return postRegisterCrudService.getPost(registerUuid)
                .map(found -> {
                    Post updatedPost = postRegisterCrudService.addPost(registerUuid, updateWithDto(postDto, found));
                    return toDto(updatedPost);
                })
                .orElseGet(() -> addPost(registerUuid, postDto));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePost(@PathVariable String registerUuid) {
        Validate.notNull(registerUuid, "Can't remove with null register uuid");
        postRegisterCrudService.removePost(registerUuid);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPost(@PathVariable String registerUuid) {
        Validate.notNull(registerUuid, "Can't get with null register uuid");
        return postRegisterCrudService.getPost(registerUuid)
                .map(this::toDto)
                .orElseThrow(DataNotFoundException::new);
    }
}
