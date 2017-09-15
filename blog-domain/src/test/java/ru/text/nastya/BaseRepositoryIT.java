package ru.text.nastya;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import ru.text.nastya.domain.repositories.PostRegisterRepository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(
        locations = "classpath:test.properties")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class})
public class BaseRepositoryIT {

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
}
