package ru.text.nastya;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.text.nastya.domain.services.crud.impl.TagCrudServiceImplIT;
import ru.text.nastya.domain.services.manager.impl.CommentaryManagerImplIT;
import ru.text.nastya.domain.services.manager.impl.PostManagerImplIT;
import ru.text.nastya.web.controllers.PostRegisterCrudControllerIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TagCrudServiceImplIT.class,
        CommentaryManagerImplIT.class,
        PostRegisterCrudControllerIT.class,
        PostManagerImplIT.class
})
public class ITSuite {
}
