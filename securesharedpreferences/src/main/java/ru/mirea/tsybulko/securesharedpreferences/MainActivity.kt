package ru.mirea.tsybulko.securesharedpreferences

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val keyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val securePrefs: SharedPreferences = EncryptedSharedPreferences.create(
            "secret_prefs",
            keyAlias,
            baseContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        val tbName: EditText = findViewById(R.id.tbAuthorName)

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            securePrefs.edit().putString("secure", tbName.text.toString()).apply()
        }

        findViewById<Button>(R.id.loadButton).setOnClickListener {
            tbName.setText(securePrefs.getString("secure", "Маяковский"))
            findViewById<ImageView>(R.id.imgAuthor).visibility = View.VISIBLE
        }

    }
}