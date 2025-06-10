CREATE TABLE profile (
                         id UUID PRIMARY KEY NOT NULL,
                         user_id UUID NOT NULL UNIQUE,
                         name VARCHAR(255),
                         theme VARCHAR(50) NOT NULL,
                         avatar VARCHAR(512),

                         CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
