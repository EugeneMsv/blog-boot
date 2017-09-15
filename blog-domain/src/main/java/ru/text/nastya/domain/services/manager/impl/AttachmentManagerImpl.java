package ru.text.nastya.domain.services.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.text.nastya.domain.repositories.AttachmentRepository;
import ru.text.nastya.domain.services.manager.AttachmentManager;

@Service
public class AttachmentManagerImpl implements AttachmentManager {

    private final AttachmentRepository attachmentRepository;

    @Autowired
    public AttachmentManagerImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

}
