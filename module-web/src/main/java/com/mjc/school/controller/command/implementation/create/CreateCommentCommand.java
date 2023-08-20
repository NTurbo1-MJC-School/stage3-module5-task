package com.mjc.school.controller.command.implementation.create;

import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.helper.CommandHelper;
import com.mjc.school.controller.helper.Constant;
import com.mjc.school.controller.helper.Operations;
import com.mjc.school.controller.interfaces.CommentControllerInterface;
import com.mjc.school.service.dto.CommentDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

@Component
public class CreateCommentCommand implements Command {

    private CommentControllerInterface commentController;
    private Scanner keyboard;

    @Autowired
    public CreateCommentCommand(@Qualifier("commentController") CommentControllerInterface commentController) {
        this.commentController = commentController;
        this.keyboard = new Scanner(System.in);
    }
    @Override
    public void execute() {
        CommentDtoRequest dtoRequest = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(Operations.CREATE_COMMENT.getOperation());
                System.out.println(Constant.NEWS_CONTENT_ENTER);
                String content = keyboard.nextLine();
                System.out.println(Constant.NEWS_ID_ENTER);
                Long newsId = CommandHelper.getKeyboardNumber(Constant.NEWS_ID, keyboard);
                dtoRequest = new CommentDtoRequest(null, content, newsId, null, null);
                isValid = true;

                System.out.println(CommandHelper
                        .findCommandHandlerFor(Operations.CREATE_COMMENT.getOperationNumber(), commentController)
                        .invoke(commentController, dtoRequest));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                System.out.println(e.getCause().getMessage());
            }
        }
    }
}
