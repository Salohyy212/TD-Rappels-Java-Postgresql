CREATE TABLE IF NOT EXISTS subscribers (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(150) NOT NULL,
    last_name VARCHAR(150) NOT NULL,
    creation_date DATE
    );

INSERT INTO subscribers (first_name, last_name, creation_date) VALUES
     ( 'Paul','Simpsons', '2023-11-11'),
     ( 'Jade','Malorri', '2023-11-21'),
     ( 'Maeve','Smith', '2023-11-30');