package com.yugansh.tyagi.smileyratingexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;

import com.yugansh.tyagi.smileyrating.SmileyRatingView;

public class MainActivity extends AppCompatActivity {

    RatingBar ratingBar;
    SmileyRatingView smileyRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smileyRating = findViewById(R.id.smiley_view);

        ratingBar = findViewById(R.id.rating_bar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.d("Rating", String.valueOf(rating));
                smileyRating.setSmiley(rating);
            }
        });
    }
}
