package com.mjc.school.service.interfaces;

import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;
import com.mjc.school.service.dto.TagDtoResponse;

import java.util.List;

public interface CommentServiceInterface extends BaseService<CommentDtoRequest, CommentDtoResponse, Long> {
    List<CommentDtoResponse> readByNewsId(Long newsId);
}
