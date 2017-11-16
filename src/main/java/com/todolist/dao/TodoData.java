package com.todolist.dao;

import com.todolist.model.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoData extends CrudRepository<Todo, Long> {
    List<Todo> findByTaskState(String taskState);
}
