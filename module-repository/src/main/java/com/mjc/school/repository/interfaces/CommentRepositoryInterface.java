package com.mjc.school.repository.interfaces;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.implementation.CommentEntity;

import java.util.List;

public interface CommentRepositoryInterface extends BaseRepository<CommentEntity, Long> {

    List<CommentEntity> readByNewsId(Long newsId);
}
