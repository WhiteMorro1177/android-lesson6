package ru.mirea.tsybulko.notebook

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.*
import java.nio.charset.StandardCharsets

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isExternalStorageWritable()
        isExternalStorageReadable()

        findViewById<Button>(R.id.buttonSave).setOnClickListener {
            writeFileToExternalStorage()
        }

        findViewById<Button>(R.id.buttonLoad).setOnClickListener {
            readFileFromExternalStorage()
        }
    }

    private fun writeFileToExternalStorage() {
        val path: File =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        val file = File(path, findViewById<EditText>(R.id.editTextFilename).text.toString())
        try {
            OutputStreamWriter(FileOutputStream(file.absoluteFile)).run {
                write(findViewById<EditText>(R.id.editTextQuote).text.toString())
                close()
            }
        } catch (e: IOException) {
            Log.w("ExternalStorage", "Error writing $file", e)
            file.createNewFile()
            writeFileToExternalStorage()
        }
    }

    private fun readFileFromExternalStorage() {
        val lines: List<String> = ArrayList()
        val path: File =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        val file = File(path, findViewById<EditText>(R.id.editTextFilename).text.toString())

        try {
            val inputStreamReader =
                InputStreamReader(FileInputStream(file.absoluteFile), StandardCharsets.UTF_8)
            val reader = BufferedReader(inputStreamReader)
            var line: String = reader.readLine()
            while (line != null) {
                lines.plus(line)
                line = reader.readLine()
            }
            Log.w(
                "ExternalStorage",
                java.lang.String.format("Read from file %s successful", lines.toString())
            )
            for (i in 0 until lines.stream().count()) {
                findViewById<TextView>(R.id.tvFileData).text =
                    findViewById<EditText>(R.id.editTextQuote).text.toString()
            }
        } catch (e: Exception) {
            Log.w("ExternalStorage", String.format("Read from file %s failed", e.message))
        }
    }

    /* Проверяем хранилище на доступность чтения и записи*/
    private fun isExternalStorageWritable(): Boolean {
        val state: String = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == state
    }

    /* Проверяем внешнее хранилище на доступность чтения */
    private fun isExternalStorageReadable(): Boolean {
        val state: String = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == state ||
                Environment.MEDIA_MOUNTED_READ_ONLY == state
    }
}