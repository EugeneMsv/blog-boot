package ru.text.nastya;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.text.nastya.domain.services.impl.CommentaryManagerImplIT;
import ru.text.nastya.domain.services.impl.TagCrudServiceImplIT;
import ru.text.nastya.web.controllers.PostRegisterCrudControllerIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TagCrudServiceImplIT.class,
        CommentaryManagerImplIT.class,
        PostRegisterCrudControllerIT.class
})
public class ITSuite {
}
