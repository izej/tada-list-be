package pl.izej.tadalist.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import pl.izej.tadalist.domain.entity.Task
import pl.izej.tadalist.domain.repository.TaskRepository
import java.util.UUID

@Service
class TaskService(private val taskRepository: TaskRepository) {

    fun save(task: Task): Task = taskRepository.save(task)

    fun findById(id: UUID): Task = taskRepository.findById(id).orElseThrow {
        NoSuchElementException("Task with id $id not found")
    }

    fun getTasksByUserId(userId: UUID, pageable: Pageable): Page<Task> = taskRepository.findByUserId(userId, pageable)

    fun deleteTask(id: UUID) {
        taskRepository.delete(findById(id))
    }
}
