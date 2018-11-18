package ru.text.nastya.domain.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.text.nastya.domain.entities.PostRegister;
import ru.text.nastya.domain.services.crud.PostRegisterCrudService;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Profile("dev")
@Component
public class PosRegisterInitializer implements ApplicationListener<ApplicationReadyEvent>, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(PosRegisterInitializer.class);

    private final PostRegisterCrudService postRegisterCrudService;

    private int order;

    @Autowired
    public PosRegisterInitializer(PostRegisterCrudService postRegisterCrudService) {
        this.postRegisterCrudService = postRegisterCrudService;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Value("${blog.dictionary.init.postRegisterCrudService.order:4}")
    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (!postRegisterCrudService.exists()) {
            init();
        } else {
            logger.info("Initialization of {} rejected, cause data already exists",
                    PostRegister.class.getSimpleName());
        }
        logger.info("Existed postRegisters={}",
                postRegisterCrudService.findAll(new PageRequest(0, 100)).getContent());
    }

    protected void init() {
        for (int i = 0; i < 100; i++) {
            initSinglePostRegister();
        }
        logger.info("Initialization of {}, successfully completed", PostRegister.class.getSimpleName());
    }

    private void initSinglePostRegister() {
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        PostRegister postRegister = new PostRegister();
        postRegister.setPreview(UUID.randomUUID().toString());
        postRegister.setLikes(localRandom.nextLong(0, 100000));
        postRegister.setCommentsNum(localRandom.nextLong(0, 100));
        postRegister.setCreatedTime(LocalDateTime.now());
        postRegister.setViews(localRandom.nextLong(0, 1000));
        postRegisterCrudService.save(postRegister);
    }
}
