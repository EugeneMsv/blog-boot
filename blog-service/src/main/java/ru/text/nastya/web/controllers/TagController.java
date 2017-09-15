package ru.text.nastya.web.controllers;

import ru.text.nastya.domain.entities.Tag;
import ru.text.nastya.domain.services.TagService;
import ru.text.nastya.profiling.LogLevel;
import ru.text.nastya.profiling.LoggableCall;
import ru.text.nastya.web.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Deprecated
@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @LoggableCall(logLevel = LogLevel.INFO, showArgs = true, timeRecord = true, showOutput = true)
    @RequestMapping(method = RequestMethod.GET)
    public Page<Tag> read(Pageable pageable) {
        return tagService.findAll(pageable);
    }

    @LoggableCall(logLevel = LogLevel.INFO, showArgs = true, timeRecord = true, showOutput = true)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Tag> readById(@PathVariable Long id) {
        return tagService.findOne(id).map(ResponseEntity::ok)
                .orElseThrow(DataNotFoundException::new);
    }

    @LoggableCall(logLevel = LogLevel.INFO, showArgs = true, timeRecord = true, showOutput = true)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Tag add(@RequestBody Tag input) {
        return tagService.save(input);
    }
}
