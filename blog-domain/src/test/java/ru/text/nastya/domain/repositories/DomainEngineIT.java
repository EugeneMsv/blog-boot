package ru.text.nastya.domain.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.text.nastya.BaseRepositoryIT;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DomainEngineIT extends BaseRepositoryIT {

    @Autowired
    private DataSource dataSource;

    @Test
    public void model_AutoGeneration_Success() throws Exception {
        assertNotNull(dataSource);
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            checkTable(metaData, "post_tag");
            checkTable(metaData, "attachment");
            checkTable(metaData, "post_register");
            checkTable(metaData, "commentary");
            checkTable(metaData, "post");
            checkTable(metaData, "tag");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void checkTable(DatabaseMetaData metaData, String tableName) throws SQLException {

        try (ResultSet tables = metaData.getTables(null, null, tableName, null)) {
            if (!tables.next()) {
                try (ResultSet upperCaseTables =
                             metaData.getTables(null, null, tableName.toUpperCase(), null)) {
                    assertTrue(upperCaseTables.next());
                    assertFalse(upperCaseTables.next());
                }
            } else {
                assert true;
                assertFalse(tables.next());
            }
        }

    }


}