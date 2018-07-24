package ru.text.nastya.domain.services.filter;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.text.nastya.BaseServiceConfiguration;
import ru.text.nastya.domain.entities.PostRegister;
import ru.text.nastya.filters.PostRegisterFilter;

import static org.junit.Assert.assertEquals;


@DatabaseSetup(value = "/preset/clean-web.xml", type = DatabaseOperation.DELETE_ALL)
@DatabaseSetup(value = "/preset/set_post_register.xml", type = DatabaseOperation.INSERT)
public class PostRegisterFilterServiceIT extends BaseServiceConfiguration {

    @Autowired
    private PostRegisterFilterService postRegisterFilterService;

    @Test
    public void test_filterByPreview() {
        Page<PostRegister> page = postRegisterFilterService.findAll(
                new PostRegisterFilter().withPreview("world"),
                new PageRequest(0, Integer.MAX_VALUE));

        // TODO: 24.07.2018  write asserts
        assertEquals(2, page.getTotalElements());
        System.out.println("page = " + page);
    }
}
