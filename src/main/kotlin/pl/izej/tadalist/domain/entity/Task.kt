package pl.izej.tadalist.domain.entity

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "tasks")
data class Task(

    @Id
    @Column(nullable = false) var id: UUID = UUID.randomUUID(),

    @Column(nullable = false) var text: String,
    @Column(nullable = false) var done: Boolean = false,
    @Column(nullable = false) var date: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User
)
