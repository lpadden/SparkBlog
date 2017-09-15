package com.lpadden.blog.dao;

import com.lpadden.blog.exc.DaoException;
import com.lpadden.blog.models.Comment;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oCommentDao implements CommentDAO {

    private Sql2o sql2o;

    public Sql2oCommentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Comment comment) throws DaoException {
        String sql = "INSERT INTO comments(content) VALUES(:content)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(comment)
                    .executeUpdate()
                    .getKey();
            comment.setId(id);
        } catch (Sql2oException ex) {
            throw new DaoException(ex, "Problem adding comment");
        }
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public List<Comment> findByPostId(int postId) {
        return null;
    }
}
