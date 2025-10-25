package com.crudzaso.TinyTasks.model;

public class Todo {

    private int id;
    private String title;
    private boolean done;

    public Todo() {
    }

    public Todo(String title){

        this.title = title;
        this.done = false;
    }

    public Todo(int id, String title, boolean done) {
        this.id = id;
        this.title = title;
        this.done = done;
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", done=" + done +
                '}';
    }


}
