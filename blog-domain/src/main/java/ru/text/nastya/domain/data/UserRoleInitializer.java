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
import ru.text.nastya.domain.entities.credential.UserRole;
import ru.text.nastya.domain.repositories.UserRoleRepository;

import java.util.Arrays;

@Component
public class UserRoleInitializer implements ApplicationListener<ApplicationReadyEvent>, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(UserRoleInitializer.class);

    private final UserRoleRepository userRoleRepository;

    private int order;

    @Autowired
    public UserRoleInitializer(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Value("${blog.dictionary.init.userRole.order:1}")
    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (!userRoleRepository.exists()) {
            init();
        } else {
            logger.info("Initialization of {} rejected, cause data already exists",
                    UserRole.class.getSimpleName());
        }
        logger.info("Existed user roles={}",
                userRoleRepository.findAll(new PageRequest(0, 100)).getContent());
    }

    protected void init() {
        userRoleRepository.save(Arrays.asList(new UserRole("USER", "Пользователь"),
                new UserRole("ADMIN", "Администратор")));
        logger.info("Initialization of {}, successfully completed", UserRole.class.getSimpleName());
    }
}
