package ru.text.nastya.web.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import org.junit.Test;
import org.springframework.data.domain.Page;
import ru.text.nastya.BaseControllerIT;
import ru.text.nastya.dto.PostRegisterDto;
import ru.text.nastya.utils.TestPageImpl;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;
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

    @Test
    public void test_savePostRegister() {
        PostRegisterDto postRegisterDto = buildRandomPostRegister();
        PostRegisterDto saveResponse = doSaveRequest(urlPrefix, postRegisterDto, PostRegisterDto.class);
        assertReflectionEquals(postRegisterDto, saveResponse, "id");
    }

    @Test
    public void test_findAllPostRegister() {
        // Save
        PostRegisterDto postRegisterDto = buildRandomPostRegister();
        PostRegisterDto saveResponse = doSaveRequest(urlPrefix, postRegisterDto, PostRegisterDto.class);
        assertReflectionEquals(postRegisterDto, saveResponse, "id");

        PostRegisterDto postRegisterDto2 = buildRandomPostRegister();
        PostRegisterDto saveResponse2 = doSaveRequest(urlPrefix, postRegisterDto2, PostRegisterDto.class);
        assertReflectionEquals(postRegisterDto2, saveResponse2, "id");

        Page<PostRegisterDto> page = doFindAllRequest(urlPrefix, new TypeReference<TestPageImpl<PostRegisterDto>>() {
        });
        assertTrue(page.hasContent());
        assertThat(Arrays.asList(saveResponse, saveResponse2), containsInAnyOrder(page.getContent().toArray()));
    }


    @Test
    public void test_getPostRegister() {
        // Save
        PostRegisterDto postRegisterDto = buildRandomPostRegister();
        PostRegisterDto saveResponse = doSaveRequest(urlPrefix, postRegisterDto, PostRegisterDto.class);
        assertReflectionEquals(postRegisterDto, saveResponse, "id");

        PostRegisterDto getResultDto = doGetRequest(urlPrefix, saveResponse.getId(), PostRegisterDto.class);

        assertReflectionEquals(saveResponse, getResultDto);
    }

    @Test
    public void test_deletePostRegister() {
        PostRegisterDto postRegisterDto = buildRandomPostRegister();
        PostRegisterDto saveResponse = doSaveRequest(urlPrefix, postRegisterDto, PostRegisterDto.class);
        assertReflectionEquals(postRegisterDto, saveResponse, "id");

        doDeleteRequest(urlPrefix, saveResponse.getId());

        Page<PostRegisterDto> page = doFindAllRequest(urlPrefix, new TypeReference<TestPageImpl<PostRegisterDto>>() {
        });
        assertFalse(page.hasContent());
    }

    @Test
    public void test_updatePostRegister() throws Exception {
        PostRegisterDto postRegisterDto = buildRandomPostRegister();
        PostRegisterDto beforeUpdate = doSaveRequest(urlPrefix, postRegisterDto, PostRegisterDto.class);
        assertReflectionEquals(postRegisterDto, beforeUpdate, "id");

        PostRegisterDto dtoForUpdate = new PostRegisterDto();
        dtoForUpdate.setLikes(postRegisterDto.getLikes() + 1);
        dtoForUpdate.setViews(postRegisterDto.getViews() + 1);
        dtoForUpdate.setCreatedTime(postRegisterDto.getCreatedTime().plusMinutes(10));

        assertNotEquals(beforeUpdate.getLikes(), dtoForUpdate.getLikes());
        assertNotEquals(beforeUpdate.getViews(), dtoForUpdate.getViews());
        assertNotEquals(beforeUpdate.getCreatedTime(), dtoForUpdate.getCreatedTime());
        assertNull(dtoForUpdate.getCommentsNum());
        assertNull(dtoForUpdate.getMetaInfo());
        assertNull(dtoForUpdate.getPreview());

        PostRegisterDto updatedPostRegister = doUpdateRequest(urlPrefix, beforeUpdate.getId(), dtoForUpdate,
                PostRegisterDto.class);

        assertEquals(dtoForUpdate.getLikes(), updatedPostRegister.getLikes());
        assertEquals(dtoForUpdate.getViews(), updatedPostRegister.getViews());
        assertEquals(dtoForUpdate.getCreatedTime(), updatedPostRegister.getCreatedTime());
        assertEquals(beforeUpdate.getCommentsNum(), updatedPostRegister.getCommentsNum());
        assertEquals(beforeUpdate.getMetaInfo(), updatedPostRegister.getMetaInfo());
        assertEquals(beforeUpdate.getPreview(), updatedPostRegister.getPreview());

    }


}