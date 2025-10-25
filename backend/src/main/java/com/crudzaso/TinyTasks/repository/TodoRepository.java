package com.crudzaso.TinyTasks.repository;

import com.crudzaso.TinyTasks.model.Todo;

import java.util.*;

public interface TodoRepository {


    List<Todo> findAll();

    Optional<Todo> findById(Integer id);

    Todo save(Todo todo);

    void delete(int id);

}
