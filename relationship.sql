-- Author table
CREATE TABLE Author (
                        id BIGINT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        email VARCHAR(255) UNIQUE NOT NULL
);

-- User table
CREATE TABLE User (
                      id BIGINT PRIMARY KEY,
                      username VARCHAR(255) UNIQUE NOT NULL,
                      password VARCHAR(255) NOT NULL
);

-- Post table
CREATE TABLE Post (
                      id BIGINT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      content TEXT NOT NULL,
                      author_id BIGINT REFERENCES Author(id)
);

-- Post_Author mapping table for many-to-many relationship
CREATE TABLE Post_Author (
                             post_id BIGINT REFERENCES Post(id),
                             author_id BIGINT REFERENCES Author(id),
                             PRIMARY KEY (post_id, author_id)
);

-- Comment table
CREATE TABLE Comment (
                         id BIGINT PRIMARY KEY,
                         content TEXT NOT NULL,
                         post_id BIGINT REFERENCES Post(id),
                         user_id BIGINT REFERENCES User(id)
);

-- AppUser and Profile for One-to-One relationship
CREATE TABLE AppUser (
                         id BIGINT PRIMARY KEY,
                         username VARCHAR(255) NOT NULL,
                         password VARCHAR(255) NOT NULL
);

CREATE TABLE Profile (
                         id BIGINT PRIMARY KEY,
                         bio TEXT,
                         appuser_id BIGINT UNIQUE REFERENCES AppUser(id) -- One-to-One relationship
);

-- Blog and Article for One-to-Many / Many-to-One relationship
CREATE TABLE Blog (
                      id BIGINT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      description TEXT
);

CREATE TABLE Article (
                         id BIGINT PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         content TEXT NOT NULL,
                         blog_id BIGINT REFERENCES Blog(id) -- Many-to-One relationship
);

-- Writer, Book, and Writer_Book for Many-to-Many relationship
CREATE TABLE Writer (
                        id BIGINT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL
);

CREATE TABLE Book (
                      id BIGINT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL
);

CREATE TABLE Writer_Book (
                             writer_id BIGINT REFERENCES Writer(id),
                             book_id BIGINT REFERENCES Book(id),
                             PRIMARY KEY (writer_id, book_id) -- Many-to-Many relationship
);

-- Inserts for AppUser
INSERT INTO AppUser(id, username, password) VALUES
                                                (1, 'appuser1', 'password1'),
                                                (2, 'appuser2', 'password2'),
                                                (3, 'appuser3', 'password3'),
                                                (4, 'appuser4', 'password4'),
                                                (5, 'appuser5', 'password5'),
                                                (6, 'appuser6', 'password6'),
                                                (7, 'appuser7', 'password7'),
                                                (8, 'appuser8', 'password8'),
                                                (9, 'appuser9', 'password9'),
                                                (10, 'appuser10', 'password10'),
                                                (11, 'appuser11', 'password11'),
                                                (12, 'appuser12', 'password12'),
                                                (13, 'appuser13', 'password13'),
                                                (14, 'appuser14', 'password14'),
                                                (15, 'appuser15', 'password15'),
                                                (16, 'appuser16', 'password16'),
                                                (17, 'appuser17', 'password17'),
                                                (18, 'appuser18', 'password18'),
                                                (19, 'appuser19', 'password19'),
                                                (20, 'appuser20', 'password20');

-- Inserts for Profile
INSERT INTO Profile(id, bio, appuser_id) VALUES
                                             (1, 'Bio for appuser1', 1),
                                             (2, 'Bio for appuser2', 2),
                                             (3, 'Bio for appuser3', 3),
                                             (4, 'Bio for appuser4', 4),
                                             (5, 'Bio for appuser5', 5),
                                             (6, 'Bio for appuser6', 6),
                                             (7, 'Bio for appuser7', 7),
                                             (8, 'Bio for appuser8', 8),
                                             (9, 'Bio for appuser9', 9),
                                             (10, 'Bio for appuser10', 10),
                                             (11, 'Bio for appuser11', 11),
                                             (12, 'Bio for appuser12', 12),
                                             (13, 'Bio for appuser13', 13),
                                             (14, 'Bio for appuser14', 14),
                                             (15, 'Bio for appuser15', 15),
                                             (16, 'Bio for appuser16', 16),
                                             (17, 'Bio for appuser17', 17),
                                             (18, 'Bio for appuser18', 18),
                                             (19, 'Bio for appuser19', 19),
                                             (20, 'Bio for appuser20', 20);

