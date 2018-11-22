package com.example.kirill.starbuzz;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class TopLevelActivity extends Activity {
    private SQLiteDatabase database;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        setupOptionsListView();
        setupFavoritesListView();
    }

    private void setupOptionsListView() {
        // Create OnItemClickListener
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> ListView, View itemView, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(TopLevelActivity.this, DrinkCategoryActivity.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(TopLevelActivity.this, FoodCategoryActivity.class);
                    startActivity(intent);
                }
            }
        };

        // Add listener to ListView
        ListView listView = (ListView) findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);
    }

    private void setupFavoritesListView() {
        // Fill list_favorites from cursor
        ListView listFavorites = (ListView) findViewById(R.id.list_favorites);
        SQLiteOpenHelper helper = new StarbuzzDatabaseHelper(this);

        try {
            database = helper.getReadableDatabase();
            cursor = database.query("DRINK",
                    new String[] {"_id", "NAME"},
                    "FAVORITE = 1",
                    null, null, null, null);

            CursorAdapter adapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);

            listFavorites.setAdapter(adapter);
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        }

        // Jump to DrinkActivity
        listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Intent intent = new Intent(TopLevelActivity.this, DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINKID, (int) id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        database.close();

    }
}