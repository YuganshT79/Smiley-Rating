package dev.yuganshtyagi.smileyratingexample;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;

import dev.yuganshtyagi.smileyrating.SmileyRatingView;

import com.yugansh.tyagi.smileyratingexample.R;

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
