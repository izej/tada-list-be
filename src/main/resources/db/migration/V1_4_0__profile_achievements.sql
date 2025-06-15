CREATE TABLE profile_achievements (
                                      id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                      profile_id UUID NOT NULL,
                                      achievement_id UUID NOT NULL,
                                      unlocked_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                      CONSTRAINT fk_profile FOREIGN KEY (profile_id) REFERENCES profile(id) ON DELETE CASCADE,
                                      CONSTRAINT fk_achievement FOREIGN KEY (achievement_id) REFERENCES achievements(id) ON DELETE CASCADE,
                                      CONSTRAINT unique_profile_achievement UNIQUE (profile_id, achievement_id)
);