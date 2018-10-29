package com.example.kirill.starbuzz;

public abstract class EatableProperties {
    private String name;
    private String description;
    private int imageResourceId;

    EatableProperties (String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    EatableProperties () {
        name = "empty";
        description = "empty";
        imageResourceId = R.drawable.ic_launcher_foreground;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String toString() {
        return this.name;
    }
}
