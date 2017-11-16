package com.todolist.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.todolist.model.Todo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoRestController {
    private static final String template = "%s";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/api")
    public Todo todo(@RequestParam Long id) {
        return new Todo(String.format(template, id));
    }
}


