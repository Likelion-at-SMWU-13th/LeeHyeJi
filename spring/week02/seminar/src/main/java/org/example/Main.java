package org.example;

import org.example.bean.Lion;
import org.example.bean.Person;
import org.example.config.ProjectConfig;
import org.example.model.Comment;
import org.example.service.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.function.Supplier;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Comment comment = new Comment();
        comment.setAuthor("babylion");
        comment.setText("This is a comment");

        var commentService = context.getBean(CommentService.class);
        commentService.publishComment(comment);



    }
}