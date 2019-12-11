CREATE TABLE IF NOT EXISTS users
(
    id SERIAL,
    username VARCHAR UNIQUE NOT NULL,
    "password" VARCHAR NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS "sessions"
(
    id SERIAL,
    session_id VARCHAR,
    user_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);