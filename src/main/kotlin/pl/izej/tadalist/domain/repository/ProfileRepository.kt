package pl.izej.tadalist.domain.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.izej.tadalist.domain.entity.Profile
import pl.izej.tadalist.domain.entity.User
import java.util.Optional
import java.util.UUID

@Repository
interface ProfileRepository: CrudRepository<Profile, UUID>  {
    fun findByUser(user: User): Optional<Profile>

    fun findByUserId(userId: UUID): Optional<Profile>
}
