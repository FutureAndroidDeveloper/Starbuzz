package com.example.kirill.starbuzz;

import android.app.Activity;
import android.os.Bundle;

public class DrinkActivity extends Activity {

    protected static final int EXTRA_DRINKID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
    }
}
