package pl.izej.tadalist.internal.security

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "security.jwt")
class AuthConfig(
    val secret: String,
    val expiration: Int
)
