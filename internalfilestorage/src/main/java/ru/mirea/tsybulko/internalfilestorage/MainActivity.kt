package ru.mirea.tsybulko.internalfilestorage

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fileName = "log_project.txt"
        val dataTextField: EditText = findViewById(R.id.editTextDate)
        val textToWrite: String = dataTextField.text.toString()

        try {
            openFileInput(fileName).run {
                val bytes = ByteArray(available())
                read(bytes)
                dataTextField.setText(String(bytes))
                close()
            }
        } catch (exc: Exception) {
            print(exc.stackTrace)
        }

        findViewById<EditText>(R.id.buttonSave).setOnClickListener {
            try {
                openFileOutput(fileName, Context.MODE_PRIVATE).run {
                    write(textToWrite.toByteArray())
                    close()
                }
            } catch (exc: Exception) {
                print(exc.stackTrace)
            }
        }


    }
}