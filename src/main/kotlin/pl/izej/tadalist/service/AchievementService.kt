package pl.izej.tadalist.service

import org.springframework.stereotype.Service
import pl.izej.tadalist.api.v1.dto.AchievementDto
import pl.izej.tadalist.domain.entity.ProfileAchievement
import pl.izej.tadalist.domain.entity.Task
import pl.izej.tadalist.domain.repository.AchievementRepository
import pl.izej.tadalist.domain.repository.ProfileRepository
import pl.izej.tadalist.domain.repository.TaskRepository
import pl.izej.tadalist.domain.repository.UserAchievementRepository
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Service
class AchievementService(
    private val taskRepository: TaskRepository,
    private val achievementRepository: AchievementRepository,
    private val userAchievementRepository: UserAchievementRepository,
    private val profileRepository: ProfileRepository,
) {

    fun checkAndAssignBusyBee(task: Task) {
        if (!task.done) return

        val profile = task.profile
        val taskDate = LocalDate.parse(task.date) // jeśli task.date jest Stringiem, lepiej mieć go jako LocalDate w encji!

        val doneTasksToday = taskRepository.countByProfileAndDoneIsTrueAndDate(profile, taskDate.toString())
        if (doneTasksToday < 10) return

        val achievement = achievementRepository.findByNameKey("achievement.name.busy_bee") ?: return

        val alreadyUnlocked = userAchievementRepository.existsByProfileAndAchievement(profile, achievement)
        if (alreadyUnlocked) return

        val profileAchievement = ProfileAchievement(
            id = UUID.randomUUID(),
            profile = profile,
            achievement = achievement,
            unlockedAt = LocalDateTime.now()
        )

        userAchievementRepository.save(profileAchievement)
    }

    fun checkAllAchievementsForTask(task: Task) {
        checkAndAssignBusyBee(task)
        // other achievements
    }

    fun getUnlockedAchievementsForUser(email: String): List<AchievementDto> {
        val profile = profileRepository.findByUserEmail(email)
            ?: throw IllegalArgumentException("Profile not found for user with email: $email")

        val profileAchievements = userAchievementRepository.findAllByProfile(profile)

        return profileAchievements.map { pa ->
            val a = pa.achievement
            AchievementDto(
                id = a.id,
                nameKey = a.nameKey,
                descriptionKey = a.descriptionKey,
                type = a.type,
                criteriaType = a.criteriaType,
                daysRequired = a.daysRequired,
                tasksPerDay = a.tasksPerDay,
                timeOfDay = a.timeOfDay,
                rewardType = a.rewardType,
                rewardValue = a.rewardValue,
                rewardIcon = a.rewardIcon,
                unlocked = true,
                dateUnlocked = pa.unlockedAt
            )
        }
    }
}
