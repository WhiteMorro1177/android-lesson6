package ru.mirea.tsybulko.employeedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db: AppDatabase = App.instance!!.database!!
        val heroDao: HeroDao = db.heroDao()!!
        val heroOne = SuperHero().apply {
            id = 1
            name = "Flash"
            ability = "Speed"
        }

        val heroTwo = SuperHero().apply {
            id = 2
            name = "Black Panter"
            ability = "Meow"
        }

        heroDao.insert(heroOne)
        heroDao.insert(heroTwo)
    }
}