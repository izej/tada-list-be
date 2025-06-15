package pl.izej.tadalist.api.v1.mapper

import pl.izej.tadalist.api.v1.dto.AchievementDto
import pl.izej.tadalist.domain.entity.Achievement

fun Achievement.toDto() = AchievementDto(
    id = id,
    nameKey = nameKey,
    descriptionKey = descriptionKey,
    type = type,
    criteriaType = criteriaType,
    daysRequired = daysRequired,
    tasksPerDay = tasksPerDay,
    timeOfDay = timeOfDay,
    rewardType = rewardType,
    rewardValue = rewardValue,
    rewardIcon = rewardIcon,
    unlocked = unlocked,
    dateUnlocked = dateUnlocked
)
