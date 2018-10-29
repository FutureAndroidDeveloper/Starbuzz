package com.example.kirill.starbuzz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FoodCategoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_category);

        ArrayAdapter<Food> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Food.foods);

        ListView listDrink = (ListView) findViewById(R.id.list_food);
        listDrink.setAdapter(adapter);
    }
}
