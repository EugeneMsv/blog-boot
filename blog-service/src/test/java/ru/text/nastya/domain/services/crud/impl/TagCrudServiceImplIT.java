package ru.text.nastya.domain.services.crud.impl;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.text.nastya.BaseServiceIT;
import ru.text.nastya.domain.entities.Tag;
import ru.text.nastya.domain.services.crud.TagCrudService;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static ru.text.nastya.utils.AssertionUtils.assertFieldsEquals;
import static ru.text.nastya.utils.AssertionUtils.assertPersist;
import static ru.text.nastya.utils.AssertionUtils.assertReflectionEquals;
import static ru.text.nastya.utils.DomainEntityBuilder.buildRandomString;
import static ru.text.nastya.utils.DomainEntityBuilder.buildRandomTag;

@DatabaseSetup(value = "/preset/clean-web.xml", type = DatabaseOperation.DELETE_ALL)
public class TagCrudServiceImplIT extends BaseServiceIT {

    @Autowired
    private TagCrudService tagCrudService;

    @Test
    public void test_save_Tag_Success() throws Exception {
        Tag randTag = buildRandomTag();
        Tag saved = tagCrudService.save(randTag);
        assertPersist(saved);
        assertFieldsEquals(randTag, saved);
    }

    @Test
    @Ignore
    // TODO: 17.08.2017 имеет смысл для dto  т.к.  здесь обхект по одной ссылке
    public void test_save_UpdateAfterFoundSaved_Success() throws Exception {
        //save
        Tag randTag = buildRandomTag();
        Tag saved = tagCrudService.save(randTag);
        assertPersist(saved);
        assertFieldsEquals(randTag, saved);
        //find
        Optional<Tag> optTag = tagCrudService.findOne(saved.getId());
        Tag found = optTag.get();
        assertNotNull(found);
        assertPersist(found);
        assertFieldsEquals(saved, found);
        //update
        found.setDescription(buildRandomString());

        Tag updated = tagCrudService.save(found);

        assertReflectionEquals(randTag, updated, "description");
    }


    @Test
    public void test_findOne_ById_Success() throws Exception {
        Tag randTag = buildRandomTag();
        Tag saved = tagCrudService.save(randTag);
        assertFieldsEquals(randTag, saved);
        Optional<Tag> optTag = tagCrudService.findOne(saved.getId());
        Tag found = optTag.get();
        assertFieldsEquals(saved, found);
    }

    @Test
    public void test_delete_AfterSaveById_Success() throws Exception {
        assertFalse(tagCrudService.exists());
        //save
        Tag randTag = buildRandomTag();
        Tag saved = tagCrudService.save(randTag);
        assertFieldsEquals(randTag, saved);

        assertTrue(tagCrudService.exists());
        //delete
        tagCrudService.delete(saved.getId());
        assertFalse(tagCrudService.exists());
    }


    @Test
    public void test_findAll_TwoObjectsOnOnePage_Success() throws Exception {
        assertFalse(tagCrudService.exists());
        //save
        Tag randTag1 = buildRandomTag();
        Tag saved1 = tagCrudService.save(randTag1);
        assertFieldsEquals(randTag1, saved1);

        Tag randTag2 = buildRandomTag();
        Tag saved2 = tagCrudService.save(randTag2);
        assertFieldsEquals(randTag2, saved2);

        Page<Tag> page = tagCrudService.findAll(new PageRequest(0, 10));
        assertFalse(page.hasNext());
        assertThat(Arrays.asList(saved1, saved2), is(page.getContent()));
    }

    @Test
    public void test_findAll_TwoObjectsOnTwoPage_Success() throws Exception {
        assertFalse(tagCrudService.exists());
        //save
        Tag randTag1 = buildRandomTag();
        Tag saved1 = tagCrudService.save(randTag1);
        assertFieldsEquals(randTag1, saved1);

        Tag randTag2 = buildRandomTag();
        Tag saved2 = tagCrudService.save(randTag2);
        assertFieldsEquals(randTag2, saved2);

        Page<Tag> page1 = tagCrudService.findAll(new PageRequest(0, 1));
        Page<Tag> page2 = tagCrudService.findAll(new PageRequest(0, 2));
        assertTrue(page1.hasNext());
        assertFalse(page2.hasNext());
        assertTrue(page1.getContent().contains(saved1) || page2.getContent().contains(saved1));
        assertTrue(page1.getContent().contains(saved2) || page2.getContent().contains(saved2));
    }


    @Test
    @Ignore
    public void test_callProfilingTime() throws Exception {
        long allCalls = 0;
        int cycles = 300000;
        for (int i = 0; i < cycles; i++) {
            Tag saveTarget = new Tag(UUID.randomUUID().toString().substring(0, 20),
                    UUID.randomUUID().toString().substring(0, 20));
            Instant start = Instant.now();
            tagCrudService.save(saveTarget);
            allCalls += Duration.between(start, Instant.now()).getNano();
        }
        System.out.println("Average time " + allCalls / cycles);
    }

}