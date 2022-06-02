package com.jumpydoll.pkmnnerd.javatutorialproject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("todo")
public class TodoItem {
    @Id
    private String id;
    private String description;
    private boolean finished;

    public TodoItem(String id, String description, boolean finished) {
        this.id = id;
        this.description = description;
        this.finished = finished;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
