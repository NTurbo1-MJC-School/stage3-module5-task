package com.mjc.school.controller.impl;

import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.interfaces.CommentControllerInterface;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;
import com.mjc.school.service.interfaces.CommentServiceInterface;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/comments", consumes = {"application/JSON"}, produces = {"application/JSON", "application/XML"})
public class CommentController implements CommentControllerInterface {

    private final CommentServiceInterface commentService;

    @Autowired
    public CommentController(@Qualifier("commentService") CommentServiceInterface commentService) {
        this.commentService = commentService;
    }
    @Override
    @CommandHandler("18")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "View all comments", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all comments")
    }
    )
    public List<CommentDtoResponse> readAll(@RequestParam String sort,
                                            @RequestParam int page) {
        List<CommentDtoResponse> comments = commentService.readAll();
        if (comments.isEmpty()) {
            System.out.println("There is no comment data in the database yet.");
        }
        return comments;
    }

    @Override
    @CommandHandler("19")
    @GetMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieves comment data by specific id", response = CommentDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved comment data")
    })
    public CommentDtoResponse readById(@PathVariable Long id) {
        return commentService.readById(id);
    }

    @Override
    @CommandHandler("20")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Creates new comment data", response = CommentDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created the new comment data")
    })
    public CommentDtoResponse create(@RequestBody CommentDtoRequest createRequest) {
        return commentService.create(createRequest);
    }

    @Override
    @CommandHandler("21")
    @PutMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Updates the existing comment data", response = CommentDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the comment data")
    })
    public CommentDtoResponse update(@PathVariable Long id,
                                     @RequestBody CommentDtoRequest updateRequest) {
        return commentService.update(updateRequest);
    }

    @Override
    @CommandHandler("22")
    @DeleteMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deletes comment data by specific id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted the comment data")
    })
    public void deleteById(@PathVariable Long id) {
        commentService.deleteById(id);
    }

    @Override
    @GetMapping("/{newsId:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieves comment data by specific news id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved comment data")
    })
    public List<CommentDtoResponse> readByNewsId(@PathVariable Long newsId) {
        return null;
    }
}
