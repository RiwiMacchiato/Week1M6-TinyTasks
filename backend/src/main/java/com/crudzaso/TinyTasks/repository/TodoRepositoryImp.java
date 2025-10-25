package com.crudzaso.TinyTasks.repository;

import com.crudzaso.TinyTasks.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TodoRepositoryImp implements TodoRepository {

    private final Map<Integer, Todo> todos = new HashMap<>();
    private Integer nextId = 1;


    // Estas variables se inicializan directamente en su declaración, lo cual se ejecuta antes que cualquier constructor. Esto es equivalente a:
    // public TodoRepository() {
    //  this.todos = new HashMap<>();
    //  this.nextId = 1L;
    //  por lo tanto, el constructor es opcional, no es obligatorio
    //}

    @Override
    public List<Todo> findAll(){

        return new ArrayList<>(todos.values());
    }

    @Override
    public Optional<Todo> findById(Integer id){

        // Optional es una forma de manejar valores que pueden ser null. Evita NullPointerException y obliga al código que llama a este método a manejar explícitamente el caso donde no se encuentra el Todo.

        return Optional.ofNullable(todos.get(id));
    }

    @Override
    public Todo save(Todo todo) {
        todo.setId(nextId++);
        todos.put(todo.getId(), todo);
        return todo;
    }

    @Override
    public void delete(int id) {

        todos.remove(id);

    }

}
