package org.example.repository;

import org.example.model.Comment;

public interface CommentRepository {
    void storeComment(Comment comment);
}
