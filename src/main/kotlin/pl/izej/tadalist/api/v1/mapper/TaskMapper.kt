package pl.izej.tadalist.api.v1.mapper

import pl.izej.tadalist.api.v1.dto.TaskDto
import pl.izej.tadalist.domain.entity.Task
import pl.izej.tadalist.domain.entity.User
import pl.izej.tadalist.utils.toISOString

import java.time.LocalDateTime

fun Task.toDto(): TaskDto = TaskDto(
    id = id,
    text = text,
    done = done,
    date = date,
    userId = user.id
)

fun TaskDto.toTask(user: User): Task = Task(
    text = text,
    done = done ?: false,
    date = date ?: LocalDateTime.now().toISOString(),
    user = user,
    profile = user.profile!!
)
