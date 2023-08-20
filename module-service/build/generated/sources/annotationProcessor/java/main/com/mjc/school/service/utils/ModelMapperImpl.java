package com.mjc.school.service.utils;

import com.mjc.school.repository.model.implementation.AuthorEntity;
import com.mjc.school.repository.model.implementation.CommentEntity;
import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.repository.model.implementation.TagEntity;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-21T01:25:05+0600",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.4.1 (Oracle Corporation)"
)
public class ModelMapperImpl implements ModelMapper {

    @Override
    public List<NewsDtoResponse> newsEntityListToDtoList(List<NewsEntity> newsEntityList) {
        if ( newsEntityList == null ) {
            return null;
        }

        List<NewsDtoResponse> list = new ArrayList<NewsDtoResponse>( newsEntityList.size() );
        for ( NewsEntity newsEntity : newsEntityList ) {
            list.add( newsEntityToDto( newsEntity ) );
        }

        return list;
    }

    @Override
    public List<AuthorDtoResponse> authorEntityListToDtoList(List<AuthorEntity> authorEntityList) {
        if ( authorEntityList == null ) {
            return null;
        }

        List<AuthorDtoResponse> list = new ArrayList<AuthorDtoResponse>( authorEntityList.size() );
        for ( AuthorEntity authorEntity : authorEntityList ) {
            list.add( authorEntityToDto( authorEntity ) );
        }

        return list;
    }

    @Override
    public List<TagDtoResponse> tagEntityListToDtoList(List<TagEntity> tagEntityList) {
        if ( tagEntityList == null ) {
            return null;
        }

        List<TagDtoResponse> list = new ArrayList<TagDtoResponse>( tagEntityList.size() );
        for ( TagEntity tagEntity : tagEntityList ) {
            list.add( tagEntityToDto( tagEntity ) );
        }

        return list;
    }

    @Override
    public List<CommentDtoResponse> commentEntityListToDtoList(List<CommentEntity> commentEntityList) {
        if ( commentEntityList == null ) {
            return null;
        }

        List<CommentDtoResponse> list = new ArrayList<CommentDtoResponse>( commentEntityList.size() );
        for ( CommentEntity commentEntity : commentEntityList ) {
            list.add( commentEntityToDto( commentEntity ) );
        }

        return list;
    }

    @Override
    public NewsDtoResponse newsEntityToDto(NewsEntity newsEntity) {
        if ( newsEntity == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String content = null;
        LocalDateTime createDate = null;
        LocalDateTime lastUpdatedDate = null;

        id = newsEntity.getId();
        title = newsEntity.getTitle();
        content = newsEntity.getContent();
        createDate = newsEntity.getCreateDate();
        lastUpdatedDate = newsEntity.getLastUpdatedDate();

        Long authorId = null;
        List<Long> tagIds = null;

        NewsDtoResponse newsDtoResponse = new NewsDtoResponse( id, title, content, createDate, lastUpdatedDate, authorId, tagIds );

        return newsDtoResponse;
    }

    @Override
    public AuthorDtoResponse authorEntityToDto(AuthorEntity authorEntity) {
        if ( authorEntity == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = authorEntity.getId();
        name = authorEntity.getName();

        AuthorDtoResponse authorDtoResponse = new AuthorDtoResponse( id, name );

        return authorDtoResponse;
    }

    @Override
    public TagDtoResponse tagEntityToDto(TagEntity tagEntity) {
        if ( tagEntity == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = tagEntity.getId();
        name = tagEntity.getName();

        List<Long> newsIds = null;

        TagDtoResponse tagDtoResponse = new TagDtoResponse( id, name, newsIds );

        return tagDtoResponse;
    }

    @Override
    public CommentDtoResponse commentEntityToDto(CommentEntity commentEntity) {
        if ( commentEntity == null ) {
            return null;
        }

        Long id = null;
        String content = null;
        LocalDateTime created = null;
        LocalDateTime modified = null;

        id = commentEntity.getId();
        content = commentEntity.getContent();
        created = commentEntity.getCreated();
        modified = commentEntity.getModified();

        Long newsId = null;

        CommentDtoResponse commentDtoResponse = new CommentDtoResponse( id, content, newsId, created, modified );

        return commentDtoResponse;
    }

    @Override
    public NewsEntity dtoToNewsEntity(NewsDtoRequest newsDtoRequest) {
        if ( newsDtoRequest == null ) {
            return null;
        }

        NewsEntity newsEntity = new NewsEntity();

        newsEntity.setId( newsDtoRequest.id() );
        newsEntity.setTitle( newsDtoRequest.title() );
        newsEntity.setContent( newsDtoRequest.content() );

        return newsEntity;
    }

    @Override
    public AuthorEntity dtoToAuthorEntity(AuthorDtoRequest authorDtoRequest) {
        if ( authorDtoRequest == null ) {
            return null;
        }

        AuthorEntity authorEntity = new AuthorEntity();

        authorEntity.setId( authorDtoRequest.id() );
        authorEntity.setName( authorDtoRequest.name() );

        return authorEntity;
    }

    @Override
    public TagEntity dtoToTagEntity(TagDtoRequest tagDtoRequest) {
        if ( tagDtoRequest == null ) {
            return null;
        }

        TagEntity tagEntity = new TagEntity();

        tagEntity.setId( tagDtoRequest.id() );
        tagEntity.setName( tagDtoRequest.name() );

        return tagEntity;
    }

    @Override
    public CommentEntity dtoToCommentEntity(CommentDtoRequest commentDtoRequest) {
        if ( commentDtoRequest == null ) {
            return null;
        }

        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setId( commentDtoRequest.id() );
        commentEntity.setContent( commentDtoRequest.content() );
        commentEntity.setCreated( commentDtoRequest.created() );
        commentEntity.setModified( commentDtoRequest.modified() );

        return commentEntity;
    }
}
