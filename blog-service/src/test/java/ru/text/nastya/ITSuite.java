package ru.text.nastya;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.text.nastya.domain.services.impl.CommentaryManagerImplIT;
import ru.text.nastya.domain.services.impl.TagServiceImplIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TagServiceImplIT.class,
        CommentaryManagerImplIT.class
})

public class ITSuite {
}
