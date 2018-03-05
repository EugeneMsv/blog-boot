package ru.text.nastya.domain.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.text.nastya.domain.entities.credential.User;
import ru.text.nastya.domain.entities.credential.UserGroup;
import ru.text.nastya.domain.entities.credential.UserStatus;
import ru.text.nastya.domain.services.crud.UserCrudService;

@Component
public class UserInitializer implements ApplicationListener<ApplicationReadyEvent>, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(UserInitializer.class);

    private final UserCrudService userCrudService;

    private int order;

    @Autowired
    public UserInitializer(UserCrudService userCrudService) {
        this.userCrudService = userCrudService;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Value("${blog.dictionary.init.user.order:3}")
    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (!userCrudService.exists()) {
            init();
        } else {
            logger.info("Initialization of {} rejected, cause data already exists",
                    User.class.getSimpleName());
        }
        logger.info("Existed users={}",
                userCrudService.findAll(new PageRequest(0, Integer.MAX_VALUE)).getContent());
    }

    protected void init() {
        User admin = new User();
        admin.setSsoId("admin");
        admin.setPassword("admin");
        admin.setStatus(UserStatus.ACTIVE);
        admin.getGroups().add(new UserGroup("SYSTEM"));
        userCrudService.save(admin);
        logger.info("Initialization of {}, successfully completed", User.class.getSimpleName());
    }
}
