package com.example.kirill.starbuzz;

public class Food extends EatableProperties {
    protected static final Food[] foods = {
            new Food("Мивина", "Дудушке очень нравится", R.drawable.mivina),
            new Food("Кора - Рора", "Дудушке очень нравится", R.drawable.cola),
            new Food("Арэшки", "Дудушке очень нравится", R.drawable.nuts),
    };

    private Food(String name, String description, int imageResourceId) {
        super(name, description, imageResourceId);
    }
}
