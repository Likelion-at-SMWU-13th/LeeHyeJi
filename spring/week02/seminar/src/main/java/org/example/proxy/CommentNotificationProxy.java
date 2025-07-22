package org.example.proxy;

import org.example.model.Comment;

public interface CommentNotificationProxy {
    void sendComment(Comment comment);
}
