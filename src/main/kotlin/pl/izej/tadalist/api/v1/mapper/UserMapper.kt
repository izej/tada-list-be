package pl.izej.tadalist.api.v1.mapper

import pl.izej.tadalist.api.v1.dto.SignupRequestDto
import pl.izej.tadalist.api.v1.dto.UserDto
import org.springframework.security.crypto.password.PasswordEncoder
import pl.izej.tadalist.domain.entity.User
import pl.izej.tadalist.domain.entity.UserRole

fun User.toDto(): UserDto = UserDto(
    id = id,
    email = email,
)

fun SignupRequestDto.toUser(passwordEncoder: PasswordEncoder): User = User(
    role = UserRole.USER,
    email = email,
    password = passwordEncoder.encode(password),
)
