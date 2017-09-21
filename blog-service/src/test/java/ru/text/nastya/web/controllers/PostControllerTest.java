package ru.text.nastya.web.controllers;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import ru.text.nastya.BaseControllerIT;
import ru.text.nastya.dto.PostDto;
import ru.text.nastya.dto.TagDto;
import ru.text.nastya.dto.mapper.collection.ListWrapper;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.text.nastya.utils.DomainEntityBuilder.*;

@DatabaseSetups({
        @DatabaseSetup(value = "/preset/clean-web.xml", type = DatabaseOperation.DELETE_ALL),})
public class PostControllerTest extends BaseControllerIT {

    private static final String urlPrefix = "/postRegister/{registerId}/post";

    private PostDto buildRandomPost() {
        ListWrapper<TagDto> tags = new ListWrapper<>(new ArrayList<>(3));
        for (int i = 0; i < 3; i++) {
            tags.getValues().add(getTagBuilder().randomDto());
        }
        return getPostBuilder().randomDto(getPostRegisterBuilder().randomDto(), tags);
    }

    protected PostDto doAddRequest(String url, PostDto instance) {
        try {
            String saveJson = jsonMapper.writeValueAsString(instance);
            MvcResult mvcSaveResult = mvc.perform(post(url)
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(saveJson)
            ).andExpect(status().isCreated()).andReturn();
            String responseJson = mvcSaveResult.getResponse().getContentAsString();
            return jsonMapper.readValue(responseJson, PostDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test_addPost() {
        PostDto randomPost = buildRandomPost();

        // TODO: 21.09.2017  
    }
}