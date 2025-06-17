package pl.izej.tadalist.event

import java.util.UUID

data class AchievementUnlockedEvent(
    val profileId: UUID,
    val achievementNameKey: String
)