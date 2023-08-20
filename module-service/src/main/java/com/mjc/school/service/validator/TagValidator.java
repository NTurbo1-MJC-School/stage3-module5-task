package com.mjc.school.service.validator;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.interfaces.TagRepositoryInterface;
import com.mjc.school.repository.model.implementation.TagEntity;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.exceptions.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static com.mjc.school.service.exceptions.ServiceErrorCode.TAG_ID_DOES_NOT_EXIST;

@Component
public class TagValidator extends Validator {

    private final TagRepositoryInterface tagRepository;

    @Autowired
    public TagValidator(@Qualifier("tagRepository") TagRepositoryInterface tagRepository) {
        super();
        this.tagRepository = tagRepository;
    }

    public void validateTagDto(TagDtoRequest tagDtoRequest) {
        validateString(tagDtoRequest.name(), TAG_NAME, TAG_NAME_MIN_LENGTH, TAG_NAME_MAX_LENGTH);
    }

    public void validateTagId(Long tagId) {
        validateNumber(tagId, TAG_ID);
        if (!tagRepository.existById(tagId)) {
            throw new ValidatorException(String.format(TAG_ID_DOES_NOT_EXIST.getMessage(), tagId));
        }
    }
}
