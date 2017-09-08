package ru.text.nastya.domain.services;

import ru.text.nastya.domain.entities.Post;
import ru.text.nastya.PostFilter;

public interface PostService extends FilteredEntityService<Post, PostFilter> {
}
