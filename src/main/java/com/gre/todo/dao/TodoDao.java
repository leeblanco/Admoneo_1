package com.gre.todo.dao;

import java.util.Date;
import java.util.List;

import com.gre.model.Todo;

public interface TodoDao {

    public boolean addTodo(Todo todo);
    public boolean updateTodo(Todo todo);
//    public boolean updateTodo(Todo todo, int id);
    public List<Todo> retrieveTodo();
    public List<Todo> searchTodoByProjectName(String projectName);
    public List<Todo> searchTodoByProjectOwner(String ownerName);
    public List<Todo> searchTodoByDate(Date fromDate, Date toDate);
    
}
