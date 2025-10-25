package com.crudzaso.TinyTasks.service;

import com.crudzaso.TinyTasks.model.Todo;
import com.crudzaso.TinyTasks.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo createTodo(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (title.trim().length() < 3) {
            throw new IllegalArgumentException("Title must be at least 3 characters");
        }

        Todo newTodo = new Todo(title.trim());
        return todoRepository.save(newTodo);
    }

    public Optional<Todo> toggleTodo(int id) {
        Optional<Todo> todoOpt = todoRepository.findById(id);
        if (todoOpt.isPresent()) {
            Todo todo = todoOpt.get();
            todo.setDone(!todo.isDone());
            return Optional.of(todo);
        }
        return Optional.empty();
    }

    public boolean deleteTodo(int id) {
        if (todoRepository.findById(id).isPresent()) {
            todoRepository.delete(id);
            return true;
        }
        return false;
    }
}
