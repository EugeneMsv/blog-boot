package ru.text.nastya.web.controllers;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import ru.text.nastya.BaseControllerIT;
import ru.text.nastya.dto.PostRegisterDto;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.text.nastya.utils.AssertionUtils.assertReflectionEquals;
import static ru.text.nastya.utils.DomainEntityBuilder.buildRandomString;
import static ru.text.nastya.utils.DomainEntityBuilder.getPostRegisterBuilder;


@DatabaseSetups({
        @DatabaseSetup(value = "/preset/clean-web.xml", type = DatabaseOperation.DELETE_ALL),})
public class PostRegisterCrudControllerIT extends BaseControllerIT {


    private static final String urlPrefix = "/postRegister";

    private PostRegisterDto buildRandomPostRegister() {
        return getPostRegisterBuilder().dto()
                .general(rand.nextLong(), rand.nextLong(), rand.nextLong())
                .info(buildRandomString(), LocalDateTime.now())
                .meta(buildRandomString())
                .buildDto();
    }

    private PostRegisterDto doSaveRequest(PostRegisterDto postRegisterDto) {
        try {
            String saveJson = jsonMapper.writeValueAsString(postRegisterDto);
            MvcResult mvcSaveResult = mvc.perform(post(urlPrefix)
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(saveJson)
            ).andExpect(status().isCreated()).andReturn();
            String responseJson = mvcSaveResult.getResponse().getContentAsString();
            return jsonMapper.readValue(responseJson, PostRegisterDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test_savePostRegister_NullPost() {
        PostRegisterDto postRegisterDto = buildRandomPostRegister();
        PostRegisterDto saveResponse = doSaveRequest(postRegisterDto);
        assertReflectionEquals(postRegisterDto, saveResponse, "id");
    }


}