package es.winepalace.gestwp.service;

import es.winepalace.gestwp.entity.Task;
import es.winepalace.gestwp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public String addTask(Task task) {
        return "";
    }

    @Override
    public List<Task> getAllTasks() {
        return List.of();
    }

    @Override
    public Task getTaskById(Integer id) {
        return null;
    }

    @Override
    public String updateTask(Integer id) {
        return "";
    }

    @Override
    public String deleteTask(Integer id) {
        return "";
    }
}
