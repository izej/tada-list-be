package pl.izej.tadalist.domain.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.izej.tadalist.domain.entity.Profile
import pl.izej.tadalist.domain.entity.Task
import java.util.UUID

@Repository
interface TaskRepository: CrudRepository<Task, UUID> {
    fun findByUserId(userId: UUID, pageable: Pageable): Page<Task>

    fun countByProfileAndDoneIsTrueAndDate(profile: Profile, date: String): Int
}
