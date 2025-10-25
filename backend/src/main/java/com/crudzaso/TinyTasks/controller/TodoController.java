package com.crudzaso.TinyTasks.controller;

import com.crudzaso.TinyTasks.model.Todo;
import com.crudzaso.TinyTasks.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody Map<String, String> request) {
        try {
            String title = request.get("title");
            Todo newTodo = todoService.createTodo(title);
            return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}/toggle")
    public ResponseEntity<?> toggleTodo(@PathVariable int id) {
        return todoService.toggleTodo(id)
                .map(todo -> ResponseEntity.ok(todo))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable int id) {
        if (todoService.deleteTodo(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
