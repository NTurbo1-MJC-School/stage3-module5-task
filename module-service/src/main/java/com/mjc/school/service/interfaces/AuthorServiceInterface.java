package com.mjc.school.service.interfaces;

import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;

public interface AuthorServiceInterface extends BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {

    AuthorDtoResponse readByNewsId(Long newsId);
}
