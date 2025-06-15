package pl.izej.tadalist.api.v1.dto

import pl.izej.tadalist.domain.entity.AchievementType
import pl.izej.tadalist.domain.entity.CriteriaType
import pl.izej.tadalist.domain.entity.RewardType
import java.time.LocalDateTime
import java.util.UUID

data class AchievementDto(
    val id: UUID,
    val nameKey: String,
    val descriptionKey: String,
    val type: AchievementType,
    val criteriaType: CriteriaType,
    val daysRequired: Int?,
    val tasksPerDay: Int?,
    val timeOfDay: String?,
    val rewardType: RewardType,
    val rewardValue: Int?,
    val rewardIcon: String?,
    val unlocked: Boolean,
    val dateUnlocked: LocalDateTime?
)

data class AchievementCriteriaDTO(
    val type: CriteriaType,
    val daysRequired: Int? = null,
    val tasksPerDay: Int? = null,
    val timeOfDay: String? = null,
)

data class AchievementRewardDTO(
    val type: RewardType,
    val value: Int? = null,
    val icon: String? = null,
)
