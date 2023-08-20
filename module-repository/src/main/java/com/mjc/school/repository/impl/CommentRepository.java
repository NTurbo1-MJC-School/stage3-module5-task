package com.mjc.school.repository.impl;

import com.mjc.school.repository.interfaces.CommentRepositoryInterface;
import com.mjc.school.repository.interfaces.NewsRepositoryInterface;
import com.mjc.school.repository.model.implementation.CommentEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CommentRepository extends AbstractRepository<CommentEntity, Long>
        implements CommentRepositoryInterface {

    @Qualifier("newsRepository")
    private NewsRepositoryInterface newsRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CommentEntity> readByNewsId(Long newsId) {
        return newsRepository.readById(newsId).get().getComments();
    }
}
