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
import ru.text.nastya.domain.entities.credential.UserGroup;
import ru.text.nastya.domain.entities.credential.UserRole;
import ru.text.nastya.domain.services.crud.UserGroupCrudService;

@Component
public class UserGroupInitializer implements ApplicationListener<ApplicationReadyEvent>, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(UserGroupInitializer.class);

    private final UserGroupCrudService userGroupCrudService;

    private int order;

    @Autowired
    public UserGroupInitializer(UserGroupCrudService userGroupCrudService) {
        this.userGroupCrudService = userGroupCrudService;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Value("${blog.dictionary.init.userGroup.order:2}")
    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (!userGroupCrudService.exists()) {
            init();
        } else {
            logger.info("Initialization of {} rejected, cause data already exists",
                    UserGroup.class.getSimpleName());
        }
        logger.info("Existed user groups={}",
                userGroupCrudService.findAll(new PageRequest(0, Integer.MAX_VALUE)).getContent());
    }

    protected void init() {
        UserGroup mainUserGroup = new UserGroup("MAIN", "Основной");
        mainUserGroup.getRoles().add(new UserRole("USER"));

        UserGroup systemUserGroup = new UserGroup("SYSTEM", "Системный");
        systemUserGroup.getRoles().add(new UserRole("ADMIN"));
        userGroupCrudService.save(mainUserGroup);
        userGroupCrudService.save(systemUserGroup);
        logger.info("Initialization of {}, successfully completed", UserGroup.class.getSimpleName());
    }
}
