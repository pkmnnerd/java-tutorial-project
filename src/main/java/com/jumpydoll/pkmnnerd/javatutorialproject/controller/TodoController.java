package com.jumpydoll.pkmnnerd.javatutorialproject.controller;

import com.jumpydoll.pkmnnerd.javatutorialproject.exceptions.NotFoundException;
import com.jumpydoll.pkmnnerd.javatutorialproject.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jumpydoll.pkmnnerd.javatutorialproject.model.TodoItem;
import org.springframework.web.server.ResponseStatusException;

import javax.print.attribute.standard.Media;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class TodoController {

    @Autowired
    TodoRepository todoRepository;

    @GetMapping(value = "/api/todolist/{id}")
    public ResponseEntity<?> getTodoItem(@PathVariable("id") String id) {
        Optional<TodoItem> item = todoRepository.findById(id);
        if (item.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found.");
        }
        return new ResponseEntity<>(item.get(), HttpStatus.OK);
    }

    @GetMapping("/api/todolist")
    public List<TodoItem> listTodoItems() {
        return todoRepository.findAll();
    }

    @PostMapping(value = "/api/todolist", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTodoItem(@RequestBody TodoItem todoItem) {
        todoItem.setId(null);
        TodoItem savedItem = todoRepository.insert(todoItem);
        return new ResponseEntity<TodoItem>(savedItem, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/todolist/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTodoItem(@PathVariable("id") String id, @RequestBody TodoItem todoItem) {
        Optional<TodoItem> item = todoRepository.findById(id);
        if (item.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found.");
        }
        if (!id.equals(todoItem.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'id' in body must match 'id' in path");
        }
        TodoItem savedItem = todoRepository.save(todoItem);
        return new ResponseEntity<TodoItem>(savedItem, HttpStatus.OK);
    }

    @DeleteMapping("/api/todolist/{id}")
    public ResponseEntity<?> deleteTodoItem(@PathVariable("id") String id) {
        Optional<TodoItem> item = todoRepository.findById(id);
        if (item.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found.");
        }
        todoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
