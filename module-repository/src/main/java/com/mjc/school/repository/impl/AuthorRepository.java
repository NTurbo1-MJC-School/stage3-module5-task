package com.mjc.school.repository.impl;

import com.mjc.school.repository.interfaces.AuthorRepositoryInterface;
import com.mjc.school.repository.interfaces.NewsRepositoryInterface;
import com.mjc.school.repository.model.implementation.AuthorEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class AuthorRepository extends AbstractRepository<AuthorEntity, Long> implements AuthorRepositoryInterface {
    @Qualifier("newsRepository")
    private NewsRepositoryInterface newsRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<AuthorEntity> readByNewsId(Long newsId) {
        return readById(
                newsRepository.readById(newsId).get().getAuthor().getId()
        );
    }
}
