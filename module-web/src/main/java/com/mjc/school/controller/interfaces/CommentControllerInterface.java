package com.mjc.school.controller.interfaces;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;

import java.util.List;

public interface CommentControllerInterface extends BaseController<CommentDtoRequest, CommentDtoResponse, Long> {
    List<CommentDtoResponse> readByNewsId(Long newsId);
}
