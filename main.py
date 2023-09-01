# Define the number of records
num_records = 10000

# Generate Author inserts
with open('author_inserts.sql', 'w') as file:
    for i in range(1, num_records + 1):
        file.write(f"INSERT INTO Author(id, name, email) VALUES ({i}, 'Author Name {i}', 'author.name{i}@example.com');\n")

# Generate User inserts
with open('user_inserts.sql', 'w') as file:
    for i in range(1, num_records + 1):
        file.write(f"INSERT INTO User(id, username, password) VALUES ({i}, 'user{i}', 'password{i}');\n")

# Generate Post inserts
with open('post_inserts.sql', 'w') as file:
    for i in range(1, num_records + 1):
        file.write(f"INSERT INTO Post(id, title, content, author_id) VALUES ({i}, 'Sample Post {i}', 'This is a sample post {i}.', {i});\n")

# Generate Comment inserts
with open('comment_inserts.sql', 'w') as file:
    for i in range(1, num_records + 1):
        file.write(f"INSERT INTO Comment(id, content, post_id, user_id) VALUES ({i}, 'Great post {i}!', {i}, {i});\n")
