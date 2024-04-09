package es.winepalace.gestwp.controller;

import es.winepalace.gestwp.service.ITaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(path = "api/v1/task")
@Tag(
        name = "Task API",
        description = "Endpoints for managing tasks"
)
@RestController
public class TaskController {

    private final ITaskService taskService;

    @Autowired
    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }
}
