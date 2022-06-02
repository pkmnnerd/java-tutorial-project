package com.jumpydoll.pkmnnerd.javatutorialproject.repository;

import com.jumpydoll.pkmnnerd.javatutorialproject.model.TodoItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TodoRepository extends MongoRepository<TodoItem, String> {
    @Query("{name:'?0'}")
    TodoItem findItemByName(String name);

    @Query(value="{category:'?0'}", fields="{'id' : 1, 'description' : 1, 'finished': 1}")
    List<TodoItem> findAll(String category);

    public long count();
}
