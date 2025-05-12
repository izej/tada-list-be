CREATE TABLE users
(
    id            UUID NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    role          TEXT NOT NULL DEFAULT 'USER',

    email         TEXT NOT NULL UNIQUE,
    password      TEXT NOT NULL
);