-- Inserts for Blog
INSERT INTO Blog(id, name, description) VALUES
                                            (1, 'Blog 1', 'Description for Blog 1'),
                                            (2, 'Blog 2', 'Description for Blog 2'),
                                            (3, 'Blog 3', 'Description for Blog 3'),
                                            (4, 'Blog 4', 'Description for Blog 4'),
                                            (5, 'Blog 5', 'Description for Blog 5'),
                                            (6, 'Blog 6', 'Description for Blog 6'),
                                            (7, 'Blog 7', 'Description for Blog 7'),
                                            (8, 'Blog 8', 'Description for Blog 8'),
                                            (9, 'Blog 9', 'Description for Blog 9'),
                                            (10, 'Blog 10', 'Description for Blog 10'),
                                            (11, 'Blog 11', 'Description for Blog 11'),
                                            (12, 'Blog 12', 'Description for Blog 12'),
                                            (13, 'Blog 13', 'Description for Blog 13'),
                                            (14, 'Blog 14', 'Description for Blog 14'),
                                            (15, 'Blog 15', 'Description for Blog 15'),
                                            (16, 'Blog 16', 'Description for Blog 16'),
                                            (17, 'Blog 17', 'Description for Blog 17'),
                                            (18, 'Blog 18', 'Description for Blog 18'),
                                            (19, 'Blog 19', 'Description for Blog 19'),
                                            (20, 'Blog 20', 'Description for Blog 20');

-- Inserts for Article
INSERT INTO Article(id, title, content, blog_id) VALUES
                                                     (1, 'Article 1', 'Content for Article 1', 1),
                                                     (2, 'Article 2', 'Content for Article 2', 2),
                                                     (3, 'Article 3', 'Content for Article 3', 3),
                                                     (4, 'Article 4', 'Content for Article 4', 4),
                                                     (5, 'Article 5', 'Content for Article 5', 5),
                                                     (6, 'Article 6', 'Content for Article 6', 6),
                                                     (7, 'Article 7', 'Content for Article 7', 7),
                                                     (8, 'Article 8', 'Content for Article 8', 8),
                                                     (9, 'Article 9', 'Content for Article 9', 9),
                                                     (10, 'Article 10', 'Content for Article 10', 10),
                                                     (11, 'Article 11', 'Content for Article 11', 11),
                                                     (12, 'Article 12', 'Content for Article 12', 12),
                                                     (13, 'Article 13', 'Content for Article 13', 13),
                                                     (14, 'Article 14', 'Content for Article 14', 14),
                                                     (15, 'Article 15', 'Content for Article 15', 15),
                                                     (16, 'Article 16', 'Content for Article 16', 16),
                                                     (17, 'Article 17', 'Content for Article 17', 17),
                                                     (18, 'Article 18', 'Content for Article 18', 18),
                                                     (19, 'Article 19', 'Content for Article 19', 19),
                                                     (20, 'Article 20', 'Content for Article 20', 20);

-- Inserts for Writer
INSERT INTO Writer(id, name) VALUES
                                 (1, 'Writer 1'),
                                 (2, 'Writer 2'),
                                 (3, 'Writer 3'),
                                 (4, 'Writer 4'),
                                 (5, 'Writer 5'),
                                 (6, 'Writer 6'),
                                 (7, 'Writer 7'),
                                 (8, 'Writer 8'),
                                 (9, 'Writer 9'),
                                 (10, 'Writer 10'),
                                 (11, 'Writer 11'),
                                 (12, 'Writer 12'),
                                 (13, 'Writer 13'),
                                 (14, 'Writer 14'),
                                 (15, 'Writer 15'),
                                 (16, 'Writer 16'),
                                 (17, 'Writer 17'),
                                 (18, 'Writer 18'),
                                 (19, 'Writer 19'),
                                 (20, 'Writer 20');

-- Inserts for Book
INSERT INTO Book(id, title) VALUES
                                (1, 'Book 1'),
                                (2, 'Book 2'),
                                (3, 'Book 3'),
                                (4, 'Book 4'),
                                (5, 'Book 5'),
                                (6, 'Book 6'),
                                (7, 'Book 7'),
                                (8, 'Book 8'),
                                (9, 'Book 9'),
                                (10, 'Book 10'),
                                (11, 'Book 11'),
                                (12, 'Book 12'),
                                (13, 'Book 13'),
                                (14, 'Book 14'),
                                (15, 'Book 15'),
                                (16, 'Book 16'),
                                (17, 'Book 17'),
                                (18, 'Book 18'),
                                (19, 'Book 19'),
                                (20, 'Book 20');

-- Inserts for WriterBook (Many-to-Many relationship)
-- Assuming each writer has written at least 2 books
INSERT INTO WriterBook(writer_id, book_id) VALUES
                                               (1, 1),
                                               (1, 2),
                                               (2, 2),
                                               (2, 3),
                                               (3, 3),
                                               (3, 4),
                                               (4, 4),
                                               (4, 5),
                                               (5, 5),
                                               (5, 6),
                                               (6, 6),
                                               (6, 7),
                                               (7, 7),
                                               (7, 8),
                                               (8, 8),
                                               (8, 9),
                                               (9, 9),
                                               (9, 10),
                                               (10, 10),
                                               (10, 11),
                                               (11, 11),
                                               (11, 12),
                                               (12, 12),
                                               (12, 13),
                                               (13, 13),
                                               (13, 14),
                                               (14, 14),
                                               (14, 15),
                                               (15, 15),
                                               (15, 16),
                                               (16, 16),
                                               (16, 17),
                                               (17, 17),
                                               (17, 18),
                                               (18, 18),
                                               (18, 19),
                                               (19, 19),
                                               (19, 20),
                                               (20, 20),
                                               (20, 1);
