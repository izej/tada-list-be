package pl.izej.tadalist.api.v1.dto

import java.util.UUID

data class TaskDto(
    val id: UUID?,

    val text: String,
    val done: Boolean?,
    val date: String?,
    val userId: UUID?
)

data class UpdateTaskDoneDto(
    val id: UUID,
    val done: Boolean
)
