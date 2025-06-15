CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE achievements (
                              id UUID PRIMARY KEY,
                              name_key VARCHAR(255) NOT NULL,
                              description_key VARCHAR(255) NOT NULL,
                              type VARCHAR(50) NOT NULL,
                              criteria_type VARCHAR(50) NOT NULL,
                              days_required INTEGER,
                              tasks_per_day INTEGER,
                              time_of_day VARCHAR(50),
                              reward_type VARCHAR(50) NOT NULL,
                              reward_value INTEGER,
                              reward_icon VARCHAR(255),
                              unlocked BOOLEAN NOT NULL DEFAULT FALSE,
                              date_unlocked TIMESTAMP,
                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO achievements (
    id, name_key, description_key, type, criteria_type, days_required, tasks_per_day, time_of_day, reward_type, reward_value, reward_icon, unlocked, date_unlocked, created_at
) VALUES
-- A. Za ciągłość (streaks)
(uuid_generate_v4(), 'achievement.name.weekly_warrior', 'achievement.description.weekly_warrior', 'ONE_TIME', 'TASK_STREAK', 7, 1, NULL, 'BADGE', NULL, '🎯', false, NULL, NOW()),
(uuid_generate_v4(), 'achievement.name.passionate_tada', 'achievement.description.passionate_tada', 'ONE_TIME', 'TASK_STREAK', 3, NULL, NULL, 'BADGE', NULL, '🔥', false, NULL, NOW()),
(uuid_generate_v4(), 'achievement.name.foundation', 'achievement.description.foundation', 'ONE_TIME', 'TASK_STREAK', 5, 3, NULL, 'BADGE', NULL, '🧱', false, NULL, NOW()),

-- B. Za ilość wykonanych zadań
(uuid_generate_v4(), 'achievement.name.busy_bee', 'achievement.description.busy_bee', 'ONE_TIME', 'DAILY_TASK_COUNT', NULL, 10, NULL, 'BADGE', NULL, '🐝', false, NULL, NOW()),
(uuid_generate_v4(), 'achievement.name.productivity_master', 'achievement.description.productivity_master', 'ONE_TIME', 'TOTAL_TASK_COUNT', NULL, 50, NULL, 'BADGE', NULL, '🧠', false, NULL, NOW()),
(uuid_generate_v4(), 'achievement.name.progressive', 'achievement.description.progressive', 'repeatable', 'WEEKLY_TASK_IMPROVEMENT', NULL, NULL, NULL, 'points', 100, '📈', false, NULL, NOW()),

-- C. Za konkretne godziny / nawyki
(uuid_generate_v4(), 'achievement.name.morning_starter', 'achievement.description.morning_starter', 'ONE_TIME', 'time_specific_streak', 5, 1, 'before_09:00', 'BADGE', NULL, '🌅', false, NULL, NOW()),
(uuid_generate_v4(), 'achievement.name.night_owl', 'achievement.description.night_owl', 'repeatable', 'TIME_SPECIFIC_TASK', NULL, 3, 'after_22:00', 'points', 50, '🌙', false, NULL, NOW()),
(uuid_generate_v4(), 'achievement.name.daily_rhythm', 'achievement.description.daily_rhythm', 'ONE_TIME', 'MULTIPLE_TASKS_PER_DAY', 1, 3, 'morning_noon_evening', 'BADGE', NULL, '🔄', false, NULL, NOW()),

-- D. Zabawa z kategoriami zadań
(uuid_generate_v4(), 'achievement.name.balanced', 'achievement.description.balanced', 'ONE_TIME', 'CATEGORY_DIVERSITY', 1, 3, NULL, 'BADGE', NULL, '🧘', false, NULL, NOW()),
(uuid_generate_v4(), 'achievement.name.specialist', 'achievement.description.specialist', 'ONE_TIME', 'CATEGORY_TASK_COUNT', 7, 10, NULL, 'BADGE', NULL, '🛠️', false, NULL, NOW()),
(uuid_generate_v4(), 'achievement.name.multitalent', 'achievement.description.multitalent', 'ONE_TIME', 'MULTI_CATEGORY_STREAK', 3, 5, NULL, 'BADGE', NULL, '🎨', false, NULL, NOW()),

-- E. Nagrody specjalne / losowe
(uuid_generate_v4(), 'achievement.name.daily_surprise', 'achievement.description.daily_surprise', 'repeatable', 'RANDOM_DAILY_REWARD', NULL, 3, NULL, 'BADGE', NULL, '🎁', false, NULL, NOW()),
(uuid_generate_v4(), 'achievement.name.secret_badge', 'achievement.description.secret_badge', 'ONE_TIME', 'HIDDEN_TASK', NULL, NULL, NULL, 'BADGE', NULL, '🧩', false, NULL, NOW());
