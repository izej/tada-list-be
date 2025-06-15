package pl.izej.tadalist.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import pl.izej.tadalist.domain.entity.Achievement
import java.util.UUID

interface AchievementRepository : JpaRepository<Achievement, UUID> {

    fun findByNameKey(nameKey: String): Achievement?
}
