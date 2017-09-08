package ru.text.nastya.domain.services.impl;

import ru.text.nastya.domain.entities.Attachment;
import ru.text.nastya.domain.repositories.AttachmentRepository;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.domain.services.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl extends AbstractCrudServiceImpl<Attachment> implements AttachmentService {

    private final AttachmentRepository attachmentRepository;

    @Autowired
    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    protected PersistedEntityRepository<Attachment> getRepository() {
        return attachmentRepository;
    }
}
