package pl.izej.tadalist.service

import pl.izej.tadalist.api.v1.dto.LoginRequestDto
import pl.izej.tadalist.api.v1.dto.LoginResponseDto
import pl.izej.tadalist.api.v1.dto.SignupRequestDto
import pl.izej.tadalist.api.v1.dto.SignupResponseDto

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.util.Date
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import pl.izej.tadalist.api.v1.mapper.toUser
import pl.izej.tadalist.domain.entity.User
import pl.izej.tadalist.internal.exception.ConflictException
import pl.izej.tadalist.internal.exception.NotFoundException
import pl.izej.tadalist.internal.exception.UnauthorizedException
import pl.izej.tadalist.internal.security.AuthConfig
import java.nio.charset.StandardCharsets

@Service
class AuthService(
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder,
    private val authConfig: AuthConfig
) {

    fun signup(request: SignupRequestDto): SignupResponseDto {
        userService.findByEmail(request.email)?.let {
            throw ConflictException("User with email ${request.email} already exists")
        }

        val user = userService.save(request.toUser(passwordEncoder))

        return SignupResponseDto(userId = user.id.toString())
    }

    fun login(request: LoginRequestDto): LoginResponseDto {
        val user = userService.findByEmail(request.email) ?: throw NotFoundException("User ${request.email} not found")

        if (passwordEncoder.matches(request.password, user.password)) {
            return LoginResponseDto(user.buildToken(authConfig))
        }

        throw UnauthorizedException("User or password invalid")
    }

    private fun User.buildToken(authConfig: AuthConfig): String = Jwts.builder()
        .subject("AUTHORIZATION")
        .claims(mapOf("user" to mapOf("id" to id.toString(), "email" to email, "role" to role.name)))
        .signWith(Keys.hmacShaKeyFor(authConfig.secret.toByteArray(StandardCharsets.UTF_8)))
        .expiration(Date(Date().time + authConfig.expiration))
        .compact()

}
