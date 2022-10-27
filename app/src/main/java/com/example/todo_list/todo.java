package com.example.todo_list;

import java.util.ArrayList;
import java.util.Date;

public class todo {

    public static ArrayList<todo> todoArrayList = new ArrayList<>();
    public static String TODO_EDIT_EXTRA = "todoEdit";

    private int id;
    private String title;
    private String description;
    private Date deleted;

    public todo(int id, String title, String description, Date deleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deleted = deleted;
    }

    public todo(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        deleted = null;
    }

    public static todo getTodoForID(int passedTodoID) {
        for (todo t : todoArrayList) {
            if (t.getId() == passedTodoID)
                return t;
        }

        return null;
    }

    public static ArrayList<todo> nonDeletedTodos() {
        ArrayList<todo> nonDeleted = new ArrayList<>();
        for (todo todo : todoArrayList) {
            if (todo.getDeleted() == null)
                nonDeleted.add(todo);
        }

        return nonDeleted;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}