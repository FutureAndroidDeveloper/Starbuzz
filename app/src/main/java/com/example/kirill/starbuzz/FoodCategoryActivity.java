package com.example.kirill.starbuzz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> ListView, View itemView, int position, long id) {
                Intent intent = new Intent(FoodCategoryActivity.this, FoodActivity.class);

                intent.putExtra(FoodActivity.EXTRA_FOODID, (int) id);
                startActivity(intent);
            }
        };

        listDrink.setOnItemClickListener(itemClickListener);
    }
}
