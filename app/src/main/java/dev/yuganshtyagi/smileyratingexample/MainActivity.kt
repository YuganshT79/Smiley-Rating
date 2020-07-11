package dev.yuganshtyagi.smileyratingexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yugansh.tyagi.smileyratingexample.R.layout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        rating_bar.setOnRatingBarChangeListener { _, rating, _ ->
            smiley_view.setSmiley(rating = rating)
        }
    }
}