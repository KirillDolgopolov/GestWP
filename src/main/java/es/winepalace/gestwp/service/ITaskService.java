package es.winepalace.gestwp.service;

import es.winepalace.gestwp.entity.Task;
import java.util.List;

public interface ITaskService {
    String addTask(Task task);
    List<Task> getAllTasks();
    Task getTaskById(Integer id);
    String updateTask(Integer id);
    String deleteTask(Integer id);

}
