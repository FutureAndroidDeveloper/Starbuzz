package com.example.kirill.starbuzz;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends Activity {
    protected static final String EXTRA_DRINKID = "drinkId";
    private TextView name;
    private TextView description;
    private ImageView photo;
    private CheckBox favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkId = (Integer) getIntent().getExtras().get(EXTRA_DRINKID);

        // Create cursor
        StarbuzzDatabaseHelper databaseHelper = new StarbuzzDatabaseHelper(this);
        try {
            SQLiteDatabase database = databaseHelper.getReadableDatabase();
            Cursor cursor = database.query("DRINK",
                    new String[] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE"},
                    "_id = ?",
                    new String[] {Integer.toString(drinkId)},
                    null, null, null);

            // Go to the first entry in the cursor
            if (cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                boolean isFavorite = (cursor.getInt(3) == 1);

                // Set info about drink
                name = (TextView) findViewById(R.id.name);
                name.setText(nameText);

                description = (TextView) findViewById(R.id.description);
                description.setText(descriptionText);

                photo = (ImageView) findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

                favorite = (CheckBox) findViewById(R.id.favorite);
                favorite.setChecked(isFavorite);
            }

            cursor.close();
            database.close();
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        }
    }

    // Update database by click
    public void onFavoriteClicked(View view) {
        int drinkId = getIntent().getIntExtra(EXTRA_DRINKID, -1);

        ContentValues drinkValues = new ContentValues();
        drinkValues.put("FAVORITE", favorite.isChecked());

        SQLiteOpenHelper helper = new StarbuzzDatabaseHelper(this);
        try {
            SQLiteDatabase database = helper.getWritableDatabase();
            database.update("DRINK",
                    drinkValues,
                    "_id = ?",
                    new String[] {Integer.toString(drinkId)});
        } catch(SQLiteException e) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        }
    }
}
