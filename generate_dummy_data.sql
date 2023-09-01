-- Create a function to insert dummy data
CREATE OR REPLACE FUNCTION generate_dummy_data()
    RETURNS VOID AS
$$
DECLARE
    i BIGINT;
BEGIN
    -- Truncate existing tables
    TRUNCATE TABLE Comment, Post, commenter, Author CASCADE;

    -- Generate Author inserts
    FOR i IN 1..10000
        LOOP
            INSERT INTO Author(id, name, email) VALUES (i, 'Author Name ' || i, 'author.name' || i || '@example.com');
        END LOOP;

    -- Generate User inserts
    FOR i IN 1..10000
        LOOP
            INSERT INTO commenter(id, username, password) VALUES (i, 'user' || i, 'password' || i);
        END LOOP;

    -- Generate Post inserts
    FOR i IN 1..10000
        LOOP
            INSERT INTO Post(id, title, content, author_id)
            VALUES (i, 'Sample Post ' || i, 'This is a sample post ' || i, i);
        END LOOP;

    -- Generate Comment inserts
    FOR i IN 1..10000
        LOOP
            INSERT INTO Comment(id, content, post_id, commenter_id) VALUES (i, 'Great post ' || i || '!', i, i);
        END LOOP;

END;
$$ LANGUAGE plpgsql;
SELECT generate_dummy_data();
