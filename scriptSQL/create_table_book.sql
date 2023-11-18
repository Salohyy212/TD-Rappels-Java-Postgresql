CREATE TABLE IF NOT EXISTS book (
    id SERIAL PRIMARY KEY,
    book_name VARCHAR(255) NOT NULL,
    author_id INTEGER REFERENCES author(id),
    page_numbers INTEGER NOT NULL,
    topic VARCHAR(10) CHECK (topic IN ('COMEDY', 'ROMANCE', 'OTHER')) DEFAULT 'OTHER',
    release_date DATE,
    CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES author(id)
    );

INSERT INTO book (book_name, author_id, page_numbers, topic, release_date) VALUES
   ('Forteresse Digital', 1, 500, 'OTHER', '1998-01-01'),
   ('Joyland', 2, 250, 'OTHER', '2010-02-15'),
   ('Rolfe et Zarina', 3, 158, 'ROMANCE', '1990-03-30');
