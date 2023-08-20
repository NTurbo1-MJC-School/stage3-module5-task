package com.mjc.school.service.validator;

import com.mjc.school.repository.interfaces.CommentRepositoryInterface;
import com.mjc.school.repository.interfaces.CommentRepositoryInterface;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.exceptions.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static com.mjc.school.service.exceptions.ServiceErrorCode.COMMENT_ID_DOES_NOT_EXIST;

@Component
public class CommentValidator extends Validator {

    private final CommentRepositoryInterface commentRepository;
    private final NewsValidator newsValidator;

    @Autowired
    public CommentValidator(@Qualifier("commentRepository") CommentRepositoryInterface commentRepository,
                         NewsValidator newsValidator) {
        super();
        this.commentRepository = commentRepository;
        this.newsValidator = newsValidator;
    }

    public void validateCommentDto(CommentDtoRequest dtoRequest) {
        validateString(
                dtoRequest.content(), COMMENT_CONTENT, COMMENT_CONTENT_MIN_LENGTH, COMMENT_CONTENT_MAX_LENGTH);
        newsValidator.validateNewsId(dtoRequest.newsId());
    }

    public void validateCommentId(Long commentId) {

        validateNumber(commentId, COMMENT_ID);

        if (!commentRepository.existById(commentId)) {
            throw new ValidatorException(String.format(COMMENT_ID_DOES_NOT_EXIST.getMessage(), commentId));
        }
    }
}
