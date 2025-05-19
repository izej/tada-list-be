CREATE TABLE tasks
(
    id      UUID NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    text    TEXT NOT NULL,
    done    BOOLEAN NOT NULL,
    date    TEXT NOT NULL,

    user_id UUID NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
