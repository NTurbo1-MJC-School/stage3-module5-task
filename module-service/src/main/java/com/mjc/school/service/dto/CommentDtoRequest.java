package com.mjc.school.service.dto;

import java.time.LocalDateTime;

public record CommentDtoRequest(
        Long id,
        String content,
        Long newsId,
        LocalDateTime created,
        LocalDateTime modified
) {
}
