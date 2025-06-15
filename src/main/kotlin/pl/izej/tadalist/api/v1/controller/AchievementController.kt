package pl.izej.tadalist.api.v1.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.izej.tadalist.api.v1.dto.AchievementDto
import pl.izej.tadalist.service.AchievementService
import java.security.Principal

@RestController
@RequestMapping("/api/v1/achievements")
class AchievementController(
    private val achievementService: AchievementService
) {

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/unlocked")
    fun getUnlockedAchievements(principal: Principal): List<AchievementDto> {
        return achievementService.getUnlockedAchievementsForUser(principal.name)
    }
}