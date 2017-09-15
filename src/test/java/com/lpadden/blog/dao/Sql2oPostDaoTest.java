package com.lpadden.blog.dao;

import com.lpadden.blog.models.Post;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oPostDaoTest {

    private Sql2oPostDao dao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/init.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        dao = new Sql2oPostDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingPostSetsId() throws Exception {
        Post post = newTestPost();
        int originalPostId = post.getId();

        dao.add(post);

        assertNotEquals(originalPostId, post.getId());
    }

    @Test
    public void addedPostsAreReturnedFromFindAll() throws Exception {
        Post post = newTestPost();

        dao.add(post);

        assertEquals(1, dao.findAll().size());

    }

    @Test
    public void existingPostCanBeFoundById() throws Exception {
        Post post = newTestPost();
        dao.add(post);

        Post foundPost = dao.findById(post.getId());

        assertEquals(post, foundPost);

    }

    @Test
    public void noPostsReturnsEmptyList() throws Exception {
        assertEquals(0, dao.findAll().size());
    }

    private Post newTestPost() {
        return new Post("Test Post", "this is a test post");
    }
}