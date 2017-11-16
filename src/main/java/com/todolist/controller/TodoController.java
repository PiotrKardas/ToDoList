package com.todolist.controller;

import com.todolist.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.todolist.dao.TodoData;

@Controller
public class TodoController {

    @Autowired
    private TodoData todoData;


    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/addtaskform")
    public String addTaskForm(){
        return "form";
    }

    @PostMapping("/addtask")
    public String addTask(@ModelAttribute Todo todo, ModelMap modelMap){
        modelMap.addAttribute("todo", todo);
        todoData.save(todo);
        return "addtask";
    }

    @GetMapping("/showtodo")
    public String showTasksToDo(ModelMap modelMap){
        modelMap.addAttribute("taskstodo", todoData.findByTaskState("DO WYKONANIA"));
        return "showtodo";
    }

    @GetMapping("/showinprogress")
    public String showTasksInProgress(ModelMap modelMap){
        modelMap.addAttribute("tasksinprogress", todoData.findByTaskState("W TRAKCIE"));
        return "showinprogress";
    }

    @GetMapping("/showdone")
    public String showTasksDone(ModelMap modelMap){
        modelMap.addAttribute("tasksdone", todoData.findByTaskState("WYKONANE"));
        return "showdone";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id, ModelMap modelMap){
        modelMap.addAttribute("todo", todoData.findById(id).get());
        todoData.deleteById(id);
        return "deletetask";
    }

    @GetMapping("/changeinprogress/{id}")
    public String changeStateInProgress(@PathVariable Long id) {
        Todo todo = todoData.findById(id).get();
        todo.setTaskState("W TRAKCIE");
        todoData.save(todo);
        return "changestatus";
    }

    @GetMapping("/changedone/{id}")
    public String changeStateDone(@PathVariable Long id) {
        Todo todo = todoData.findById(id).get();
        todo.setTaskState("WYKONANE");
        todoData.save(todo);
        return "changestatus";
    }

    @GetMapping("/showall")
    public String showAll(ModelMap modelMap){
        modelMap.addAttribute("taskstodo", todoData.findByTaskState("DO WYKONANIA"));
        modelMap.addAttribute("tasksinprogress", todoData.findByTaskState("W TRAKCIE"));
        modelMap.addAttribute("tasksdone", todoData.findByTaskState("WYKONANE"));
        return "showall";
    }

}
