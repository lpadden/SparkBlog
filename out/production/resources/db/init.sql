CREATE TABLE IF NOT EXISTS posts
(
    id int PRIMARY KEY auto_increment,
    title VARCHAR,
    content VARCHAR


);

CREATE TABLE IF NOT EXISTS comments
(
    id int PRIMARY KEY auto_increment,
    post_id int,
    content VARCHAR,
    FOREIGN KEY(post_id) REFERENCES public.posts(id)
);