package com.lpadden.blog.dao;


import com.lpadden.blog.exc.DaoException;
import com.lpadden.blog.models.Comment;
import com.lpadden.blog.models.Post;

import java.util.List;

public interface PostDAO {
    void add(Post post) throws DaoException;

    List<Post> findAll();

    Post findById(int postId);
}
