package ru.mirea.tsybulko.employeedb

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [SuperHero::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao?
}