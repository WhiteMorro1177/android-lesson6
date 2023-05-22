package ru.mirea.tsybulko.lesson6

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var tbGroupName: EditText
    private lateinit var tbNumber: EditText
    private lateinit var tbFavoriteMovieName: EditText

    companion object {
        const val GROUP_TAG: String = "group_name"
        const val NUMBER_TAG: String = "number_in_group"
        const val MOVIE_TAG: String = "favorite_movie"

        const val PREFERENCES_TAG = "current_settings"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tbGroupName = findViewById(R.id.tbGroupName)
        tbNumber = findViewById(R.id.tbNumber)
        tbFavoriteMovieName = findViewById(R.id.tbFavoriteMovieName)

        val prefs: SharedPreferences = getSharedPreferences(PREFERENCES_TAG, Context.MODE_PRIVATE)
        prefs.run {
            tbGroupName.setText(this.getString(GROUP_TAG, ""))
            tbNumber.setText(this.getString(NUMBER_TAG, ""))
            tbFavoriteMovieName.setText(this.getString(MOVIE_TAG, ""))
        }

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            prefs.edit().run {
                putString(GROUP_TAG, tbGroupName.text.toString())
                putString(NUMBER_TAG, tbNumber.text.toString())
                putString(MOVIE_TAG, tbFavoriteMovieName.text.toString())
                apply()
            }
        }
    }
}