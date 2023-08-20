package com.mjc.school.repository.impl;

import com.mjc.school.repository.interfaces.NewsRepositoryInterface;
import com.mjc.school.repository.interfaces.TagRepositoryInterface;
import com.mjc.school.repository.model.implementation.TagEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TagRepository extends AbstractRepository<TagEntity, Long> implements TagRepositoryInterface {
    @Qualifier("newsRepository")
    private NewsRepositoryInterface newsRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TagEntity> readByNewsId(Long newsId) {
        return newsRepository.readById(newsId).get().getTags();
    }
}
