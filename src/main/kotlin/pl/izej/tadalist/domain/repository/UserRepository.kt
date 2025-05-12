package pl.izej.tadalist.domain.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import pl.izej.tadalist.domain.entity.User

import java.util.UUID

@Repository
interface UserRepository : CrudRepository<User, UUID> {
    fun findByEmail(email: String): User?
}
