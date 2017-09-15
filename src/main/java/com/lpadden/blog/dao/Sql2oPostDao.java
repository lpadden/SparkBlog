package com.lpadden.blog.dao;

import com.lpadden.blog.exc.DaoException;
import com.lpadden.blog.models.Post;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oPostDao implements PostDAO {

    private final Sql2o sql2o;

    public Sql2oPostDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Post post) throws DaoException {
        String sql = "INSERT INTO posts(title, content) VALUES(:title, :content)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(post)
                    .executeUpdate()
                    .getKey();
            post.setId(id);

        } catch (Sql2oException ex) {
            throw new DaoException(ex, "Problem adding post");
        }
    }

    @Override
    public List<Post> findAll() {
        String sql = "SELECT * FROM posts";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Post.class);
        }
    }

    @Override
    public Post findById(int postId) {
        String sql = "SELECT * FROM posts WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", postId)
                    .executeAndFetchFirst(Post.class);
        }
    }
}
