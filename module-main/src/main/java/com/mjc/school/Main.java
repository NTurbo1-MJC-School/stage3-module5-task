package com.mjc.school;

import static com.mjc.school.controller.helper.Constant.COMMAND_NOT_FOUND;

import java.util.Scanner;

import com.mjc.school.config.ApplicationConfig;
import com.mjc.school.controller.command.CommandReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Main implements CommandLineRunner {

  @Autowired
  private CommandReceiver commandReceiver;

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @Override
  public void run(String... args) {
    Scanner keyboard = new Scanner(System.in);

    while (true) {
      try {
        commandReceiver.printMainMenu();
        String command = keyboard.nextLine();

        switch (command) {
          case "1" -> commandReceiver.getAllNews();
          case "2" -> commandReceiver.getAllAuthors();
          case "3" -> commandReceiver.getAllTags();
          case "4" -> commandReceiver.getNewsById();
          case "5" -> commandReceiver.getAuthorById();
          case "6" -> commandReceiver.getTagById();
          case "7" -> commandReceiver.createNews();
          case "8" -> commandReceiver.createAuthor();
          case "9" -> commandReceiver.createTag();
          case "10" -> commandReceiver.updateNews();
          case "11" -> commandReceiver.updateAuthor();
          case "12" -> commandReceiver.updateTag();
          case "13" -> commandReceiver.deleteNews();
          case "14" -> commandReceiver.deleteAuthor();
          case "15" -> commandReceiver.deleteTag();
          case "16" -> commandReceiver.getAuthorByNewsId();
          case "17" -> commandReceiver.getTagsByNewsId();
          case "18" -> commandReceiver.getAllComments();
          case "19" -> commandReceiver.getCommentById();
          case "20" -> commandReceiver.createComment();
          case "21" -> commandReceiver.updateComment();
          case "22" -> commandReceiver.deleteComment();
          case "0" -> commandReceiver.exit();
          default -> System.out.println(COMMAND_NOT_FOUND);
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
