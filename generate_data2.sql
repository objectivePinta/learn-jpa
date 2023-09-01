CREATE OR REPLACE FUNCTION generate_data()
    RETURNS VOID AS $$
DECLARE
    author_id BIGINT;
    post_id BIGINT;
    comment_id BIGINT;
    commenter_id BIGINT;
BEGIN
    -- Truncate existing tables
    TRUNCATE TABLE Comment, Post, Commenter, Author, Post_Author CASCADE;

    -- Generate Authors
    FOR author_id IN 1..200 LOOP
            INSERT INTO Author(id, name, email) VALUES (author_id, 'Author Name ' || author_id, 'author.name' || author_id || '@example.com');

            -- Generate Posts for each Author
            FOR post_id IN 1..5 LOOP
                    INSERT INTO Post(id, title, content, author_id)
                    VALUES ((author_id - 1) * 5 + post_id, 'Sample Post ' || ((author_id - 1) * 5 + post_id), 'This is a sample post content for post ' || ((author_id - 1) * 5 + post_id), author_id);

                    -- Insert into Post_Author mapping table
                    INSERT INTO Post_Author(post_id, author_id) VALUES ((author_id - 1) * 5 + post_id, author_id);

                    -- Generate Commenters for each Post
                    FOR commenter_id IN 1..5 LOOP
                            INSERT INTO Commenter(id, username, password)
                            VALUES ((author_id - 1) * 25 + (post_id - 1) * 5 + commenter_id, 'commenter' || ((author_id - 1) * 25 + (post_id - 1) * 5 + commenter_id), 'password' || ((author_id - 1) * 25 + (post_id - 1) * 5 + commenter_id));

                            -- Generate Comments for each Post
                            INSERT INTO Comment(id, content, post_id, commenter_id)
                            VALUES ((author_id - 1) * 25 + (post_id - 1) * 5 + commenter_id, 'Great comment ' || ((author_id - 1) * 25 + (post_id - 1) * 5 + commenter_id) || '!', (author_id - 1) * 5 + post_id, (author_id - 1) * 25 + (post_id - 1) * 5 + commenter_id);
                        END LOOP;
                END LOOP;
        END LOOP;

END;
$$ LANGUAGE plpgsql;
