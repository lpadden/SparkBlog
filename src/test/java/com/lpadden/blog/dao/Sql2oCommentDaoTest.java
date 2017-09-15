package com.lpadden.blog.dao;

import com.lpadden.blog.models.Comment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oCommentDaoTest {

    private Sql2oCommentDao dao;
    private Sql2oPostDao postDao;
    private Connection conn;
    private Sql2oCommentDao commentDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/init.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        dao = new Sql2oCommentDao(sql2o);
        postDao = new Sql2oPostDao(sql2o);
        commentDao = new Sql2oCommentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingCommentSetsId() throws Exception {
        Comment comment = new Comment("This post is awesome");
    }
}