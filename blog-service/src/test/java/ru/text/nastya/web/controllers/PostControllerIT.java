package ru.text.nastya.web.controllers;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import ru.text.nastya.BaseControllerConfiguration;
import ru.text.nastya.dto.PostDto;
import ru.text.nastya.dto.TagDto;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.text.nastya.utils.DomainEntityBuilder.*;

@DatabaseSetups({
        @DatabaseSetup(value = "/preset/clean-web.xml", type = DatabaseOperation.DELETE_ALL),})
@Ignore("need support security")
public class PostControllerIT extends BaseControllerConfiguration {

    private static final String urlPrefix = "/postRegister/{registerId}/post";

    private PostDto buildRandomPost() {
        List<TagDto> tags = new ArrayList<>(new ArrayList<>(3));
        for (int i = 0; i < 3; i++) {
            tags.add(getTagBuilder().randomDto());
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