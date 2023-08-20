package com.mjc.school.service.impl;

import com.mjc.school.repository.interfaces.CommentRepositoryInterface;
import com.mjc.school.repository.interfaces.NewsRepositoryInterface;
import com.mjc.school.repository.model.implementation.CommentEntity;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.interfaces.CommentServiceInterface;
import com.mjc.school.service.utils.ModelMapper;
import com.mjc.school.service.validator.CommentValidator;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.mjc.school.service.exceptions.ServiceErrorCode.COMMENT_ID_DOES_NOT_EXIST;

@Service
public class CommentService implements CommentServiceInterface {

    private final CommentRepositoryInterface commentRepository;
    private final NewsRepositoryInterface newsRepository;
    private final CommentValidator commentValidator;
    private ModelMapper mapper = Mappers.getMapper(ModelMapper.class);

    @Autowired
    public CommentService(@Qualifier("commentRepository") CommentRepositoryInterface commentRepository,
                          @Qualifier("newsRepository") NewsRepositoryInterface newsRepository,
                          CommentValidator commentValidator) {
        this.commentRepository = commentRepository;
        this.newsRepository = newsRepository;
        this.commentValidator = commentValidator;
    }
    @Override
    public List<CommentDtoResponse> readAll() {
        return mapper.commentEntityListToDtoList(commentRepository.readAll());
    }

    @Override
    public CommentDtoResponse readById(Long id) {
        commentValidator.validateCommentId(id);
        if (commentRepository.existById(id)) {
            CommentEntity commentEntity = commentRepository.readById(id).get();
            return mapper.commentEntityToDto(commentEntity);
        } else {
            throw new NotFoundException(
                    String.format(String.valueOf(COMMENT_ID_DOES_NOT_EXIST.getMessage()), id));
        }
    }

    @Override
    public CommentDtoResponse create(CommentDtoRequest dtoRequest) {

        commentValidator.validateCommentDto(dtoRequest);
        CommentEntity commentEntity = mapper.dtoToCommentEntity(dtoRequest);

        commentEntity.setNews(newsRepository.readById(dtoRequest.newsId()).get());

        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        commentEntity.setCreated(date);
        commentEntity.setModified(date);
        CommentEntity entity = commentRepository.create(commentEntity);
        return mapper.commentEntityToDto(entity);
    }

    @Override
    public CommentDtoResponse update(CommentDtoRequest dtoRequest) {
        commentValidator.validateCommentId(dtoRequest.id());
        commentValidator.validateCommentDto(dtoRequest);

        if (commentRepository.existById(dtoRequest.id())) {
            CommentEntity commentEntity = mapper.dtoToCommentEntity(dtoRequest);

            commentEntity.setNews(newsRepository.readById(dtoRequest.newsId()).get());

            LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            commentEntity.setCreated(date);
            commentEntity.setModified(date);
            CommentEntity entity = commentRepository.create(commentEntity);
            return mapper.commentEntityToDto(entity);
        } else {
            throw new NotFoundException(
                    String.format(COMMENT_ID_DOES_NOT_EXIST.getMessage(), dtoRequest.id()));
        }
    }

    @Override
    public boolean deleteById(Long id) {
        commentValidator.validateCommentId(id);
        if (commentRepository.existById(id)) {
            return commentRepository.deleteById(id);
        } else {
            throw new NotFoundException(String.format(COMMENT_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }

    @Override
    public List<CommentDtoResponse> readByNewsId(Long newsId) {
        return mapper.commentEntityListToDtoList(
                commentRepository.readByNewsId(newsId)
        );
    }
}
