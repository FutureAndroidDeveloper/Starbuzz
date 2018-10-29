package com.example.kirill.starbuzz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DrinkCategoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);

        // Create adapter
        ArrayAdapter<Drink> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Drink.drinks);

        ListView listDrinks = (ListView) findViewById(R.id.list_drinks);
        listDrinks.setAdapter(adapter);

        // Create Listener
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> ListView, View itemView, int position, long id) {
                // Send drink, which user is picked
                Intent intent = new Intent(DrinkCategoryActivity.this, DrinkActivity.class);

                intent.putExtra(DrinkActivity.EXTRA_DRINKID, (int) id);
                startActivity(intent);
            }
        };

        // Set Listener to ListView
        listDrinks.setOnItemClickListener(itemClickListener);
    }
}
