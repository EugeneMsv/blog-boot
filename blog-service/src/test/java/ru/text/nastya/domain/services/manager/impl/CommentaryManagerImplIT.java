package ru.text.nastya.domain.services.manager.impl;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.text.nastya.BaseServiceIT;
import ru.text.nastya.domain.entities.PostRegister;
import ru.text.nastya.domain.services.manager.CommentaryManager;
import ru.text.nastya.domain.services.crud.PostRegisterCrudService;

import static org.junit.Assert.assertNotNull;

@DatabaseSetups({@DatabaseSetup(value = "/preset/clean-web.xml", type = DatabaseOperation.DELETE_ALL),
        @DatabaseSetup(value = "/preset/set_commentary.xml", type = DatabaseOperation.INSERT)})
public class CommentaryManagerImplIT extends BaseServiceIT {

    @Autowired
    private CommentaryManager commentaryManager;

    @Autowired
    private PostRegisterCrudService postRegisterCrudService;

    private static PostRegister POST_REGISTER;

    @Test
    public void notNull() {
        assertNotNull(commentaryManager);
    }
   /*  @Before
   public void setUp() {
        PostRegister found = postRegisterService.findOne(0L).get();
        assertNotNull(found);
        assertPersist(found);

        POST_REGISTER = new PostRegister();
        POST_REGISTER.setId(found.getId());
    }

    @Test
    public void test_save_Commentary_Success() throws Exception {
        Commentary randCommentary = buildRandomCommentary();
        randCommentary.setPostRegister(POST_REGISTER);
        Commentary saved = commentaryManager.save(randCommentary);
        assertPersist(saved);
        assertFieldsEquals(randCommentary, saved);
    }

    @Test(expected = Exception.class)
    public void test_save_NotSetReferencePostRegisterCommentary_Exception() throws Exception {
        Commentary randCommentary = buildRandomCommentary();
        Commentary saved = commentaryManager.save(randCommentary);
    }

    @Test
    public void test_findOne_ById_Success() throws Exception {
        Commentary randCommentary = buildRandomCommentary();
        randCommentary.setPostRegister(POST_REGISTER);
        Commentary saved = commentaryManager.save(randCommentary);
        assertPersist(saved);
        assertFieldsEquals(randCommentary, saved);
        Optional<Commentary> optCommentary = commentaryManager.findOne(saved.getId());
        Commentary found = optCommentary.get();
        assertFieldsEquals(saved, found);
    }

    @Test
    public void test_delete_AfterSaveById_Success() throws Exception {
        assertFalse(commentaryManager.exists());
        //save
        Commentary randCommentary = buildRandomCommentary();
        randCommentary.setPostRegister(POST_REGISTER);
        Commentary saved = commentaryManager.save(randCommentary);
        assertFieldsEquals(randCommentary, saved);

        assertTrue(commentaryManager.exists());
        //delete
        commentaryManager.delete(saved.getId());
        assertFalse(commentaryManager.exists());
    }


    @Test
    public void test_findAll_TwoObjectsOnOnePage_Success() throws Exception {
        assertFalse(commentaryManager.exists());
        //save
        Commentary randCommentary1 = buildRandomCommentary();
        randCommentary1.setPostRegister(POST_REGISTER);
        Commentary saved1 = commentaryManager.save(randCommentary1);
        assertFieldsEquals(randCommentary1, saved1);

        Commentary randCommentary2 = buildRandomCommentary();
        randCommentary2.setPostRegister(POST_REGISTER);
        Commentary saved2 = commentaryManager.save(randCommentary2);
        assertFieldsEquals(randCommentary2, saved2);

        Page<Commentary> page = commentaryManager.doFindAllRequest(new PageRequest(0, 10));
        assertFalse(page.hasNext());
        assertThat(Arrays.asList(saved1, saved2), is(page.getContent()));
    }

    @Test
    public void test_findAll_TwoObjectsOnTwoPage_Success() throws Exception {
        assertFalse(commentaryManager.exists());
        //save
        Commentary randCommentary1 = buildRandomCommentary();
        randCommentary1.setPostRegister(POST_REGISTER);
        Commentary saved1 = commentaryManager.save(randCommentary1);
        assertFieldsEquals(randCommentary1, saved1);

        Commentary randCommentary2 = buildRandomCommentary();
        randCommentary2.setPostRegister(POST_REGISTER);
        Commentary saved2 = commentaryManager.save(randCommentary2);
        assertFieldsEquals(randCommentary2, saved2);

        Page<Commentary> page1 = commentaryManager.doFindAllRequest(new PageRequest(0, 1));
        Page<Commentary> page2 = commentaryManager.doFindAllRequest(new PageRequest(0, 2));
        assertTrue(page1.hasNext());
        assertFalse(page2.hasNext());
        assertTrue(page1.getContent().contains(saved1) || page2.getContent().contains(saved1));
        assertTrue(page1.getContent().contains(saved2) || page2.getContent().contains(saved2));
    }*/


}