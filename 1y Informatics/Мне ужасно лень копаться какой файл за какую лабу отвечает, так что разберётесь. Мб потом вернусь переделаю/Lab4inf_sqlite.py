import sqlite3
from sqlite3 import Error

def create_connection(path):
    connection = None
    try:
        connection = sqlite3.connect(path)
        print("Connection to SQLite DB successful")
    except Error as e:
        print(f"The error '{e}' occurred")
    return connection

connection = create_connection("C://Users/Kivisd3n/OneDrive/Рабочий стол/udgfjhfcg/Inf/sm2_app")

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
 id INTEGER PRIMARY KEY AUTOINCREMENT,
 name TEXT NOT NULL,
 dol TEXT,
 gender TEXT,
 nationality TEXT
);
"""

execute_query(connection, create_authors_table)

create_books_table = """
CREATE TABLE IF NOT EXISTS books(
 id INTEGER PRIMARY KEY AUTOINCREMENT, 
 title TEXT NOT NULL, 
 description TEXT NOT NULL, 
 author_id INTEGER NOT NULL, 
 FOREIGN KEY (author_id) REFERENCES authors (id)
);
"""

execute_query(connection, create_books_table)

create_readers_table = """
CREATE TABLE IF NOT EXISTS readers (
 id INTEGER PRIMARY KEY AUTOINCREMENT, 
 comment_id INTEGER NOT NULL, 
 book_id integer NOT NULL, 
 FOREIGN KEY (comment_id) REFERENCES comment (id), FOREIGN KEY (book_id)  REFERENCES books (id)
);
"""

execute_query(connection, create_readers_table)

create_comments_table = """
CREATE TABLE IF NOT EXISTS comments (
 id INTEGER PRIMARY KEY AUTOINCREMENT, 
 text TEXT NOT NULL, 
 reader_id INTEGER NOT NULL, 
 book_id INTEGER NOT NULL, 
 FOREIGN KEY (reader_id) REFERENCES readers (id) FOREIGN KEY (book_id) REFERENCES books (id)
);
"""

execute_query(connection, create_comments_table)

print("\n-----------------------------------------------------------------------\n")
#############################################################3

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

print("\n-----------------------------------------------------------------------\n")
#####################################################################

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

print("\n-----------------------------------------------------------------------\n")

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

print("\n-----------------------------------------------------------------------\n")

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

print('\n')

select_reader_comments = """
SELECT
 text as comment,
 COUNT(comments.id) as ids
FROM
 readers,
 comments
WHERE
 readers.id = comments.reader_id
GROUP BY
 readers.comment_id
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


print('\n')

delete_comment = "DELETE FROM comments WHERE id > 2"
execute_query(connection, delete_comment)

select_comments = "SELECT * from comments"
comments = execute_read_query(connection, select_comments)
for comment in comments:
    print(comment)


