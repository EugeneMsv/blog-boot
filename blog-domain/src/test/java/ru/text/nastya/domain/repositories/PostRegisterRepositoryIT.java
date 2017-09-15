package ru.text.nastya.domain.repositories;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.text.nastya.BaseRepositoryIT;
import ru.text.nastya.domain.entities.Post;
import ru.text.nastya.domain.entities.PostRegister;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;
import static ru.text.nastya.utils.AssertionUtils.assertFieldsEquals;
import static ru.text.nastya.utils.AssertionUtils.assertPersist;
import static ru.text.nastya.utils.DomainEntityBuilder.buildRandomString;
import static ru.text.nastya.utils.DomainEntityBuilder.getPostRegisterBuilder;

@DatabaseSetups({@DatabaseSetup(value = "/preset/clean.xml", type = DatabaseOperation.DELETE_ALL),
        @DatabaseSetup(value = "/preset/postregister/set_postregister.xml", type = DatabaseOperation.INSERT)})
public class PostRegisterRepositoryIT extends BaseRepositoryIT {

    private static final Random rand = new Random(System.nanoTime());

    @Autowired
    private PostRegisterRepository postRegisterRepository;

    @Autowired
    private PostRepository postRepository;

    private static Post preSavedPost;

    @Before
    public void setUp() {
        preSavedPost = postRepository.findOne(0L).get();
    }

    private PostRegister buildRandomPostRegister() {
        return getPostRegisterBuilder()
                .entity()
                .general(rand.nextLong(), rand.nextLong(), rand.nextLong())
                .info(buildRandomString(), LocalDateTime.now())
                .meta(buildRandomString())
                .post(preSavedPost)
                .buildEntity();
    }


    @Test
    public void test_save_PostRegister_Success() throws Exception {
        PostRegister randPostRegister = buildRandomPostRegister();
        PostRegister saved = postRegisterRepository.save(randPostRegister);
        assertPersist(saved);
        assertFieldsEquals(randPostRegister, saved);
    }


    @Test
    public void test_findOne_ById_Success() throws Exception {
        PostRegister randPostRegister = buildRandomPostRegister();
        PostRegister saved = postRegisterRepository.save(randPostRegister);
        assertFieldsEquals(randPostRegister, saved);
        Optional<PostRegister> optPostRegister = postRegisterRepository.findOne(saved.getId());
        PostRegister found = optPostRegister.get();
        assertFieldsEquals(saved, found);
    }

    @Test
    public void test_delete_AfterSaveById_Success() throws Exception {
        assertFalse(postRegisterRepository.exists());
        //save
        PostRegister randPostRegister = buildRandomPostRegister();
        PostRegister saved = postRegisterRepository.save(randPostRegister);
        assertFieldsEquals(randPostRegister, saved);

        assertTrue(postRegisterRepository.exists());
        //delete
        postRegisterRepository.delete(saved.getId());
        assertFalse(postRegisterRepository.exists());
    }


    @Test
    public void test_findAll_TwoObjectsOnOnePage_Success() throws Exception {
        assertFalse(postRegisterRepository.exists());
        //save
        PostRegister randPostRegister1 = buildRandomPostRegister();
        PostRegister saved1 = postRegisterRepository.save(randPostRegister1);
        assertFieldsEquals(randPostRegister1, saved1);

        PostRegister randPostRegister2 = buildRandomPostRegister();
        PostRegister saved2 = postRegisterRepository.save(randPostRegister2);
        assertFieldsEquals(randPostRegister2, saved2);

        Page<PostRegister> page = postRegisterRepository.findAll(new PageRequest(0, 10));
        assertFalse(page.hasNext());
        assertThat(Arrays.asList(saved1, saved2), containsInAnyOrder(page.getContent().toArray()));
    }

    @Test
    public void test_findAll_TwoObjectsOnTwoPage_Success() throws Exception {
        assertFalse(postRegisterRepository.exists());
        //save
        PostRegister randPostRegister1 = buildRandomPostRegister();
        PostRegister saved1 = postRegisterRepository.save(randPostRegister1);
        assertFieldsEquals(randPostRegister1, saved1);

        PostRegister randPostRegister2 = buildRandomPostRegister();
        PostRegister saved2 = postRegisterRepository.save(randPostRegister2);
        assertFieldsEquals(randPostRegister2, saved2);

        Page<PostRegister> page1 = postRegisterRepository.findAll(new PageRequest(0, 1));
        Page<PostRegister> page2 = postRegisterRepository.findAll(new PageRequest(0, 2));
        assertTrue(page1.hasNext());
        assertFalse(page2.hasNext());

        assertTrue(page1.getContent().contains(saved1) || page2.getContent().contains(saved1));
        assertTrue(page1.getContent().contains(saved2) || page2.getContent().contains(saved2));
    }

}