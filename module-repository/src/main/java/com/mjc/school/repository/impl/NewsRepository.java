package com.mjc.school.repository.impl;

import com.mjc.school.repository.interfaces.NewsRepositoryInterface;
import com.mjc.school.repository.model.implementation.NewsEntity;
import org.springframework.stereotype.Repository;

@Repository
public class NewsRepository extends AbstractRepository<NewsEntity, Long> implements NewsRepositoryInterface {

}
