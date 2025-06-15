package pl.izej.tadalist.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "achievements")
data class Achievement(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(name = "name_key", nullable = false)
    val nameKey: String,

    @Column(name = "description_key", nullable = false)
    val descriptionKey: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: AchievementType, // one_time, repeatable

    @Enumerated(EnumType.STRING)
    @Column(name = "criteria_type", nullable = false)
    val criteriaType: CriteriaType, // task_streak, daily_goal, etc

    @Column(name = "days_required")
    val daysRequired: Int? = null,

    @Column(name = "tasks_per_day")
    val tasksPerDay: Int? = null,

    @Column(name = "time_of_day")
    val timeOfDay: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "reward_type", nullable = false)
    val rewardType: RewardType, // badge, points, etc

    @Column(name = "reward_value")
    val rewardValue: Int? = null,

    @Column(name = "reward_icon")
    val rewardIcon: String? = null,

    @Column(nullable = false)
    val unlocked: Boolean = false,

    @Column(name = "date_unlocked")
    val dateUnlocked: LocalDateTime? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)


enum class AchievementType {
    ONE_TIME,
    REPEATABLE
}

enum class CriteriaType {
    TASK_STREAK,
    DAILY_GOAL,
    TIME_BASED,
    CUSTOM,
    DAILY_TASK_COUNT,
}

enum class RewardType {
    BADGE,
    POINTS,
    VISUAL,
    CUSTOM
}
