package pl.izej.tadalist.internal.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.web.filter.OncePerRequestFilter
import pl.izej.tadalist.internal.exception.UnauthorizedException
import java.nio.charset.StandardCharsets

class AuthenticationFilter(private val jwtSecret: String) : OncePerRequestFilter() {

    override fun shouldNotFilter(request: HttpServletRequest): Boolean =
        request.requestURI.contains("/api/v1/auth") || request.requestURI.contains("/ws")

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val token = parseJwtToken(request) ?: throw UnauthorizedException("Token is not present")
        val loggedUser = buildLoggedUser(token) ?: throw UnauthorizedException("Token is not valid")

        SecurityContextHolder.getContext().authentication = loggedUser
        filterChain.doFilter(request, response)
    }

    private fun parseJwtToken(request: HttpServletRequest): String? =
        request.getHeader("Authorization")
            ?.takeIf { it.startsWith("Bearer ") && it.split(" ").size == 2 }
            ?.substring(7)

    private fun buildLoggedUser(token: String): UsernamePasswordAuthenticationToken? {
        val claims: Jws<Claims> = Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(jwtSecret.toByteArray(StandardCharsets.UTF_8)))
            .build()
            .parseSignedClaims(token)

        val userDetails = claims.payload.get("user", Map::class.java) as Map<*, *>

        val id: String = userDetails["id"]?.toString() ?: return null
        val email: String = userDetails["email"]?.toString() ?: return null

        val authorities: List<SimpleGrantedAuthority> = userDetails["role"]?.toString()
            ?.let { listOf(SimpleGrantedAuthority("ROLE_$it")) } ?: return null

        return UsernamePasswordAuthenticationToken(User(email, token, authorities), id, authorities)
    }
}
