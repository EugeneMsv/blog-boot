package ru.text.nastya.domain.services.manager.impl;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import ru.text.nastya.BaseServiceConfiguration;
import ru.text.nastya.domain.entities.Post;
import ru.text.nastya.domain.entities.PostRegister;
import ru.text.nastya.domain.entities.PostState;
import ru.text.nastya.domain.entities.Tag;
import ru.text.nastya.domain.repositories.PostRegisterRepository;
import ru.text.nastya.domain.repositories.TagRepository;
import ru.text.nastya.domain.services.manager.PostManager;
import ru.text.nastya.utils.AssertionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static ru.text.nastya.utils.AssertionUtils.assertListEqualsInAnyOrder;
import static ru.text.nastya.utils.AssertionUtils.assertReflectionEquals;
import static ru.text.nastya.utils.DomainEntityBuilder.buildRandomString;
import static ru.text.nastya.utils.DomainEntityBuilder.getPostBuilder;

@DatabaseSetup(value = "/preset/clean-web.xml", type = DatabaseOperation.DELETE_ALL)
@DatabaseSetup(value = "/preset/set_post.xml", type = DatabaseOperation.INSERT)
public class PostManagerImplIT extends BaseServiceConfiguration {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostRegisterRepository postRegisterRepository;

    @Autowired
    private PostManager postManager;

    private static PostRegister postRegister;

    private static List<Tag> tags = new ArrayList<>(2);

    @Before
    public void setUp() {
        postRegister = postRegisterRepository.findOne("0").get();

        tags.clear();
        Iterable<Tag> tagIterable = tagRepository.findAll(new PageRequest(0, Integer.MAX_VALUE));

        StreamSupport.stream(tagIterable.spliterator(), false)
                .peek(tags::add).count();
    }

    private Post buildRandomPost() {
        return getPostBuilder().entity(buildRandomString(100))
                .head(buildRandomString(), buildRandomString())
                .main(buildRandomString())
                .state(PostState.NEW)
                .etags(tags)
                .register(postRegister)
                .buildEntity();
    }

    private void assertPostEquals(Post randomPost, Post addedPost) {
        assertReflectionEquals(randomPost, addedPost, "id", "tags");
        assertListEqualsInAnyOrder(randomPost.getTags(), addedPost.getTags(), AssertionUtils::isFieldsEquals);
    }


    @Test
    public void test_addPost() {
        Post randomPost = buildRandomPost();
        Post addedPost = postManager.addPost(postRegister.getUuid(), randomPost);
        assertPostEquals(randomPost, addedPost);
    }


    @Test
    public void test_removePost() {
        Post randomPost = buildRandomPost();
        Post addedPost = postManager.addPost(postRegister.getUuid(), randomPost);
        assertPostEquals(randomPost, addedPost);

        Post beforeRemove = postManager.getPost(postRegister.getUuid()).get();
        assertNotNull(beforeRemove);

        postManager.removePost(postRegister.getUuid());

        assertFalse(postManager.getPost(postRegister.getUuid()).isPresent());
    }

    @Test
    public void test_getPost() {
        Post randomPost = buildRandomPost();
        postManager.addPost(postRegister.getUuid(), randomPost);

        Post post = postManager.getPost(postRegister.getUuid()).get();
        assertNotNull(post);
        assertPostEquals(randomPost, post);
    }

    @Test
    public void test_getPost_Null() {
        assertFalse(postManager.getPost(postRegister.getUuid()).isPresent());
    }

}