package com.mjc.school.controller.impl;

import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.interfaces.AuthorControllerInterface;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.interfaces.AuthorServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/authors", consumes = {"application/JSON"}, produces = {"application/JSON", "application/XML"})
@Api(produces = "application/json", value = "Operations for creating, updating, retrieving and deleting authors in the application")
public class AuthorController implements AuthorControllerInterface {

  private final AuthorServiceInterface authorService;

  @Autowired
  public AuthorController(@Qualifier("authorService") AuthorServiceInterface authorService) {
    this.authorService = authorService;
  }

  @CommandHandler("2")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "View all authors", response = List.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved all authors"),
          @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
          @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
          @ApiResponse(code = 500, message = "Application failed to process the request")
  }
  )
  public List<AuthorDtoResponse> readAll(@RequestParam String sort,
                                         @RequestParam int page) {
    List<AuthorDtoResponse> authors = authorService.readAll();
    if (authors.isEmpty()) {
      System.out.println("There is no author data in the database yet.");
    }
    return authors;
  }

  @CommandHandler("5")
  @GetMapping("/{id:\\d+}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Retrieves author data by specific id", response = AuthorDtoResponse.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved author data"),
          @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
          @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
          @ApiResponse(code = 500, message = "Application failed to process the request")
  })
  public AuthorDtoResponse readById(@PathVariable Long id) {
      return authorService.readById(id);
  }

  @CommandHandler("8")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Creates new author data", response = AuthorDtoResponse.class)
  @ApiResponses(value = {
          @ApiResponse(code = 201, message = "Successfully created the new author data"),
          @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
          @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
          @ApiResponse(code = 500, message = "Application failed to process the request")
  })
  public AuthorDtoResponse create(@RequestBody AuthorDtoRequest dtoRequest) {
    return authorService.create(dtoRequest);
  }

  @CommandHandler("11")
  @PatchMapping("/{id:\\d+}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Updates the existing author data", response = AuthorDtoResponse.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully updated the author data"),
          @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
          @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
          @ApiResponse(code = 500, message = "Application failed to process the request")
  })
  public AuthorDtoResponse update(@PathVariable Long id,
                                  @RequestBody AuthorDtoRequest dtoRequest) {
    return authorService.update(dtoRequest);
  }

  @CommandHandler("14")
  @DeleteMapping("/{id:\\d+}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Deletes author data by specific id")
  @ApiResponses(value = {
          @ApiResponse(code = 204, message = "Successfully deleted the author data"),
          @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
          @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
          @ApiResponse(code = 500, message = "Application failed to process the request")
  })
  public void deleteById(@PathVariable Long id) {
      authorService.deleteById(id);
  }

  @CommandHandler("16")
  @GetMapping("/{newsId:\\d+}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Retrieves author data by specific news id", response = AuthorDtoResponse.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved author data"),
          @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
          @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
          @ApiResponse(code = 500, message = "Application failed to process the request")
  })
  public AuthorDtoResponse readByNewsId(@PathVariable Long newsId) {
    return authorService.readByNewsId(newsId);
  }
}
