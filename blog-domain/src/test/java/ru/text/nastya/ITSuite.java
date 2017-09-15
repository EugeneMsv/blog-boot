package ru.text.nastya;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.text.nastya.domain.repositories.PostRegisterRepositoryIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PostRegisterRepositoryIT.class,
})

public class ITSuite {
}
