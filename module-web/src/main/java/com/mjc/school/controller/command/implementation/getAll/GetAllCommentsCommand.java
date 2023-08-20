package com.mjc.school.controller.command.implementation.getAll;

import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.helper.CommandHelper;
import com.mjc.school.controller.helper.Operations;
import com.mjc.school.controller.interfaces.CommentControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Component
public class GetAllCommentsCommand implements Command {

    private CommentControllerInterface commentController;

    @Autowired
    public GetAllCommentsCommand(@Qualifier("commentController") CommentControllerInterface commentController) {
        this.commentController = commentController;
    }
    @Override
    public void execute() {
        System.out.println(Operations.GET_ALL_COMMENTS.getOperation());
        try {
            if (CommandHelper
                    .findCommandHandlerFor(Operations.GET_ALL_COMMENTS.getOperationNumber(), commentController)
                    .invoke(commentController) instanceof List<?> allNews) {
                allNews.forEach(System.out::println);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println(e.getCause().getMessage());
        }
    }
}
