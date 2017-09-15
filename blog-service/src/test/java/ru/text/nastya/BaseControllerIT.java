package ru.text.nastya;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.text.nastya.dto.base.IdentityDto;
import ru.text.nastya.utils.TestPageImpl;

import javax.sql.DataSource;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BlogApplication.class})
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:test.properties")
@Rollback
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class})
public class BaseControllerIT {

    protected static final Random rand = new Random(System.nanoTime());

    @Autowired
    protected ObjectMapper jsonMapper;

    @Autowired
    protected MockMvc mvc;


    @TestConfiguration
    public static class DBUnitConfig {

        @Autowired
        private DataSource dataSource;

        @Bean
        public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection() {
            DatabaseConfigBean bean = new DatabaseConfigBean();
            bean.setDatatypeFactory(new H2DataTypeFactory());

            DatabaseDataSourceConnectionFactoryBean dbConnectionFactory = new DatabaseDataSourceConnectionFactoryBean(
                    dataSource);
            dbConnectionFactory.setDatabaseConfig(bean);
            return dbConnectionFactory;
        }
    }

    protected <D extends IdentityDto> D doSaveRequest(String url, D instance, Class<D> dtoClass) {
        try {
            String saveJson = jsonMapper.writeValueAsString(instance);
            MvcResult mvcSaveResult = mvc.perform(post(url)
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(saveJson)
            ).andExpect(status().isCreated()).andReturn();
            String responseJson = mvcSaveResult.getResponse().getContentAsString();
            return jsonMapper.readValue(responseJson, dtoClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected <D extends IdentityDto> D doGetRequest(String url, Long id, Class<D> dtoClass) {
        try {
            MvcResult mvcGetResult = mvc.perform(get(url + "/{id}", id)
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(status().isOk())
                    .andReturn();
            String responseJson = mvcGetResult.getResponse().getContentAsString();

            return jsonMapper.readValue(responseJson, dtoClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    protected <D extends IdentityDto> Page<D> doFindAllRequest(String url,
                                                               TypeReference<TestPageImpl<D>> typeReference) {
        try {
            // Try to doFindAllRequest
            MvcResult mvcFindAllResult = mvc.perform(get(url))
                    .andExpect(status().isOk())
                    .andReturn();
            String responseJson = mvcFindAllResult.getResponse().getContentAsString();

            return jsonMapper.readValue(responseJson, typeReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void doDeleteRequest(String url, Long id) {
        try {
            mvc.perform(delete(url + "/{id}", id)
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(status().isNoContent()).andReturn();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected <D extends IdentityDto> D doUpdateRequest(String url, Long id, D instance, Class<D> dtoClass) {
        try {
            String updateJson = jsonMapper.writeValueAsString(instance);
            MvcResult mvcUpdateResult = mvc.perform(put(url + "/{id}", id)
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(updateJson))
                    .andExpect(status().isOk())
                    .andReturn();
            String responseJson = mvcUpdateResult.getResponse().getContentAsString();
            return jsonMapper.readValue(responseJson, dtoClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
