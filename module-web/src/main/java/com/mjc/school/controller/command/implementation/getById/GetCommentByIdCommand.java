package com.mjc.school.controller.command.implementation.getById;

import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.helper.CommandHelper;
import com.mjc.school.controller.helper.Constant;
import com.mjc.school.controller.helper.Operations;
import com.mjc.school.controller.interfaces.CommentControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

@Component
public class GetCommentByIdCommand implements Command {

    private CommentControllerInterface commentController;
    private Scanner keyboard;

    @Autowired
    public GetCommentByIdCommand(@Qualifier("commentController") CommentControllerInterface commentController) {
        this.commentController = commentController;
        this.keyboard = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.println(Operations.GET_COMMENT_BY_ID.getOperation());
        System.out.println(Constant.COMMENT_ID_ENTER);
        try {
            Long id = CommandHelper.getKeyboardNumber(Constant.COMMENT_ID, keyboard);
            System.out.println(CommandHelper
                    .findCommandHandlerFor(Operations.GET_COMMENT_BY_ID.getOperationNumber(), commentController)
                    .invoke(commentController, id));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println(e.getCause().getMessage());
        }
    }
}
