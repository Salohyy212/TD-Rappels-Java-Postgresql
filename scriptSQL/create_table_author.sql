CREATE TABLE IF NOT EXISTS author (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(150) NOT NULL,
    last_name VARCHAR(150) NOT NULL,
    sex CHAR(1) CHECK (sex IN ('M', 'F'))
    );


INSERT INTO author (first_name, last_name, sex) VALUES
      ('Stephen','King', 'M'),
      ('Dan','Brown','M'),
      ('Barbara', 'Gartland', 'F');
