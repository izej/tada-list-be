package pl.izej.tadalist.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import pl.izej.tadalist.domain.entity.Achievement
import pl.izej.tadalist.domain.entity.Profile
import pl.izej.tadalist.domain.entity.ProfileAchievement
import java.util.UUID

interface UserAchievementRepository : JpaRepository<ProfileAchievement, UUID> {

    fun existsByProfileAndAchievement(profile: Profile, achievement: Achievement): Boolean

    fun findAllByProfile(profile: Profile): List<ProfileAchievement>
}
