package com.mjc.school.controller.impl;

import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.interfaces.NewsControllerInterface;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.interfaces.NewsServiceInterface;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/news", consumes = {"application/JSON"}, produces = {"application/JSON", "application/XML"})
public class NewsController implements NewsControllerInterface {

  private final NewsServiceInterface newsService;

  @Autowired
  public NewsController(@Qualifier("newsService") NewsServiceInterface newsService) {
    this.newsService = newsService;
  }

  @CommandHandler("1")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "View all news", response = List.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved all news"),
          @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
          @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
          @ApiResponse(code = 500, message = "Application failed to process the request")
  }
  )
  public List<NewsDtoResponse> readAll(@RequestParam String sort,
                                       @RequestParam int page) {
    List<NewsDtoResponse> news = newsService.readAll();
    if (news.isEmpty()) {
      System.out.println("There is no news data in the database yet.");
    }
    return news;
  }

  @CommandHandler("4")
  @GetMapping("/{id:\\d+}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Retrieves news data by specific id", response = NewsDtoResponse.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved news data"),
          @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
          @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
          @ApiResponse(code = 500, message = "Application failed to process the request")
  })
  public NewsDtoResponse readById(@PathVariable Long id) {
    return newsService.readById(id);
  }

  @CommandHandler("7")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Creates new news data", response = NewsDtoResponse.class)
  @ApiResponses(value = {
          @ApiResponse(code = 201, message = "Successfully created the new news data"),
          @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
          @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
          @ApiResponse(code = 500, message = "Application failed to process the request")
  })
  public NewsDtoResponse create(@RequestBody NewsDtoRequest dtoRequest) {
    return newsService.create(dtoRequest);
  }

  @CommandHandler("10")
  @PatchMapping("/{id:\\d+}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Updates the existing news data", response = NewsDtoResponse.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully updated the news data"),
          @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
          @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
          @ApiResponse(code = 500, message = "Application failed to process the request")
  })
  public NewsDtoResponse update(@PathVariable Long id,
                                @RequestBody NewsDtoRequest dtoRequest) {
    return newsService.update(dtoRequest);
  }

  @CommandHandler("13")
  @DeleteMapping("/{id:\\d+}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Deletes news data by specific id")
  @ApiResponses(value = {
          @ApiResponse(code = 204, message = "Successfully deleted the news data"),
          @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
          @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
          @ApiResponse(code = 500, message = "Application failed to process the request")
  })
  public void deleteById(@PathVariable Long id) {
    newsService.deleteById(id);
  }
}
