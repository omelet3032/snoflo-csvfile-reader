CREATE TABLE IF NOT EXISTS question (
    id INTEGER IDENTITY,
    concept VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    keyword1 VARCHAR(255),
    keyword2 VARCHAR(255),
    PRIMARY KEY (id)
)