package com.example.kirill.starbuzz;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends Activity {
    protected static final String EXTRA_DRINKID = "drinkId";
    private TextView name;
    private TextView description;
    private ImageView photo;

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
                    new String[] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "_id = ?",
                    new String[] {Integer.toString(drinkId)},
                    null, null, null);

            // Go to the first entry in the cursor
            if (cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);

                // Set info about drink
                name = (TextView) findViewById(R.id.name);
                name.setText(nameText);

                description = (TextView) findViewById(R.id.description);
                description.setText(descriptionText);

                photo = (ImageView) findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);
            }

            cursor.close();
            database.close();
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        }
    }
}
