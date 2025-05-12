package pl.izej.tadalist.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "users")
data class User(

    @Id
    @Column(nullable = false) var id: UUID = UUID.randomUUID(),

    @Enumerated(EnumType.STRING) @Column(nullable = true) var role: UserRole,

    @Column(nullable = false) var email: String,
    @Column(nullable = false) var password: String,
)

enum class UserRole {
    USER, ADMIN
}
