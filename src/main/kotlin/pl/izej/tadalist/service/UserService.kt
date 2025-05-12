package pl.izej.tadalist.service

import org.springframework.stereotype.Service
import pl.izej.tadalist.domain.entity.User
import pl.izej.tadalist.domain.repository.UserRepository
import pl.izej.tadalist.internal.exception.NotFoundException
import java.util.UUID

@Service
class UserService(private val userRepository: UserRepository) {

    fun getById(id: String): User = userRepository.findById(UUID.fromString(id))
        .orElseThrow { NotFoundException("Not found user with id $id") }

    fun findByEmail(email: String): User? = userRepository.findByEmail(email)

    fun save(user: User): User = userRepository.save(user)

//    fun updateDetails(userId: String, request: UpdateUserDetailsRequestDto) {
//        val user = getById(userId)
//
//        user.also {
//            request.username?.let { user.username = it }
//            request.firstName?.let { user.firstName = it }
//            request.lastName?.let { user.lastName = it }
//        }
//
//        userRepository.save(user)
//    }
}
