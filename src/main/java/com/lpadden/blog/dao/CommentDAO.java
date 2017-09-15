package com.lpadden.blog.dao;

import com.lpadden.blog.exc.DaoException;
import com.lpadden.blog.models.Comment;

import java.util.List;

public interface CommentDAO {
    void add(Comment comment) throws DaoException;

    List<Comment> findAll();

    List<Comment> findByPostId(int postId);
}
