

import mysql.connector
from mysql.connector import Error

def create_connection(host_name, user_name, user_password):
    connection = None
    try:
        connection = mysql.connector.connect(
        host=host_name,
        user=user_name,
        passwd=user_password
    )
        print("Connection to MySQL DB successful")
    except Error as e:
        print(f"The error '{e}' occurred")
    return connection
connection = create_connection("localhost", "user", "1234")


def create_database(connection, query):
    cursor = connection.cursor()
    try:
        cursor.execute(query)
        print("Database created successfully")
    except Error as e:
        print(f"The error '{e}' occurred")



# create_database_query = "CREATE DATABASE sm_app"
# create_database(connection, create_database_query)


def create_connection(host_name, user_name, user_password, db_name):
    connection = None
    try:
        connection = mysql.connector.connect(
            host=host_name,
            user=user_name,
            passwd=user_password,
            database=db_name
        )
        print("Connection to MySQL DB successful")
    except Error as e:
        print(f"The error '{e}' occurred")
    return connection

connection = create_connection("localhost", "user", "1234", "sm_app")


def execute_query(connection, query):
    cursor = connection.cursor()
    try:
        cursor.execute(query)
        connection.commit()
        print("Query executed successfully")
    except Error as e:
        print(f"The error '{e}' occurred")



create_authors_table = """
CREATE TABLE IF NOT EXISTS authors (
 id INT AUTO_INCREMENT,
 name TEXT NOT NULL,
 dol TEXT,
 gender TEXT,
 nationality TEXT,
 PRIMARY KEY (id)
) ENGINE = InnoDB
"""

execute_query(connection, create_authors_table)

create_books_table = """
CREATE TABLE IF NOT EXISTS books(
 id INT AUTO_INCREMENT, 
 title TEXT NOT NULL, 
 description TEXT NOT NULL, 
 author_id INT NOT NULL, 
 FOREIGN KEY (author_id) REFERENCES authors (id),
 PRIMARY KEY (id)
);
"""

execute_query(connection, create_books_table)




create_readers_table = """
CREATE TABLE IF NOT EXISTS readers (
 id INT AUTO_INCREMENT, 
 comment_id INT NOT NULL, 
 book_id INT NOT NULL, 
 FOREIGN KEY (book_id)  REFERENCES books (id),
 PRIMARY KEY(id)
);
"""

execute_query(connection, create_readers_table)

create_comments_table = """
CREATE TABLE IF NOT EXISTS comments (
 id INT AUTO_INCREMENT, 
 text TEXT NOT NULL, 
 reader_id INT NOT NULL, 
 book_id INT NOT NULL, 
 FOREIGN KEY (reader_id) REFERENCES readers (id),
 FOREIGN KEY (book_id) REFERENCES books (id),
 PRIMARY KEY (id)
);
"""

execute_query(connection, create_comments_table)

alter_table_query = """
ALTER TABLE readers
ADD CONSTRAINT `comment_id` FOREIGN KEY (`comment_id`) REFERENCES `comments` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE;
"""
execute_query(connection, create_readers_table)

print("\n1-----------------------------------------------------------------------\n")


create_authors = """
INSERT INTO
 authors (name, dol, gender, nationality)
VALUES
 ('Leo Tolstoy', '1828-1910', 'male', 'Russia'),
 ('William Shakespeare', '1564-1616', 'male', 'England'),
 ('J. K. Rowling', '1965-#', 'female', 'England'),
 ('Agatha Christie', '1890-1976', 'female', 'England'),
 ('R. L. Stine', '1943-#', 'male', 'U.S.');
"""

execute_query(connection, create_authors)

create_books = """
INSERT INTO
 books (title, description, author_id)
VALUES
 ("War and Peace", "There's always a way back to life through love", 1),
 ("Anna Karenina", "Impulsiveness leads to tragedies", 1),
 ("Hamlet", "Indecision can drive you mad", 2),
 ("Harry Potter and the Philosopher’s Stone", "Be good and don't be bad", 3),
 ("Murder on the Orient Express", "Don't trust the gardener or something", 4),
 ("Fear Street", "Boo", 5);
"""

execute_query(connection, create_books)

create_readers = """
INSERT INTO
 readers (comment_id, book_id)
VALUES
 (1, 1),
 (2, 2),
 (3, 3),
 (4, 4),
 (5, 5),
 (6, 6);
"""

execute_query(connection, create_readers)

create_comments = """
INSERT INTO
 comments (text, reader_id, book_id)
VALUES
 ('Heartbreaking', 1, 1),
 ('Meh', 2, 2),
 ('Hard to read', 3, 3),
 ('Fun for a 9-year old kid', 4, 4),
 ('Did not read lol', 5, 5),
 ('Scary or not, havent decided yet', 5, 6);
"""

execute_query(connection, create_comments)

delete_comment = "DELETE FROM authors WHERE id > 5"
execute_query(connection, delete_comment)

delete_comment = "DELETE FROM books WHERE id > 6"
execute_query(connection, delete_comment)

delete_comment = "DELETE FROM readers WHERE id > 6"
execute_query(connection, delete_comment)

delete_comment = "DELETE FROM comments WHERE id > 6"
execute_query(connection, delete_comment)

print("\n2-----------------------------------------------------------------------\n")

def execute_read_query(connection, query):
    cursor = connection.cursor()
    result = None
    try:
        cursor.execute(query)
        result = cursor.fetchall()
        return result
    except Error as e:
        print(f"The error '{e}' occurred")


select_authors = "SELECT * from authors"
authors = execute_read_query(connection, select_authors)
for author in authors:
    print(author)


print("\n3-----------------------------------------------------------------------\n")

select_authors_books = """
SELECT
 authors.id,
 authors.name,
 books.title
FROM
 books
 INNER JOIN authors ON authors.id = books.author_id
"""
authors_books = execute_read_query(connection, select_authors_books)
for authors_book in authors_books:
    print(authors_book)

print("\n4-----------------------------------------------------------------------\n")

select_books_comments = """
SELECT
 books.id,
 books.title,
 comments.text
FROM
 comments
 INNER JOIN books ON books.id = comments.book_id
"""
books_comments = execute_read_query(connection, select_books_comments)
for books_comments in books_comments:
 print(books_comments)

print("\n5-----------------------------------------------------------------------\n")

select_reader_comments = """
SELECT
  readers.comment_id,
  comments.text as comment,
  COUNT(comments.id) as ids
FROM
  readers
  JOIN comments ON readers.id = comments.reader_id
GROUP BY
  readers.comment_id, comments.text;

"""
reader_comments = execute_read_query(connection, select_reader_comments)

for reader_comment in reader_comments:
 print(reader_comment)

print('\n')

update_book_description = """
UPDATE
 books
SET
 description = "Changed my mind, its nice"
WHERE
 id = 2
"""
execute_query(connection, update_book_description)

select_book_description = "SELECT description FROM books WHERE id = 2"
book_description = execute_read_query(connection, select_book_description)
for description in book_description:
 print(description)


print("\n6-----------------------------------------------------------------------\n")


delete_comment = "DELETE FROM comments WHERE id > 2"
execute_query(connection, delete_comment)

select_comments = "SELECT * from comments"
comments = execute_read_query(connection, select_comments)
for comment in comments:
    print(comment)
