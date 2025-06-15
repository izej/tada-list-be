package pl.izej.tadalist.api.v1.controller

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.izej.tadalist.api.v1.dto.TaskDto
import pl.izej.tadalist.api.v1.dto.UpdateTaskDoneDto
import pl.izej.tadalist.api.v1.mapper.toDto
import pl.izej.tadalist.api.v1.mapper.toTask
import pl.izej.tadalist.service.AchievementService
import pl.izej.tadalist.service.TaskService
import pl.izej.tadalist.service.UserService
import java.security.Principal
import java.util.UUID

@RestController
@RequestMapping("/api/v1/tasks")
class TaskController(
    private val taskService: TaskService,
    private val userService: UserService,
    private val achievementService: AchievementService
) {

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/create")
    fun createTask(@RequestBody taskDto: TaskDto, principal: Principal): TaskDto {
        val user = userService.findByEmail(principal.name)
            ?: throw NoSuchElementException("User with email ${principal.name} not found")

        val savedTask = taskService.save(taskDto.toTask(user))

        if (savedTask.done) {
            achievementService.checkAllAchievementsForTask(savedTask)
        }

        return savedTask.toDto()
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PatchMapping("/done")
    fun updateTaskDone(
        @RequestBody dto: UpdateTaskDoneDto
    ): TaskDto {
        val task = taskService.findById(dto.id)

        task.apply {
            done = dto.done
        }

        val savedTask = taskService.save(task)

        if (savedTask.done) {
            achievementService.checkAllAchievementsForTask(savedTask)
        }

        return savedTask.toDto()
    }

    @GetMapping()
    fun getTasks(principal: Principal, pageable: Pageable): Page<TaskDto> {
        val user = userService.findByEmail(principal.name)
            ?: throw NoSuchElementException("User with email ${principal.name} not found")

        return taskService.getTasksByUserId(user.id, pageable).map { it.toDto() }
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: UUID) {
        taskService.deleteTask(id)
    }
}
