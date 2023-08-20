package com.mjc.school.controller.impl;

import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.interfaces.TagControllerInterface;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.interfaces.TagServiceInterface;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tags", consumes = {"application/JSON"}, produces = {"application/JSON", "application/XML"})
public class TagController implements TagControllerInterface {

    private final TagServiceInterface tagService;

    @Autowired
    public TagController(@Qualifier("tagService") TagServiceInterface tagService) {
        this.tagService = tagService;
    }

    @CommandHandler("3")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "View all tags", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all tags")
    }
    )
    public List<TagDtoResponse> readAll(@RequestParam String sort,
                                        @RequestParam int page) {
        List<TagDtoResponse> tags = tagService.readAll();
        if (tags.isEmpty()) {
            System.out.println("There is no tag data in the database yet.");
        }
        return tags;
    }

    @CommandHandler("6")
    @GetMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieves tag data by specific id", response = TagDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved tag data")
    })
    public TagDtoResponse readById(@PathVariable Long id) {
        return tagService.readById(id);
    }

    @CommandHandler("9")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Creates new tag data", response = TagDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created the new tag data")
    })
    public TagDtoResponse create(@RequestBody TagDtoRequest dtoRequest) {
        return tagService.create(dtoRequest);
    }

    @CommandHandler("12")
    @PutMapping("/{id}:\\d+")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Updates the existing tag data", response = TagDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the tag data")
    })
    public TagDtoResponse update(@PathVariable Long id,
                                 @RequestBody TagDtoRequest dtoRequest) {
        return tagService.update(dtoRequest);
    }

    @CommandHandler("15")
    @DeleteMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deletes tag data by specific id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted the tag data")
    })
    public void deleteById(@PathVariable Long id) {
        tagService.deleteById(id);
    }

    @CommandHandler("17")
    @GetMapping("/{newsId:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retrieves tag data by specific news id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved tag data")
    })
    public List<TagDtoResponse> readByNewsId(@PathVariable Long newsId) {
        return tagService.readByNewsId(newsId);
    }
}
